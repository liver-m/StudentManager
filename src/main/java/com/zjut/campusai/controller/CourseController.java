package com.zjut.campusai.controller;

import com.zjut.campusai.common.ApiResponse;
import com.zjut.campusai.common.PageResult;
import com.zjut.campusai.entity.Course;
import com.zjut.campusai.service.CourseService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ApiResponse<PageResult<Course>> getAllCourses(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer minCredit,
            @RequestParam(required = false) Integer maxCredit,
            Pageable pageable){
        return ApiResponse.success(PageResult.of(courseService.getAllCourses(courseName,minCredit,maxCredit,pageable)));
    }
}
