package com.zjut.campusai.spec;

import com.zjut.campusai.entity.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpec {

    public static Specification<Course> hasName(String name){
        return (root, query, cb) -> {
            if(name == null || name.isBlank())return null;
            return cb.like(root.get("courseName"), "%" + name + "%");
        };
    }

    public static Specification<Course> minCredit(Integer credit){
        return (root, query, cb) -> {
            if(credit == null)return null;
            return cb.greaterThanOrEqualTo(root.get("credit"), credit);
        };
    }

    public static Specification<Course> maxCredit(Integer credit){
        return (root, query, cb) -> {
            if(credit == null)return null;
            return cb.lessThanOrEqualTo(root.get("credit"), credit);
        };
    }
}
