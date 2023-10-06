package com.myprojectexample.myprojectdemo.repository;

import com.myprojectexample.myprojectdemo.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Mentor findByEmployeeId(String employeeId);

}
