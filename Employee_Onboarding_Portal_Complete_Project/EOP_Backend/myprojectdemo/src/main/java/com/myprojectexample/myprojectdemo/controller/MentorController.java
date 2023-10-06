package com.myprojectexample.myprojectdemo.controller;

import com.myprojectexample.myprojectdemo.model.Mentor;
import com.myprojectexample.myprojectdemo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class MentorController {

    @Autowired
    private MentorRepository mentorRepository;

    @PostMapping("/mentor")
    Mentor newMentor(@RequestBody Mentor newMentor){
        return mentorRepository.save(newMentor);
    }

    @PostMapping("/mentor-login")
    public ResponseEntity<String> login(@RequestBody Mentor mentor) {
        Mentor existingMentor = mentorRepository.findByEmployeeId(mentor.getEmployeeId());

        if (existingMentor != null && existingMentor.getPassword().equals(mentor.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
