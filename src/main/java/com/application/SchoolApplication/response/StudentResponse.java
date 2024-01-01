package com.application.SchoolApplication.response;

import lombok.Data;

@Data
public class StudentResponse {
    private long id;
    private String studentName;
    private String className;
    private int studentAge;
    private String classTeacherName;
}
