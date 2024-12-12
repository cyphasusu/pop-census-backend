package com.ecl.popcensus.dto.responses;

import com.ecl.popcensus.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserListResponse extends Response{
    private List<User> data;
}
