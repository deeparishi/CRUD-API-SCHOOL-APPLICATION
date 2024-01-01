package com.application.SchoolApplication.request;

import lombok.Data;

@Data
public class StudentRequest {


    private String studentName;
    private String className;
    private int studentAge;
    private String classTeacherName;
}
