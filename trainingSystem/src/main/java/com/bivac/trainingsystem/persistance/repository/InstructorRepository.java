package com.bivac.trainingsystem.persistance.repository;

import com.bivac.trainingsystem.persistance.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
