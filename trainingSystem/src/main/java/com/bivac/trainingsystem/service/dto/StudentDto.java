package com.bivac.trainingsystem.service.dto;

import com.bivac.trainingsystem.persistance.entity.Student;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String firstName;

    private String lastName;

    private int age;

    public static StudentDto mapStudentEntityToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setAge(student.getAge());
        return studentDto;
    }

    public static List<StudentDto> mapStudentEntityToStudentDto(List<Student> entities) {
        return entities.stream().map(StudentDto::mapStudentEntityToStudentDto).collect(Collectors.toList());
    }

    public static Student mapStudentDtoToStudentEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        return student;
    }
}
