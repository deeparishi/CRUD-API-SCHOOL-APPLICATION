package com.application.SchoolApplication.repository;

import com.application.SchoolApplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
   public List<Student> findByStudentName(String name);
   public Student findOutByStudentName(String name);
@Query(value = "select * from student order by student_name DESC", nativeQuery = true)
   public List<Student> fetchAndSortByNameByDesc();
   @Query(value = "select * from student order by student_name ASC", nativeQuery = true)
   List<Student> findAllByOrderByNameAsc();
}
