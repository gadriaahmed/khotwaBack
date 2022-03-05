package com.back.khotwa.security.api.authenticationfilter;

import com.back.khotwa.dto.response.Response;
import com.back.khotwa.model.user.User;
import com.back.khotwa.security.detailsService.CustomRecruiterDetailsService;
import com.back.khotwa.security.authenticationProvider.RecruiterAuthenticationProvider;
import com.back.khotwa.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.back.khotwa.security.SecurityConstants.*;

public class ApiJWTRecruiterAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private RecruiterAuthenticationProvider recruiterAuthenticationProvider;
    private CustomRecruiterDetailsService customRecruiterDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public ApiJWTRecruiterAuthenticationFilter(RecruiterAuthenticationProvider recruiterAuthenticationProvider,CustomRecruiterDetailsService customRecruiterDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.recruiterAuthenticationProvider = recruiterAuthenticationProvider;
        this.customRecruiterDetailsService  = customRecruiterDetailsService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/recruiter/auth", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            recruiterAuthenticationProvider.setCustomRecruiterDetailsService(customRecruiterDetailsService);
            recruiterAuthenticationProvider.setbCryptPasswordEncoder(bCryptPasswordEncoder);
            return recruiterAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        }
        catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        if (auth.getPrincipal() != null) {
            UsernamePasswordAuthenticationToken authentication= (UsernamePasswordAuthenticationToken) auth;
            String login = String.valueOf(authentication.getPrincipal());
            if (login != null && login.length() > 0) {
                Claims claims = Jwts.claims().setSubject(login);
               /* List<String> roles = new ArrayList<>();
                user.getAuthorities().stream().forEach(authority -> roles.add(authority.getAuthority()));
                claims.put("roles", roles);*/
                String token = Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512,SECRET)
                        .compact();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(HEADER_STRING,token);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.getWriter().print(Response.ok().setPayload(jsonObject).toJson());

            }
        }
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //Add more descriptive message
        response.getWriter().print(Response.unauthorized().setPayload(failed.getMessage()).toJson());

    }
}
