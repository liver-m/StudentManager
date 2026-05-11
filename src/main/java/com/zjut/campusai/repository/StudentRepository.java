package com.zjut.campusai.repository;

import com.zjut.campusai.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {
    Optional<Student> findByName(String name);
    Specification<Student> spec =
            (root, query, builder) ->
                    builder.like(root.get("name"), "%qin%");
}
