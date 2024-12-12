package com.ecl.popcensus.dto.requests;


import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class JWTRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
