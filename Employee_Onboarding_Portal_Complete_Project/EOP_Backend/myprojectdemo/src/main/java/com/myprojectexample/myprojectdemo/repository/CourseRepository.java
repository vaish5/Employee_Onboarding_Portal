package com.myprojectexample.myprojectdemo.repository;

import com.myprojectexample.myprojectdemo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    long countByCompleted(boolean completed);
}
