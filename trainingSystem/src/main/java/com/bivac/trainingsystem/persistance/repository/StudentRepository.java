package com.bivac.trainingsystem.persistance.repository;

import com.bivac.trainingsystem.persistance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
