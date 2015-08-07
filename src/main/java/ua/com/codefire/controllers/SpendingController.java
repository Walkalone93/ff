package ua.com.codefire.controllers;

import java.sql.SQLException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.codefire.entity.Spending;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.com.codefire.TransformDate;
import ua.com.codefire.dao.services.SpendingDao;

@Controller
public class SpendingController {

    @Autowired
    private SpendingDao spendingDao;
    private final TransformDate transformDate = new TransformDate();

    @RequestMapping(value = "/spending", method = RequestMethod.GET)
    public ModelAndView start() {
        ModelAndView view = new ModelAndView("spending");
        return view;
    }

    @RequestMapping(headers = "content-type=application/json; charset=UTF-8",
            value = "/findSpending", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> findByDate(@RequestParam("date") String date) throws SQLException {
        String newDate = transformDate.invertDate(date);
        JSONArray arr = new JSONArray();
        List<Spending> listSpending = spendingDao.findByDateSpending(newDate);

        for (Spending item : listSpending) {
            JSONObject sub = new JSONObject();
            sub.put("description", item.getDescription());
            sub.put("sum", item.getSum());
            sub.put("currency", item.getCurrency());
            sub.put("date", date);
            arr.add(sub);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html; charset=utf-8");
        ResponseEntity<String> entity = new ResponseEntity<String>(arr.toString(), headers, HttpStatus.OK);
        return entity;
    }

    @RequestMapping(headers = "content-type=application/json; charset=UTF-8",
            value = "/removeSpending", method = RequestMethod.GET)
    public void remove(@RequestParam("date") String date,
            @RequestParam("description") String description,
            @RequestParam("sum") float sum,
            @RequestParam("currency") String currency) throws SQLException {
        Spending s = new Spending(description, sum, currency, transformDate.invertDate(date));
        spendingDao.removeSpending(s);
    }

    @RequestMapping(headers = "content-type=application/json; charset=UTF-8",
            value = "/addSpending", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> add(@RequestParam("date") String date,
            @RequestParam("description") String description,
            @RequestParam("sum") float sum,
            @RequestParam("currency") String currency) throws SQLException {
        String invertD = transformDate.invertDate(date);
        Spending s = new Spending(description, sum, currency, invertD);
        spendingDao.saveSpending(s);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html; charset=utf-8");
        JSONArray arr = new JSONArray();
        List<Spending> listSpending = spendingDao.findByDateSpending(invertD);
        System.out.println(listSpending);
        for (Spending item : listSpending) {
            JSONObject sub = new JSONObject();
            sub.put("description", item.getDescription());
            sub.put("sum", item.getSum());
            sub.put("currency", item.getCurrency());
            sub.put("date", date);
            arr.add(sub);
        }
        ResponseEntity<String> entity = new ResponseEntity<String>(arr.toString(), headers, HttpStatus.OK);
        return entity;
    }
}