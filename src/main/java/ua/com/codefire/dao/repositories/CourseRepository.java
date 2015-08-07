package ua.com.codefire.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.codefire.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Modifying
    @Query(value = "REPLACE INTO Course (name, value) VALUES (:name, :value)", nativeQuery = true)
    public void saveCourse(@Param("name") String name,
            @Param("value") float value);

}