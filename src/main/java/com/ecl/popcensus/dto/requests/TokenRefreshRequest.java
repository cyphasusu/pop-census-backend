package com.ecl.popcensus.dto.requests;

import com.ecl.popcensus.dto.responses.Response;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TokenRefreshRequest{
      @NotNull
      private String refreshToken;
}
