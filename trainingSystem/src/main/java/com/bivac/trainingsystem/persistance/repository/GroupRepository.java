package com.bivac.trainingsystem.persistance.repository;

import com.bivac.trainingsystem.persistance.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g " +
            "from Group g " +
            "left join fetch g.students " +
            "left join fetch g.instructor " +
            "where g.id = :id")
    Optional<Group> findWithStudentsAndInstructor(Long id);

}
