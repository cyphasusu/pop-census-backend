package com.ecl.popcensus.dto.mapping;

import com.ecl.popcensus.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class MapUser {
    public static User mapToUserEntity(User user)
    {
        if(user == null){
            return null;
        }

        var dto = new User();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastNames(user.getLastNames());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setCreatedBy(user.getCreatedBy());
        dto.setModifiedAt(user.getModifiedAt());
        dto.setModifiedBy(user.getModifiedBy());
        dto.setDeletedAt(user.getDeletedAt());
        dto.setModifiedBy(user.getDeletedBy());

        return dto;
    }

    public static List<User> mapToUserList(List<User> bodyTypes)
    {
        return bodyTypes.stream()
                .map(MapUser::mapToUserEntity)
                .collect(Collectors.toList());
    }
}
