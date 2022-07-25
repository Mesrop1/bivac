package com.bivac.trainingsystem.service.dto;

import com.bivac.trainingsystem.persistance.entity.Instructor;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {
    private String instructorName;

    private String email;

    public static InstructorDto mapInstructorEntityToInstructorDto(Instructor instructor) {
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setInstructorName(instructor.getInstructorName());
        instructorDto.setEmail(instructor.getEmail());
        return instructorDto;
    }

    public static List<InstructorDto> mapInstructorEntityToInstructorDto(List<Instructor> entities) {
        return entities.stream().map(InstructorDto::mapInstructorEntityToInstructorDto).collect(Collectors.toList());
    }

    public static Instructor mapInstructorDtoToInstructorEntity(InstructorDto instructorDto) {
        Instructor instructor = new Instructor();
        instructor.setInstructorName(instructorDto.getInstructorName());
        instructor.setEmail(instructorDto.getEmail());
        return instructor;
    }
}
