package org.togo.rikCorpSolution.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.togo.rikCorpSolution.security.exceptions.RoleNotFoundException;
import org.togo.rikCorpSolution.security.requests.LoginRequest;
import org.togo.rikCorpSolution.security.requests.RegisterRequest;
import org.togo.rikCorpSolution.security.responses.HttpSuccessResponse;

public interface UserService extends UserDetailsService {
    
    String authenticate(LoginRequest request, AuthenticationManager authenticationManager);

    HttpSuccessResponse storeUser(RegisterRequest request) throws RoleNotFoundException;

    HttpSuccessResponse authUser(Authentication authentication);
}
