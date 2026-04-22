package com.zjut.campusai.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("没有找到学生，id："+id);
    }

    public StudentNotFoundException(String name){
        super("没有找到学生，姓名："+name);
    }
}
