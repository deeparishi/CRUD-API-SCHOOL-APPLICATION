package com.application.SchoolApplication.iservice;

import com.application.SchoolApplication.entity.Student;
import com.application.SchoolApplication.request.StudentRequest;
import com.application.SchoolApplication.response.StudentResponse;

import java.util.List;
import java.util.Map;

public interface IStudentService {
    public StudentResponse createStundent(StudentRequest request);
     public List<StudentResponse> createListOfStudents(List<StudentRequest> studentRequests);

   public List<Student> fetchAllStudent();

   public List<Student> fetchByStudentName(String name);

    List<Student> fetchAndSortByNameDesc();

    List<Student> fetchAndSortByNameByAsc();


    Map<String, List<StudentRequest>> fetchByKeyAndValue();

    StudentResponse updateAge(String name, int age);

    List<StudentResponse> updateAll(List<StudentRequest> studentRequest);

    StudentResponse deleteByName(String name);
}
