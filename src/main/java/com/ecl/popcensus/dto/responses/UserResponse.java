package com.ecl.popcensus.dto.responses;


import com.ecl.popcensus.model.User;
import lombok.Data;


@Data
public class UserResponse extends Response {
    private User data;
}


