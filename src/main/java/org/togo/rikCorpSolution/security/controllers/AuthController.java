package org.togo.rikCorpSolution.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.security.entities.Role;
import org.togo.rikCorpSolution.security.entities.User;
import org.togo.rikCorpSolution.security.exceptions.RoleNotFoundException;
import org.togo.rikCorpSolution.security.handlers.ExceptionsHandler;
import org.togo.rikCorpSolution.security.requests.LoginRequest;
import org.togo.rikCorpSolution.security.requests.RegisterRequest;
import org.togo.rikCorpSolution.security.responses.HttpSuccessResponse;
import org.togo.rikCorpSolution.security.responses.JwtResponse;
import org.togo.rikCorpSolution.security.services.UserService;

import javax.validation.Valid;
import java.util.List;

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
    @DeleteMapping(value = "delete/{username}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public void delete(@PathVariable("username")String username){
        userService.deleteUser(username);
    }
    @PostMapping(value = "addRole",produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Role addRole(@RequestParam("role") String role){
        return  userService.addRole(role);
    }
    @PutMapping(value = "update/{password}",produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public void update(@PathVariable("password")String password,@RequestParam("username") String username){
        userService.UpdateUser(username,password);
    }
    @PostMapping(value = "addRoleToUser/{username}",produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<HttpSuccessResponse> addRoleToUser(@PathVariable("username")String username,@RequestParam("role")String roleName) throws RoleNotFoundException {
        userService.addRoleToUser2(roleName,username);
        return this.me();
    }
    @PostMapping(value = "getRoles",produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<Role> getRoles() {
        return userService.getRoles();
    }
    @PostMapping(value = "getAllUserWithoutAdmin/{username}",produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public List<User> getAllUserWithoutAdmin(@PathVariable("username")String username) {
        return userService.getAllUserWithoutAdmin(username);
    }
    @DeleteMapping("deleteRoleTo/{username}")
    public void deleteRoleTo(@PathVariable("username")String username,@RequestParam("role")String role) throws RoleNotFoundException {
        userService.deleteRoleToUser(username,role);
    }
}
