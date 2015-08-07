package ua.com.codefire.controllers;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.codefire.TransformDate;
import ua.com.codefire.dao.services.CourseDao;
import ua.com.codefire.dao.services.IncomingDao;
import ua.com.codefire.dao.services.SpendingDao;
import ua.com.codefire.entity.Course;
import ua.com.codefire.entity.Incoming;
import ua.com.codefire.entity.Spending;

@Controller
public class HistoryController {

    @Autowired
    private IncomingDao incomingDao;
    @Autowired
    private SpendingDao spendingDao; 
    @Autowired
    private CourseDao courseDao;
    
    private final TransformDate transformDate = new TransformDate();
    private float USD;
    private float EURO;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView start() {
        ModelAndView view = new ModelAndView("history");
        return view;
    }

    @RequestMapping(headers = "content-type=application/json; charset=UTF-8",
            value = "/historyIncoming", method = RequestMethod.GET)
    public @ResponseBody
    String findByDateInc(@RequestParam("date1") String date1,
            @RequestParam("date2") String date2,
            @RequestParam("amount") Integer amount,
            @RequestParam("change") String change) throws SQLException {

        // fetch course from db
        List<Course> listCourse = courseDao.findCourse();
        for (Course c : listCourse) {
            if (c.getName().equals("USD")) {
                USD = c.getValue();
            } else if (c.getName().equals("EURO")) {
                EURO = c.getValue();
            }
        }

        // convert from currency to grn.
        String newDate1 = transformDate.invertDate(date1);
        String newDate2 = transformDate.invertDate(date2);
        // Incoming
        List<Incoming> listIncoming = incomingDao.findByRangeIncoming(newDate1, newDate2);
        for (Incoming i : listIncoming) {
            if (i.getCurrency().equals("USD")) {
                i.setSum(i.getSum() * USD);
            } else if (i.getCurrency().equals("EURO")) {
                i.setSum(i.getSum() * EURO);
            }
        }
        // Spending
        List<Spending> listSpending = spendingDao.findByRangeSpending(newDate1, newDate2);
        for (Spending s : listSpending) {
            if (s.getCurrency().equals("USD")) {
                s.setSum(s.getSum() * USD);
            } else if (s.getCurrency().equals("EURO")) {
                s.setSum(s.getSum() * EURO);
            }
        }
        // end convert currency

        // convert date
        for (Incoming i : listIncoming) {
            i.setDate(transformDate.reInvertDate(i.getDate()));
        }
        for (Spending s : listSpending) {
            s.setDate(transformDate.reInvertDate(s.getDate()));
        }
        // end convert date

        // fill all days/month/year 0
        Map<String, Float> mapIncoming = new HashMap<String, Float>();
        String d = null;
        for (int i = 0; i < amount; i++) {
            if ("day".equals(change)) {
                int day = Integer.parseInt(date1.substring(0, 2));
                if ((day + i) < 10) {
                    d = "0" + (day + i) + date1.substring(2);
                } else {
                    d = day + i + date1.substring(2);
                }
                mapIncoming.put(d, 0.0f);
            } else if ("month".equals(change)) {
                int month = Integer.parseInt(date1.substring(3, 5));
                if ((month + i) < 10) {
                    d = date1.substring(0, 3) + "0" + (month + i) + date1.substring(5);
                } else {
                    d = date1.substring(0, 3) + (month + i) + date1.substring(5);
                }
                mapIncoming.put(d, 0.0f);
            } else {
                int day = Integer.parseInt(date1.substring(6));
                d = date1.substring(0, 6) + (day + i);
                mapIncoming.put(d, 0.0f);
            }
        }
        // end fill all days 0

        Map<String, Float> mapSpending = new HashMap<String, Float>(mapIncoming);
        String k = null;
        float v = 0.0f;

        // addition all sum in one day/month/year FOR INCOMING
        String ex = null;
        String fetch = null;
        for (Map.Entry<String, Float> m : mapIncoming.entrySet()) {
            for (int s = 0; s < listIncoming.size(); s++) {
                ex = "ex";
                fetch = "fetch";
                if (change.equals("day")) {
                    ex = m.getKey().substring(0, 2);
                    fetch = listIncoming.get(s).getDate().substring(0, 2);
                } else if (change.equals("month")) {
                    ex = m.getKey().substring(2, 5);
                    fetch = listIncoming.get(s).getDate().substring(2, 5);
                } else {
                    ex = m.getKey().substring(6);
                    fetch = listIncoming.get(s).getDate().substring(6);
                }

                if (ex.equals(fetch)) {
                    k = m.getKey();
                    v = m.getValue() + listIncoming.get(s).getSum();
                    mapIncoming.put(k, v);
                }
            }
        } // end addition FOR INCOMING
        
        // addition all sum in one day/month/year FOR SPENDING
        for (Map.Entry<String, Float> m : mapSpending.entrySet()) {
            for (int s = 0; s < listSpending.size(); s++) {
                ex = "ex";
                fetch = "fetch";
                if (change.equals("day")) {
                    ex = m.getKey().substring(0, 2);
                    fetch = listSpending.get(s).getDate().substring(0, 2);
                } else if (change.equals("month")) {
                    ex = m.getKey().substring(2, 5);
                    fetch = listSpending.get(s).getDate().substring(2, 5);
                } else {
                    ex = m.getKey().substring(6);
                    fetch = listSpending.get(s).getDate().substring(6);
                }

                if (ex.equals(fetch)) {
                    k = m.getKey();
                    v = m.getValue() + listSpending.get(s).getSum();
                    mapSpending.put(k, v);
                }
            }
        } // end addition
        // end addition FOR SPENDING

        //sorting MAPs    
        Map<String, Float> sortedMapInc = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        sortedMapInc.putAll(mapIncoming);

        Map<String, Float> sortedMapSpend = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        sortedMapSpend.putAll(mapSpending);

        // write to 2 array INCOMING
        String dateInc[] = new String[sortedMapInc.size()];
        Float sumInc[] = new Float[sortedMapInc.size()];
        int a = 0;
        for (Map.Entry<String, Float> pair : sortedMapInc.entrySet()) {
            dateInc[a] = pair.getKey();
            sumInc[a++] = pair.getValue();
        }
        // write to 2 array
        String dateSpend[] = new String[sortedMapSpend.size()];
        Float sumSpend[] = new Float[sortedMapSpend.size()];
        int b = 0;
        for (Map.Entry<String, Float> pair : sortedMapSpend.entrySet()) {
            dateSpend[b] = pair.getKey();
            sumSpend[b++] = pair.getValue();
        }

        // sent to AJAX
        JSONArray arr = new JSONArray();
        for (int i = 0; i < dateInc.length; i++) {
            JSONObject sub = new JSONObject();
            sub.put("dateInc", dateInc[i]);
            sub.put("sumInc", sumInc[i]);
            sub.put("dateSpend", dateSpend[i]);
            sub.put("sumSpend", sumSpend[i]);
            arr.add(sub);
        }
        return arr.toString();
    }
}