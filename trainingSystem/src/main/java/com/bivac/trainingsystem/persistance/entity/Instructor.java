package com.bivac.trainingsystem.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instructorName;

    private String email;

    @ManyToMany(mappedBy = "instructors")
    List<Student> students = new ArrayList<>();

    @OneToOne(mappedBy = "instructor", cascade = CascadeType.ALL)
    private Group group;
}
