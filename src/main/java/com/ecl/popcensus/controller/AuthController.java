package com.ecl.popcensus.controller;

import com.ecl.popcensus.dto.requests.JWTRequest;
import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.requests.TokenRefreshRequest;
import com.ecl.popcensus.dto.responses.JWTResponse;
import com.ecl.popcensus.dto.responses.UserResponse;
import com.ecl.popcensus.model.User;
import com.ecl.popcensus.service.AuthService;
import com.ecl.popcensus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.ecl.popcensus.dto.responses.MessageResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Login", description = "Send a POST request to login. Receive JWT accessToken to access API resources")
    @PostMapping("/login")
    public JWTResponse login(@Valid @RequestBody JWTRequest request) throws Exception {
        return  authService.login(request);
    }

    @Operation(summary = "Refresh Token", description = "Send a POST request to refresh JWT accessToken when expired.")
    @PostMapping(path="/refresh-token",produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> refresh(@Valid @RequestBody TokenRefreshRequest request){
        return  authService.refresh(request);
    }

}

