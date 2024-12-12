package com.ecl.popcensus.Filters;

import com.ecl.popcensus.service.JWTUserDetailsService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Component
@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {
    public static String authenticatedUser = null;
    private final JWTUtility jwtTokenUtil;
    private final JWTUserDetailsService userDetail;

    public JwtTokenFilter(JWTUtility jwtTokenUtil, JWTUserDetailsService userDetail) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetail = userDetail;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //exclude auth endpoints
        String requestPath = request.getRequestURI();
        if (requestPath.startsWith("/api/v1/auth/") || requestPath.startsWith("/api/v1/users/register")) {
            chain.doFilter(request, response);
            return;
        }


        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

//        if (header == null || !header.startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
//            response.getWriter().write("Authorization header is missing or invalid.");
//            return;
//        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        authenticatedUser = jwtTokenUtil.getUsername(token);

        // Get user identity and set it on the spring security context
        UserDetails userDetails = userDetail.loadUserByUsername(jwtTokenUtil.getUsername(token));

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
