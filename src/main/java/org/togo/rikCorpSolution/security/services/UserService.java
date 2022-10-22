package org.togo.rikCorpSolution.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.togo.rikCorpSolution.security.entities.Role;
import org.togo.rikCorpSolution.security.entities.User;
import org.togo.rikCorpSolution.security.exceptions.RoleNotFoundException;
import org.togo.rikCorpSolution.security.requests.LoginRequest;
import org.togo.rikCorpSolution.security.requests.RegisterRequest;
import org.togo.rikCorpSolution.security.responses.HttpSuccessResponse;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void UpdateUser(String username, String password);

    void deleteRoleToUser(String username, String role) throws RoleNotFoundException;

    String authenticate(LoginRequest request, AuthenticationManager authenticationManager);

    HttpSuccessResponse storeUser(RegisterRequest request) throws RoleNotFoundException;

    HttpSuccessResponse authUser(Authentication authentication);
    Role addRole(String role);
    void addRoleToUser2(String role, String username) throws RoleNotFoundException;

    List<User> getAllUserWithoutAdmin(String username);

    List<Role> getRoles();

    void deleteUser(String username);
}
