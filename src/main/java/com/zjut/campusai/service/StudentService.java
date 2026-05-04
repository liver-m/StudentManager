package com.zjut.campusai.service;

import com.zjut.campusai.dto.StudentRequest;
import com.zjut.campusai.dto.WarningReport;
import com.zjut.campusai.entity.Course;
import com.zjut.campusai.entity.Student;
import com.zjut.campusai.entity.StudentCourse;
import com.zjut.campusai.entity.StudentCourseId;
import com.zjut.campusai.exception.CourseNotFoundException;
import com.zjut.campusai.exception.InvalidScoreException;
import com.zjut.campusai.exception.StudentCourseNotFoundException;
import com.zjut.campusai.exception.StudentNotFoundException;
import com.zjut.campusai.mapper.StudentMapper;
import com.zjut.campusai.repository.CourseRepository;
import com.zjut.campusai.repository.StudentCourseRepository;
import com.zjut.campusai.repository.StudentRepository;

import com.zjut.campusai.vo.StudentCourseVO;
import com.zjut.campusai.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          StudentCourseRepository studentCourseRepository,
                          CourseRepository courseRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
    }

    //查所有学生
    public Page<StudentVO> getAllStudents(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(studentMapper::toVO);
    }

    //按ID查单个学生
    public StudentVO getStudentById(Long id) {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isEmpty()) throw new StudentNotFoundException(id);
        else return studentMapper.toVO(result.get());
    }

    //按姓名查学生
    public StudentVO getStudentByName(String name) {
        Optional<Student> result = studentRepository.findByName(name);
        if (result.isEmpty()) throw new StudentNotFoundException(name);
        else return studentMapper.toVO(result.get());
    }

    //新增学生
    public StudentVO addStudent(StudentRequest request) {
        Student student = new Student(request.getName(),request.getAge(),request.getClassroom());
        return studentMapper.toVO(studentRepository.save(student));
    }

    //按ID删除学生
    public void deleteStudentById(Long id) {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isEmpty()) throw new StudentNotFoundException(id);
        else studentRepository.deleteById(id);
    }

    //修改学生信息
    public StudentVO updateStudent(Long id, StudentRequest request) {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isEmpty()) throw new StudentNotFoundException(id);
        else {
            Student student = new Student(id,request.getName(),request.getAge(),request.getClassroom());
            return studentMapper.toVO(studentRepository.save(student));
        }
    }

    //修改学生某一门课程的成绩
    public String updateScore(Long id, String courseName, int newScore) {
        if (newScore < 0 || newScore > 100) throw new InvalidScoreException("成绩不合适，应是0~100之间");

        Optional<Student> result = studentRepository.findById(id);
        if (result.isEmpty()) throw new StudentNotFoundException(id);

        Optional<Course> result2 = courseRepository.findByCourseName(courseName);
        if (result2.isEmpty()) throw new CourseNotFoundException("没有该课程");

        Student student = result.get();
        Course course = result2.get();

        Long studentId = student.getId();
        Long courseId = course.getId();
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);

        Optional<StudentCourse> result3 = studentCourseRepository.findById(studentCourseId);
        if (result3.isEmpty()) throw new StudentCourseNotFoundException("没有该学生这门课的选课记录");

        StudentCourse studentCourse = result3.get();
        studentCourse.setScore(newScore);
        studentCourseRepository.save(studentCourse);
        return "修改学生成绩成功，学生的" + course.getCourseName() + "课程成绩已被更新为：" + newScore;
    }

    //根据Id进行联表查询学生的选课成绩
    public List<StudentCourseVO> getStudentCourseScoreById(Long id) {
        Optional<Student> result1 = studentRepository.findById(id);
        if (result1.isEmpty()) throw new StudentNotFoundException(id);
        return studentCourseRepository.findCoursesByStudentId(id);
    }

    //查询学生哪些课程不及格，给学生推荐学习课程
    public String getCourseRecommendation(Long studentId) {
        List<StudentCourseVO> list = getStudentCourseScoreById(studentId);
        if (list.isEmpty()) throw new CourseNotFoundException("没有该学生的选课记录");

        StringBuilder sb = new StringBuilder();
        list.forEach(row -> {
            if (row.getScore() < 60) sb.append(String.format("%s - %s\n", row.getCourseName(), row.getScore()));
        });
        if (sb.isEmpty()) return "该学生没有不及格的课程";
        return sb.toString();
    }

    //查询学生的成绩情况，加以判断，并进行标识，以生成学习预警
    public WarningReport generateWarningReport(Long studentId) {
        List<StudentCourseVO> list = getStudentCourseScoreById(studentId);
        if (list.isEmpty()) throw new CourseNotFoundException("没有该学生的选课记录");

        StudentVO student = getStudentById(studentId);
        WarningReport warningReport;

        String riskLevel;
        int[] failAmount = {0};
        Map<String, Integer> courseScore = new HashMap<>();

        list.forEach(row -> {
            courseScore.put(row.getCourseName(), row.getScore());
            if (row.getScore() < 60) failAmount[0]++;
        });

        if (failAmount[0] == 0) riskLevel = "low";
        else if (failAmount[0] > 0 && failAmount[0] < 3) riskLevel = "medium";
        else riskLevel = "high";

        warningReport = new WarningReport(student.getName(), courseScore, failAmount[0], riskLevel);
        return warningReport;
    }
}

