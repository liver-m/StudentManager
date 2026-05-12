package com.zjut.campusai.spec;

import com.zjut.campusai.entity.Course;
import com.zjut.campusai.entity.Student;
import com.zjut.campusai.entity.StudentCourse;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpec {

    public static Specification<Student> hasName(String name){
        return (root,query,cb) -> {

            if(name == null || name.isBlank())return null;
            return cb.like(root.get("name"), "%" + name + "%");

        };
    }

    public static Specification<Student> hasClassroom(String classroom){
        return (root, query, cb) -> {

            if(classroom == null || classroom.isBlank())return null;
            return cb.like(root.get("classroom"), "%" + classroom + "%");
        };
    }

    public static Specification<Student> minAge(Integer age){
        return (root, query, cb) -> {
            if(age == null)return null;
            return cb.greaterThanOrEqualTo(root.get("age"), age);
        };
    }

    public static Specification<Student> maxAge(Integer age){
        return (root, query, cb) -> {
            if(age == null)return null;
            return cb.lessThanOrEqualTo(root.get("age"), age);
        };
    }

    public static Specification<Student> hasCourseName(String courseName){
        return(root, query, cb) -> {
            Join<Student, StudentCourse> scJoin = root.join("studentCourses");
            Join<StudentCourse, Course> courseJoin = scJoin.join("course");
            return cb.like(courseJoin.get("courseName"), "%" + courseName + "%");
        };
    }
}
