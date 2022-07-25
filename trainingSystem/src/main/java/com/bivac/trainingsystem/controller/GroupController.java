package com.bivac.trainingsystem.controller;

import com.bivac.trainingsystem.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<?> addStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        return ResponseEntity.ok(groupService.addStudentToGroup(groupId, studentId));
    }

    @DeleteMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<?> deleteStudentFromGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        groupService.deleteStudentFromGroup(groupId, studentId);
        return new ResponseEntity<>((HttpStatus.NO_CONTENT));
    }
}
