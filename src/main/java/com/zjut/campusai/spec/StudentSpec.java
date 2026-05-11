package com.zjut.campusai.spec;

import com.zjut.campusai.entity.Student;
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
}
