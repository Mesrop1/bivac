package com.bivac.trainingsystem.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGroupDto {

    private StudentDto studentDto;
    private GroupDto groupDto;

    public StudentGroupDto(StudentDto studentDto, GroupDto groupDto) {
        this.studentDto = studentDto;
        this.groupDto = groupDto;
    }
}
