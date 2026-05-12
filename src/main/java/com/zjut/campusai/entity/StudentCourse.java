package com.zjut.campusai.entity;

import jakarta.persistence.*;

@Entity
public class StudentCourse {
    @EmbeddedId
    StudentCourseId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private int score;

    public StudentCourse() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
