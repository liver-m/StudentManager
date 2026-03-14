package com.zjut.student;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(20,"刘备","中文201班");
        StudentService service = new StudentService();
        service.addStudent(student);

        service.deleteStudent(16);

        Student student2 = new Student(16,19,"张飞","中文201班");
        service.updateStudent(student2);

        List<Student> students = service.getAllStudents();
        for(Student stu : students){
            System.out.println("ID: " + stu.getId() +
                    ", 姓名: " + stu.getName() +
                    ", 年龄: " + stu.getAge() +
                    ", 班级: " + stu.getClassroom());
        }

        int existingId = 13;
        Student student1 = service.getStudentById(existingId);
        if (student1 != null) {
            System.out.println("ID: " + student1.getId() +
                    ", 姓名: " + student1.getName() +
                    ", 年龄: " + student1.getAge() +
                    ", 班级: " + student1.getClassroom());
        }else{
            System.out.println("不存在ID为"+existingId+"的学生");
        }

        int nonExistingId = 999;
        Student notFound = service.getStudentById(nonExistingId);
        if(notFound == null){
            System.out.println("\n测试二：未找到ID为 "+ nonExistingId + "的学生，功能正常！");
        }


    }
}