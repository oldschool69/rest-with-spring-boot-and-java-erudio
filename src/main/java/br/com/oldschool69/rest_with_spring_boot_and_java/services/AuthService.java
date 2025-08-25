package br.com.oldschool69.rest_with_spring_boot_and_java.services;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.security.AccountCredentialsDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.security.TokenDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.repository.UserRepository;
import br.com.oldschool69.rest_with_spring_boot_and_java.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        var user = repository.findByUserName(credentials.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("Username " + credentials.getUsername() + " not found");
        }

        var token = tokenProvider.createAccessToken(credentials.getUsername(), user.getRoles());

        return ResponseEntity.ok(token);
    }
}
