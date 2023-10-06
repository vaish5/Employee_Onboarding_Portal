package com.myprojectexample.myprojectdemo.controller;

import com.myprojectexample.myprojectdemo.model.Trainee;
import com.myprojectexample.myprojectdemo.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TraineeController {

    @Autowired
    private TraineeRepository traineeRepository;

    @PostMapping("/trainee")
    Trainee newTrainee(@RequestBody Trainee newTrainee){
        return traineeRepository.save(newTrainee);
    }

    @GetMapping("/trainees")
    List<Trainee> getAllTrainees(){
        return traineeRepository.findAll();
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Trainee trainee) {
        Trainee existingTrainee = traineeRepository.findByCandidateId(trainee.getCandidateId());

        if (existingTrainee != null && existingTrainee.getPassword().equals(trainee.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
