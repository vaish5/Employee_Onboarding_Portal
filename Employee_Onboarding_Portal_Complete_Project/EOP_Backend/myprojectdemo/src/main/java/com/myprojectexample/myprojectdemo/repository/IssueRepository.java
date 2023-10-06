package com.myprojectexample.myprojectdemo.repository;

import com.myprojectexample.myprojectdemo.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}