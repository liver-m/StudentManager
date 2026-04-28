package com.zjut.campusai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String classroom;

    public Student(){
    }

    public Student( String name,int age, String classroom){
        this.age = age;
        this.name = name;
        this.classroom = classroom;
    }

    public Student(Long id, String name,int age, String classroom){
        this.id = id;
        this.age = age;
        this.name = name;
        this.classroom =classroom;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classroom='" + classroom + '\'' +
                '}';
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void setClassroom(String classroom){
        this.classroom = classroom;
    }

    public String getClassroom(){
        return this.classroom;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

}