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

    @Tool("根据学生id修改学生的成绩，成绩只能在0~100之间。")
    public String updateScore(Long studentId, int newScore){
        if(newScore < 0 ||newScore > 100)return "成绩不合适，应是0~100之间";
        Student s;
        try{
             s = studentService.updateScore(studentId,newScore);
        }catch(Exception e){
            return "不存在id为" + studentId + "的学生";
        }
        return s.toString();
    }
}
