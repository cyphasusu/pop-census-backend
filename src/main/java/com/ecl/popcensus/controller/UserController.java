package com.ecl.popcensus.controller;


import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.responses.Response;
import com.ecl.popcensus.dto.responses.UserListResponse;
import com.ecl.popcensus.dto.responses.UserResponse;
import com.ecl.popcensus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/users")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Fetch all users", description = "Send a GET request to fetch all user list")
    @GetMapping
    public UserListResponse getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String email
    ) {
        log.info("Received request to get list of users");
        return this.userService.getUsers(page, size, email);
    }

    @GetMapping(path = "{userId}")
    public UserResponse getUser(
            @PathVariable("userId") Long userId)
    {
        log.info("Received request to get user with userId : " + userId);
        return this.userService.getUserById(userId);
    }

    @Operation(summary = "Register a user", description = "Send a POST request to register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody RegisterUserRequest user)
    {
        UserResponse response = userService.registerUser(user);

        if (response == null) return ResponseEntity.badRequest().body(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "{userId}")
    public UserResponse deleteUser(
            @PathVariable("userId") Long userId)
    {
        log.info("Received request to delete user with userId : " + userId);
        return this.userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public UserResponse updateUser(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody RegisterUserRequest user)
    {
        log.info("Received request to update user with userId : " + userId);
        return this.userService.updateUser(userId, user);
    }



}
