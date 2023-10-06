package com.myprojectexample.myprojectdemo.controller;

import com.myprojectexample.myprojectdemo.model.Course;
import com.myprojectexample.myprojectdemo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/upload-course")
    public ResponseEntity<?> uploadCourse(@RequestParam("courseName") String courseName,
                                          @RequestParam("courseFile") MultipartFile courseFile) throws IOException {

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseFile(courseFile.getBytes());

        courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.OK).body("Course uploaded successfully");
    }

    @GetMapping("/all-courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(courseDtos);
    }

    @GetMapping("/completed-courses-count")
    public ResponseEntity<Long> getCompletedCoursesCount() {
        Long count = courseRepository.countByCompleted(true);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @GetMapping("/incomplete-courses-count")
    public ResponseEntity<Long> getIncompleteCoursesCount() {
        Long count = courseRepository.countByCompleted(false);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @PatchMapping("/mark-as-completed/{id}")
    public ResponseEntity<?> markCourseAsCompleted(@PathVariable Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setCompleted(true);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body("Course marked as completed");
    }

    @PatchMapping("/mark-as-incomplete/{id}")
    public ResponseEntity<?> markCourseAsIncomplete(@PathVariable Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setCompleted(false);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body("Course marked as incomplete");
    }

    @GetMapping("/get-video/{id}")
    public ResponseEntity<byte[]> getVideo(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getCourseName() + ".mp4\"")
                .body(course.getCourseFile());
    }

    public static class CourseDto {
        private Long id;
        private String courseName;
        private Boolean completed;

        public CourseDto(Course course) {
            this.id = course.getId();
            this.courseName = course.getCourseName();
            this.completed = course.isCompleted();
        }

        public Long getId() {
            return id;
        }

        public String getCourseName() {
            return courseName;
        }

        public boolean isCompleted() {
            return completed;
        }
    }
}
