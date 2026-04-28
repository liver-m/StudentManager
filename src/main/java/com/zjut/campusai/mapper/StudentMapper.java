package com.zjut.campusai.mapper;

import com.zjut.campusai.entity.Student;
import com.zjut.campusai.vo.StudentVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentVO toVO(Student student);
}
