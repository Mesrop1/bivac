package com.bivac.trainingsystem.controller;

import com.bivac.trainingsystem.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final GroupService groupService;

    public InstructorController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/{groupId}/instructor/{instructorId}")
    public ResponseEntity<?> updateInstructor(@PathVariable Long groupId, @PathVariable Long studentId) {
        return ResponseEntity.ok(groupService.updateInstructor(groupId, studentId));
    }
}
