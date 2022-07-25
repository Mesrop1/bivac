package com.bivac.trainingsystem.service;

import com.bivac.trainingsystem.exception.*;
import com.bivac.trainingsystem.persistance.entity.Group;
import com.bivac.trainingsystem.persistance.entity.Instructor;
import com.bivac.trainingsystem.persistance.entity.Student;
import com.bivac.trainingsystem.persistance.repository.GroupRepository;
import com.bivac.trainingsystem.persistance.repository.InstructorRepository;
import com.bivac.trainingsystem.persistance.repository.StudentRepository;
import com.bivac.trainingsystem.service.dto.GroupDto;
import com.bivac.trainingsystem.service.dto.InstructorDto;
import com.bivac.trainingsystem.service.dto.StudentDto;
import com.bivac.trainingsystem.service.dto.StudentGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
    }

    @Autowired
    private EmailService emailService;

    @Transactional
    public StudentGroupDto addStudentToGroup(Long groupId, Long studentId) {
        Group group = groupRepository.findWithStudentsAndInstructor(groupId)
                .orElseThrow(() ->
                        new GroupNotFoundException(String.format("Group not found with id: %d", groupId)));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(String.format("Group not found with id: %d", studentId)));

        Set<Student> groupStudents = group.getStudents();

        if (groupStudents.contains(student)) {
            throw new StudentAlreadyInGroupException(
                    String.format("Group with id: %d already contains student with id: %d", groupId, studentId)
            );
        }
        if (groupStudents.size() == 10) {
            throw new GroupLimitException("Group already has 10 students");
        }
        if (student.getGroups().size() == 5) {
            throw new StudentInGroupLimitException("Student already has 5 groups");
        }
        group.addStudent(student);
        Instructor groupInstructor = group.getInstructor();

        //Send notification to instructor
        if (groupInstructor != null) {
            emailService.sendSimpleEmail(groupInstructor.getEmail(),
                    "Notification",
                    String.format("Student with id: %d : added to the group with id: %d", studentId, groupId)
            );
        }
        return new StudentGroupDto(
                StudentDto.mapStudentEntityToStudentDto(student),
                GroupDto.mapGroupEntityToGroupDto(group)
        );
    }

    @Transactional
    public void deleteStudentFromGroup(Long groupId, Long studentId) {
        Group group = groupRepository.findWithStudentsAndInstructor(groupId)
                .orElseThrow(() ->
                        new GroupNotFoundException(String.format("Group not found with id: %d", groupId)));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(String.format("Group not found with id: %d", studentId)));

        Set<Student> groupStudents = group.getStudents();

        if (!groupStudents.contains(student)) {
            throw new StudentAlreadyInGroupException(
                    String.format("Group with id: %d already contains student with id: %d", groupId, studentId)
            );
        }
        group.removeStudent(student);

        Instructor groupInstructor = group.getInstructor();

        //Send notification to instructor
        CompletableFuture.runAsync(() -> emailService.sendSimpleEmail(groupInstructor.getEmail(),
                "Notification",
                String.format("Student with id: %d : deleted from the group with id: %d", studentId, groupId)
        ));
    }

    @Transactional
    public InstructorDto updateInstructor(Long groupId, Long instructorId) {
        Group group = groupRepository.findWithStudentsAndInstructor(groupId)
                .orElseThrow(() ->
                        new GroupNotFoundException(String.format("Group not found with id: %d", groupId)));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() ->
                        new StudentNotFoundException(String.format("Instructor not found with id: %d", instructorId)));
        Instructor groupInstructor = group.getInstructor();
        if (groupInstructor != null) {
            group.setInstructor(instructor);
        }
        return
                new InstructorDto();
    }
}
