package com.application.SchoolApplication.service;

import com.application.SchoolApplication.entity.Student;
import com.application.SchoolApplication.iservice.IStudentService;
import com.application.SchoolApplication.repository.StudentRepo;
import com.application.SchoolApplication.request.StudentRequest;
import com.application.SchoolApplication.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService implements IStudentService {


    @Autowired
    StudentRepo studentRepos;
    public StudentResponse createStundent(StudentRequest request) {
        System.out.println("hi i am service");
        Student student = requestToEntity(request);
        studentRepos.save(student);
        return entityToResponse(student);
    }

    @Override
    public List<StudentResponse> createListOfStudents(List<StudentRequest> studentRequests) {
        List<StudentResponse> responses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        for (StudentRequest req : studentRequests){
            Student student = requestToEntity(req);
            students.add(student);
        }
        studentRepos.saveAll(students);
        for (Student stud : students){
            StudentResponse response = entityToResponse(stud);
            responses.add(response);
        }
        return responses;

    }
    private StudentResponse entityToResponse(Student student) {
        StudentResponse studentresponse = new StudentResponse();
        studentresponse.setId(student.getId());
        studentresponse.setStudentName(student.getStudentName());
        studentresponse.setClassName(student.getClassName());
        studentresponse.setStudentAge(student.getStudentAge());
        studentresponse.setClassTeacherName(student.getClassTeacherName());
        return studentresponse;
    }

    private Student requestToEntity(StudentRequest request) {
        Student student = new Student();
        student.setStudentName(request.getStudentName());
        student.setStudentAge(request.getStudentAge());
        student.setClassName(request.getClassName());
        student.setClassTeacherName(request.getClassTeacherName());
        return student;
    }

    public List<Student> fetchAllStudent(){
        return studentRepos.findAll();
    }

    @Override
    public List<Student> fetchByStudentName(String name){
        return studentRepos.findByStudentName(name);
    }

    @Override
    public List<Student> fetchAndSortByNameDesc() {
        return studentRepos.fetchAndSortByNameByDesc();
    }

    @Override
    public List<Student> fetchAndSortByNameByAsc() {
       return studentRepos.findAllByOrderByNameAsc();
    }

    @Override
    public Map<String,List<StudentRequest>> fetchByKeyAndValue(){

        Map<String,List<StudentRequest>> list = new HashMap<>();
        List<Student> all = studentRepos.findAll();
        for(Student s : all) {
            list.put(s.getStudentName(),entityToResponses(s));
        }
        return list;
    }

    @Override
    public StudentResponse updateAge(String name, int age) {
        Student student = studentRepos.findOutByStudentName(name);

        if (student != null) {
            student.setStudentAge(age);
            studentRepos.save(student);
        }
        return entityToResponse(student);
    }

    @Override
    public List<StudentResponse> updateAll(List<StudentRequest> studentRequest) {
        List <StudentResponse> responses = new ArrayList<>();

        for (StudentRequest request : studentRequest){
            Student student = studentRepos.findOutByStudentName(request.getStudentName());
            student.setStudentAge(request.getStudentAge());
            responses.add(entityToResponse(student));
            studentRepos.save(student);
        }
        return responses;
    }

    @Override
    public StudentResponse deleteByName(String name) {
        Student student = studentRepos.findOutByStudentName(name);
        if(student!=null) {
            StudentResponse response = entityToResponse(student);
            studentRepos.delete(student);
            return response;
        }
        return null;
    }

    public List<StudentRequest> entityToResponses(Student s) {
        List<StudentRequest> data = new ArrayList();
        StudentRequest sdm = new StudentRequest();
        sdm.setClassName(s.getClassName());
        sdm.setStudentAge(s.getStudentAge());
        sdm.setClassTeacherName(s.getClassTeacherName());
        data.add(sdm);
        return data;
    }

    public List<Student> findStudentWithSorting(String field){
        return studentRepos.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Student> getStudentWithPagination(int offset, int pageSize,String field){
        Sort sort = Sort.by(Sort.Direction.ASC,field);
        Pageable page = PageRequest.of(offset,pageSize,sort);
        return studentRepos.findAll(page);
    }
}
