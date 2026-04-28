package com.zjut.campusai.vo;

public class StudentCourseVO {
    private final String courseName;
    private final int score;

    public StudentCourseVO(String courseName, int score) {
        this.courseName = courseName;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getScore() {
        return score;
    }
}
