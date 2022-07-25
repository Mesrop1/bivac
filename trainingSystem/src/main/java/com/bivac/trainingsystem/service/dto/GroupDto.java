package com.bivac.trainingsystem.service.dto;

import com.bivac.trainingsystem.persistance.entity.Group;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private int capacity;

    public static GroupDto mapGroupEntityToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setCapacity(group.getCapacity());
        return groupDto;
    }

    public static List<GroupDto> mapGroupEntityToGroupDto(List<Group> entities) {
        return entities.stream().map(GroupDto::mapGroupEntityToGroupDto).collect(Collectors.toList());
    }

    public static Group mapGroupDtoToGroupEntity(GroupDto groupDto) {
        Group group = new Group();
        group.setCapacity(groupDto.getCapacity());
        return group;
    }
}
