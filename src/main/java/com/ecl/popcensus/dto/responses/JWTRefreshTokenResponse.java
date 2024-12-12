package com.ecl.popcensus.dto.responses;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class JWTRefreshTokenResponse extends  MessageResponse{

    @Nullable
    private String jwtToken;
}
