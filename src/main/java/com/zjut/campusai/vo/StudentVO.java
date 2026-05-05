package com.zjut.campusai.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentVO {
    private final Long id;
    private final String name;
    private final String classroom;

    @JsonCreator
    public StudentVO(@JsonProperty("id") Long id,
                     @JsonProperty("name") String name,
                     @JsonProperty("classroom") String classroom) {
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
