package com.bivac.trainingsystem.controller;

import com.bivac.trainingsystem.exception.StudentNotFoundException;
import com.bivac.trainingsystem.helper.CSVParser;
import com.bivac.trainingsystem.helper.ResponseMessage;
import com.bivac.trainingsystem.persistance.entity.Student;
import com.bivac.trainingsystem.service.StudentService;
import com.bivac.trainingsystem.service.dto.StudentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/parse/csv")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVParser.hasCSVFormat(file)) {
            try {
                studentService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping()
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return ResponseEntity.created(null).body(studentService.saveStudent(student));
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable("id") Long id) throws StudentNotFoundException {
        return studentService.getStudent(id);
    }
}
