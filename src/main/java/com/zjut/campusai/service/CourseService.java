package com.zjut.campusai.service;

import com.zjut.campusai.entity.Course;
import com.zjut.campusai.repository.CourseRepository;

import com.zjut.campusai.spec.CourseSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //获取所有课程信息（待优化：以后需要改为CourseVO）
    public Page<Course> getAllCourses(String courseName, Integer minCredit, Integer maxCredit, Pageable pageable){
        Specification<Course> spec = Specification
                .where(CourseSpec.hasName(courseName))
                .and(CourseSpec.minCredit(minCredit))
                .and(CourseSpec.maxCredit(maxCredit));
        return courseRepository.findAll(spec, pageable);
    }

    
}
