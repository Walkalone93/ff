package ua.com.codefire.controllers;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.codefire.dao.services.CourseDao;
import ua.com.codefire.entity.Course;

@Controller
public class CourseController {

    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "/saveExchange", method = RequestMethod.GET)
    public void add(@RequestParam("USD") float USD,
            @RequestParam("EURO") float EURO) {
        courseDao.saveCourse(USD, EURO);
    }

    @RequestMapping(value = "/getExchange", method = RequestMethod.GET)
    public @ResponseBody
    String getCourse() {
        JSONArray arr = new JSONArray();
        List<Course> list = courseDao.findCourse();
        for (Course c : list) {
            JSONObject sub = new JSONObject();
            sub.put("name", c.getName());
            sub.put("value", c.getValue());
            arr.add(sub);
        }
        return arr.toString();
    }  
}