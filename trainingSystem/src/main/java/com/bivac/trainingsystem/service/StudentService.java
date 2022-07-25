package com.bivac.trainingsystem.service;

import com.bivac.trainingsystem.exception.StudentNotFoundException;
import com.bivac.trainingsystem.helper.CSVParser;
import com.bivac.trainingsystem.persistance.entity.Student;
import com.bivac.trainingsystem.persistance.repository.StudentRepository;
import com.bivac.trainingsystem.service.dto.StudentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CSVParser csvParser;

    public StudentService(StudentRepository studentRepository, CSVParser csvParser) {
        this.studentRepository = studentRepository;
        this.csvParser = csvParser;
    }

    public List<Student> save(MultipartFile file) throws IOException {
        List<Student> students = csvParser.parse(file.getInputStream());
        return studentRepository.saveAll(students);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public StudentDto getStudent(Long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return StudentDto.mapStudentEntityToStudentDto(student);
    }
}
