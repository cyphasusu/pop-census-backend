package com.ecl.popcensus.service;

import com.ecl.popcensus.dto.requests.TokenRefreshRequest;
import com.ecl.popcensus.dto.responses.JWTRefreshTokenResponse;
import com.ecl.popcensus.dto.responses.JWTResponse;
import com.ecl.popcensus.dto.responses.MessageResponse;
import com.ecl.popcensus.dto.requests.JWTRequest;
import com.ecl.popcensus.model.RefreshToken;
import com.ecl.popcensus.model.User;
import com.ecl.popcensus.repository.UserRepository;
import com.ecl.popcensus.Filters.JWTUtility;
import com.ecl.popcensus.util.Settings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private final JWTUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTUtility jwtUtility;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthService(JWTUserDetailsService jwtUserDetailsService,
                       AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       JWTUtility jwtUtility,
                       RefreshTokenService refreshTokenService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtility = jwtUtility;
        this.refreshTokenService = refreshTokenService;
    }

    public JWTResponse login(JWTRequest request){
        //create the response
        JWTResponse jwtResponse=new JWTResponse();
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetail = (UserDetails) authenticate.getPrincipal();

            User user = userRepository.findUserByEmail(userDetail.getUsername()).orElseThrow(() -> new IllegalStateException("This user does not exist"));

            final String accessToken =jwtUtility.generateAccessToken(user);


            //create new refresh token
            RefreshToken refreshToken=refreshTokenService.createRefreshToken();

            if(user!=null && user.getRefreshTokens().size()>0){
                user.getRefreshTokens().get(0).setToken(refreshToken.getToken());
                user.getRefreshTokens().get(0).setRefreshCount(refreshToken.getRefreshCount());
                user.getRefreshTokens().get(0).setExpiryDate(refreshToken.getExpiryDate());

                refreshToken=user.getRefreshTokens().get(0);
            }
            else
                refreshToken.setUser(user);

            refreshToken=refreshTokenService.save(refreshToken);

            jwtResponse.setData(user);
            jwtResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            jwtResponse.setAccessToken(accessToken);
            jwtResponse.setRefreshToken(refreshToken.getToken());
            jwtResponse.setExpirationDateTime(refreshToken.getExpiryDate());
            jwtResponse.setResponseMessage(Settings.getInstance("").getProperty("LOGIN_SUCCESS_MSG"));


        } catch (BadCredentialsException e) {
            //throw new Exception("Invalid credentials",e);
            jwtResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
            jwtResponse.setResponseMessage(Settings.getInstance("").getProperty("LOGIN_FAIL_MSG"));
        }
        return  jwtResponse;
    }

    public ResponseEntity<MessageResponse> refresh(TokenRefreshRequest tokenRefreshRequest){
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        //create the response
        JWTRefreshTokenResponse jwtResponse=new JWTRefreshTokenResponse();

        //validate empty refresh token
        if(requestRefreshToken==null){
            jwtResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
            jwtResponse.setResponseMessage(Settings.getInstance("").getProperty("INVALID_REFRESH_TOKEN_MSG"));
            return ResponseEntity.status(403)
                    .body(jwtResponse);
        }

        RefreshToken refreshToken=null;
        try{
            refreshToken= refreshTokenService.findByToken(requestRefreshToken).orElseThrow(() -> new IllegalStateException("This refresh token does not exist"));
        }
        catch (Exception e){}


        if(refreshToken==null){
            jwtResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
            jwtResponse.setResponseMessage(Settings.getInstance("").getProperty("REFRESH_TOKEN_REQUIRED_MSG"));
            return ResponseEntity.status(403)
                    .body(jwtResponse);
        }
        //throw new TokenRefreshException(refreshToken.getToken(), Settings.getInstance("").getProperty("INVALID_REFRESH_TOKEN_MSG"));

        //check if refresh token is valid
        refreshTokenService.verifyExpiration(refreshToken);
        refreshTokenService.increaseCount(refreshToken);


        //retrieve the current user tied to the refreshToken
        //User currentUser=userRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalStateException("This user does not exist"));
        User currentUser=refreshToken.getUser();

        //get the user details by the email
        final UserDetails userDetails=jwtUserDetailsService.loadUserByUsername(currentUser.getEmail());

        //generate a token by the user details
        final String token =jwtUtility.generateAccessToken(currentUser);


        //jwtResponse.setData(currentUser);
        jwtResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
        jwtResponse.setJwtToken(token);
        //jwtResponse.setRefreshToken(refreshToken.getToken());
        //jwtResponse.setExpiresDate(refreshToken.getExpiryDate());

        jwtResponse.setResponseMessage(Settings.getInstance("").getProperty("TOKEN_REFRESH_SUCCESS_MSG"));
        return ResponseEntity.ok()
                .body(jwtResponse);
    }
}
