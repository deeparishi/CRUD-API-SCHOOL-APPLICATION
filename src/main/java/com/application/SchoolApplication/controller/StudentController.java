package com.application.SchoolApplication.controller;

import com.application.SchoolApplication.entity.Student;
import com.application.SchoolApplication.iservice.IStudentService;
import com.application.SchoolApplication.request.StudentRequest;
import com.application.SchoolApplication.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    IStudentService iStudentService;

    @PostMapping("/create")
    public StudentResponse createStundent(@RequestBody StudentRequest request) {
        System.out.println("hello");
        return iStudentService.createStundent(request);
    }

    @PostMapping("/createlist")
    public List<StudentResponse> createListOfStudents(@RequestBody List<StudentRequest> studentRequests) {
        return iStudentService.createListOfStudents(studentRequests);
    }

    @GetMapping("/fetchAll")
    public List<Student> fetchAll() {
        return iStudentService.fetchAllStudent();
    }

    @GetMapping("/fetch/{name}")
    public List<Student> fetchByStudentName(@PathVariable String name) {
        return iStudentService.fetchByStudentName(name);
    }

    @GetMapping("/fetchAndSortByName/{order}")
    public List<Student> fetchAndSortByName(@PathVariable String order) {
        if (order.equalsIgnoreCase("DESC")) {
            return iStudentService.fetchAndSortByNameDesc();
        } else if (order.equalsIgnoreCase("ASC")) {
            return iStudentService.fetchAndSortByNameByAsc();
        }
        return Collections.emptyList();
    }

    @GetMapping("/fetchByKeyAndValue")
    public Map<String, List<StudentRequest>> fetchByKeyAndValue() {
        return iStudentService.fetchByKeyAndValue();
    }

    @PutMapping("updateAge/{name}/{age}")
    public StudentResponse updateAge(@PathVariable String name, @PathVariable int age) {
        return iStudentService.updateAge(name, age);
    }

    @PatchMapping("/updateAllAge")
    public List<StudentResponse> updateAll(@RequestBody List<StudentRequest> studentRequest) {
        return iStudentService.updateAll(studentRequest);
    }

    @DeleteMapping("/deletebyName/{name}")
    public StudentResponse deleteByName(@PathVariable String name){
        return iStudentService.deleteByName(name);
    }
}
