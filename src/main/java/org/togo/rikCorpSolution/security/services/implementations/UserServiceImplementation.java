package org.togo.rikCorpSolution.security.services.implementations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.security.entities.Role;
import org.togo.rikCorpSolution.security.entities.User;
import org.togo.rikCorpSolution.security.exceptions.RoleNotFoundException;
import org.togo.rikCorpSolution.security.repositories.RoleRepository;
import org.togo.rikCorpSolution.security.repositories.UserRepository;
import org.togo.rikCorpSolution.security.requests.LoginRequest;
import org.togo.rikCorpSolution.security.requests.RegisterRequest;
import org.togo.rikCorpSolution.security.responses.HttpSuccessResponse;
import org.togo.rikCorpSolution.security.services.UserService;
import org.togo.rikCorpSolution.security.utils.JavaConverter;
import org.togo.rikCorpSolution.security.utils.JwtUtils;
import org.togo.rikCorpSolution.security.utils.UserPrincipal;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.togo.rikCorpSolution.security.utils.JavaUtils.successResponse;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaConverter javaConverter;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, JwtUtils jwtUtils, BCryptPasswordEncoder bCryptPasswordEncoder, JavaConverter javaConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaConverter = javaConverter;
    }

    @Override
    public HttpSuccessResponse storeUser(RegisterRequest request) throws RoleNotFoundException {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        User user = userRepository.save(javaConverter.registerToUser(request));
        addRoleToUser("ROLE_USER", user.getUsername());
        return successResponse(CREATED, "Votre compte a bien été créer.", javaConverter.userToUserResponse(user));
    }

    @Override
    public HttpSuccessResponse authUser(Authentication authentication) {
        String username = authentication.getName();
        Optional<org.togo.rikCorpSolution.security.entities.User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur Non trouvée!"));
        return successResponse(OK, "Détails du compte de l'utilisateur", user.map(javaConverter::userToUserResponse));
    }

    private void addRoleToUser(String roleName, String username) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findByName(roleName);
        role.orElseThrow(() -> new RoleNotFoundException("Role n'existe pas"));
        Optional<org.togo.rikCorpSolution.security.entities.User> user = userRepository.findByUsername(username);
        user
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas"))
                .getRoles()
                .add(role.get());
    }

    @Override
    public String authenticate(LoginRequest request, AuthenticationManager authenticationManager) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Optional<org.togo.rikCorpSolution.security.entities.User> user = userRepository.findByUsername(request.getUsername());
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur Non trouvée!"));
        return jwtUtils.generateJwtToken(user.map(UserPrincipal::new).get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<org.togo.rikCorpSolution.security.entities.User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas dans la base."));
        return user.map(UserPrincipal::new).get();
    }
}
