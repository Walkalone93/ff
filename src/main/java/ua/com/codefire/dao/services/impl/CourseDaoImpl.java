package ua.com.codefire.dao.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.dao.repositories.CourseRepository;
import ua.com.codefire.dao.services.CourseDao;
import ua.com.codefire.dao.services.CourseDao;
import ua.com.codefire.entity.Course;

@Transactional
@Controller("courseDao")
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void saveCourse(float U, float E) {
        courseRepository.saveCourse("USD", U);
        courseRepository.saveCourse("EURO", E);
    } 

    @Override
    public List<Course> findCourse() {
        return courseRepository.findAll();
    }
    
}