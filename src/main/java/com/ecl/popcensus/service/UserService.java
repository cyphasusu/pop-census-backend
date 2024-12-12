package com.ecl.popcensus.service;

import com.ecl.popcensus.Filters.JwtTokenFilter;
import com.ecl.popcensus.dto.mapping.MapUser;
import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.responses.Metadata;
import com.ecl.popcensus.dto.responses.UserListResponse;
import com.ecl.popcensus.dto.responses.UserResponse;
import com.ecl.popcensus.model.User;
import com.ecl.popcensus.model.UserStatus;
import com.ecl.popcensus.repository.UserRepository;
import com.ecl.popcensus.util.Settings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.util.*;


@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest user) {
        UserResponse userResponse = new UserResponse();
        String authenticatedUser = JwtTokenFilter.authenticatedUser;
        Optional<User> u = userRepository.findUserByEmail(user.getEmail());

            if (u.isPresent()) {
                log.info("User with email {} already exists", user.getEmail());
                userResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
                userResponse.setResponseMessage("A user with this email already exists : " + user.getEmail());
            }else{
                try {
                    log.info("Creating new user...");
                    User userToSave = new User();
                    userToSave.setEmail(user.getEmail());
                    userToSave.setFirstName(user.getFirstName());
                    userToSave.setLastNames(user.getLastNames());
                    userToSave.setPhoneNumber(user.getPhoneNumber());
                    userToSave.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the raw password
                    userToSave.setRole(user.getRole());
                    userToSave.setUserStatus(UserStatus.New);
                    userToSave.setCreatedBy(user.getEmail());
                    userToSave.setModifiedBy(user.getEmail());
                    userToSave.setCreatedAt(new Date());
                    userToSave.setModifiedAt(new Date());
                    User savedUser = userRepository.save(userToSave);

                    userResponse.setData(savedUser);
                    userResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                    userResponse.setResponseMessage("User registration successful");

                    log.info("User with email {} has been created successfully", savedUser.getEmail());


                } catch (Exception ex) {
                    System.out.println("An error occurred while registering a user. Error : " + ex);
                    userResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
                    userResponse.setResponseMessage("An error occurred while registering a user. Error : " + ex);
                }
            }

        return userResponse;
    }


    public UserListResponse getUsers(int page, int size, String email) {
        log.info("Getting list of users");

        UserListResponse userListResponse = new UserListResponse();
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
            Specification<User> spec = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (email != null && !email.isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };

            var users = this.userRepository.findAll(spec, pageable);
            var metadata = new Metadata();
            metadata.setTotalCount(users.getTotalElements());
            metadata.setTotalPages(users.getTotalPages());
            metadata.setHasNextPage(users.hasNext());
            metadata.setHasPreviousPage(users.hasPrevious());
            metadata.setCurrentPage(users.getNumber());
            metadata.setPageSize(users.getSize());

            userListResponse.setMetadata(metadata);
            userListResponse.setData(MapUser.mapToUserList(users.getContent()));
            userListResponse.setCount((int) users.getTotalElements());
            userListResponse.setIsCollection(!users.isEmpty());
            userListResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            userListResponse.setResponseMessage("Successfully retrieved list of all users");
            log.info("Users fetched successfully");
            return userListResponse;
        }catch(Exception ex){
            userListResponse.setResponseMessage(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            userListResponse.setResponseMessage("An error occurred while fetch user list. Error : " + ex);
            return userListResponse;
        }

    }

    public UserResponse getUserById(Long userId){
        UserResponse userResponse = new UserResponse();
        try {
            User u = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("This user does not exist"));

            userResponse.setData(u);
            userResponse.setCount(1);
            userResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            userResponse.setResponseMessage("Successfully retrieved user");
            log.info("User with userId {} fetched successfully", userId);
        }catch (IllegalStateException illegalStateException){
            userResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            userResponse.setResponseMessage("An error occurred getting user by userId. Error : " + illegalStateException);
            log.info("An error occurred getting user by userId");
        }
        return userResponse;
    }

    public UserResponse deleteUser(Long userId) {
        UserResponse userResponse = new UserResponse();
        try {
            String authenticatedUser = JwtTokenFilter.authenticatedUser;
            User u = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("This user does not exist"));
            u.setDeletedAt(new Date());
            u.setDeletedBy(authenticatedUser);
            u.setUserStatus(UserStatus.Deactivated);
            u.setModifiedAt(new Date());
            u.setModifiedBy(authenticatedUser);

            userRepository.save(u);

            userResponse.setData(u);
            userResponse.setCount(1);
            userResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            userResponse.setResponseMessage("Successfully deleted user with id " +  userId);
            log.info("User with userId {} deleted successfully", userId);
        }catch (IllegalStateException illegalStateException){
            userResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            userResponse.setResponseMessage("An error occurred while deleting user with userId. Error : " + illegalStateException);
            log.error("An error occurred while deleting user with userId : " + userId);
        }

        return userResponse;
    }

    public UserResponse updateUser(Long userId, RegisterUserRequest user) {
        UserResponse userResponse = new UserResponse();
        String authenticatedUser = JwtTokenFilter.authenticatedUser;
        if(user.getFirstName() == null || user.getFirstName().isBlank()){
            log.info("User firstname cannot be empty");
            userResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
            userResponse.setResponseMessage("User firstname cannot be empty");
        }

        if(user.getEmail() == null || user.getEmail().isBlank()){
            log.info("User email cannot be empty");
            userResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
            userResponse.setResponseMessage("User email cannot be empty");
        }


        try {
            User u = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("This user does not exist"));

            if(!Objects.equals(user.getEmail(), u.getEmail())){
                if(this.userEmailExists(user)){
                    log.info("Error updating user. Email already taken");
                    userResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
                    userResponse.setResponseMessage("Error updating user. Email already taken");

                }
            }
            u.setFirstName(user.getFirstName());
            u.setLastNames(user.getLastNames());
            u.setRole(user.getRole());
            u.setEmail(user.getEmail());
            u.setPhoneNumber(user.getPhoneNumber());
            u.setModifiedBy(authenticatedUser);
            u.setModifiedAt(new Date());

            userRepository.save(u);

            userResponse.setData(u);
            userResponse.setCount(1);
            userResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            userResponse.setResponseMessage("User with id " + userId + " updated successfully");
            log.info("User with userId {} updated successfully", userId);

        }catch (IllegalStateException ex){
            userResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            userResponse.setResponseMessage("An error occurred updating user with userId. Error : " + ex);
            log.info("An error occurred updating user with userId : " + userId);
        }

        return userResponse;
    }


    private boolean userEmailExists(RegisterUserRequest user){
        log.info("Checking if user exists...");
        try {
            User exists = userRepository.findUserByEmail(user.getEmail()).orElseThrow(() -> new IllegalStateException("This user does not exist"));
            return exists != null;
        }catch (IllegalStateException illegalStateException){
            log.info("An error occurred checking if user exists");
            return false;
        }

    }


}
