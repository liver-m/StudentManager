package com.zjut.campusai.vo;

public class StudentVO {
    private final Long id;
    private final String name;
    private final String classroom;

    public StudentVO(Long id, String name, String classroom) {
        this.id = id;
        this.name = name;
        this.classroom = classroom;
    }

    public Long getId() {
        return id;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getName() {
        return name;
    }
}
