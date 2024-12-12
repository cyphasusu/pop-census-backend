package com.ecl.popcensus.dto.responses;

import com.ecl.popcensus.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class JWTResponse extends UserResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType="Bearer";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private Date  expirationDateTime;
}


