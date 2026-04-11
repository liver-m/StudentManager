package com.zjut.student.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("没有找到学生，id："+id);
    }
}
