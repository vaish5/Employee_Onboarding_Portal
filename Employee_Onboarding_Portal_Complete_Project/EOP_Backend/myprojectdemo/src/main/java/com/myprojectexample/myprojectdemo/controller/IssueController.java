package com.myprojectexample.myprojectdemo.controller;

import com.myprojectexample.myprojectdemo.model.Issue;
import com.myprojectexample.myprojectdemo.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @GetMapping("/issues")
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @PostMapping("/issues")
    public Issue createIssue(@RequestBody Issue issue) {
        return issueRepository.save(issue);
    }

}

