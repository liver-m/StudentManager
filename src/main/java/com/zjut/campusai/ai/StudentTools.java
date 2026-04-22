package com.zjut.campusai.ai;

import com.zjut.campusai.Student;
import com.zjut.campusai.service.StudentService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentTools {
    private final StudentService studentService;
    @Autowired
    public StudentTools(StudentService studentService){
        this.studentService = studentService;
    }

    @Tool("根据学生姓名查询学生信息")
    public Student getStu(String name){
        return studentService.getStudentByName(name);
    }
}
