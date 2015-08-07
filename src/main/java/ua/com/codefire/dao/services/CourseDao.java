package ua.com.codefire.dao.services;

import java.util.List;
import ua.com.codefire.entity.Course;

public interface CourseDao {
    
    public void saveCourse(float USD, float EURO);
    public List<Course> findCourse();
    
}