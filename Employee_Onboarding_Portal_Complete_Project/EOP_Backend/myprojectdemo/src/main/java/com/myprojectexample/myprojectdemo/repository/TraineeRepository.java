package com.myprojectexample.myprojectdemo.repository;

import com.myprojectexample.myprojectdemo.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Trainee findByCandidateId(String candidateId);

}
