package org.togo.rikCorpSolution.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togo.rikCorpSolution.security.exceptions.RoleNotFoundException;
import org.togo.rikCorpSolution.security.handlers.ExceptionsHandler;
import org.togo.rikCorpSolution.security.requests.LoginRequest;
import org.togo.rikCorpSolution.security.requests.RegisterRequest;
import org.togo.rikCorpSolution.security.responses.HttpSuccessResponse;
import org.togo.rikCorpSolution.security.responses.JwtResponse;
import org.togo.rikCorpSolution.security.services.UserService;

import javax.validation.Valid;
import static org.springframework.http.HttpStatus.CREATED;
import static org.togo.rikCorpSolution.security.utils.constants.JavaConstant.API_BASE_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping(API_BASE_URL)
public class AuthController extends ExceptionsHandler {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "register", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<HttpSuccessResponse> register(@RequestBody @Valid RegisterRequest request) throws RoleNotFoundException {
        HttpSuccessResponse response = userService.storeUser(request);
        return ResponseEntity.status(CREATED).body(response);
    }

    @PostMapping(value = "login", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest request) {
        System.out.println(request+"***************************************");
        JwtResponse response = new JwtResponse();
        String token = userService.authenticate(request, authenticationManager);
        response.setAccess_token(token);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "me", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<HttpSuccessResponse> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpSuccessResponse response = userService.authUser(authentication);
        return ResponseEntity.ok().body(response);
    }


}
