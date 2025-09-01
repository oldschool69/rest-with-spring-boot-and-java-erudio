package br.com.oldschool69.rest_with_spring_boot_and_java.controllers;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.security.AccountCredentialsDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.security.TokenDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint!")
@RestController
@RequestMapping("/auth")
public class AuthController implements br.com.oldschool69.rest_with_spring_boot_and_java.controllers.docs.AuthControllerDocs {

    @Autowired
    private AuthService service;

    @PostMapping("/signin")
    @Override
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO credentials) {

        if (isInvalidCredentials(credentials)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var token = service.signIn(credentials);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        return token;
    }

    @PutMapping("/refresh/{username}")
    @Override
    public ResponseEntity<?> refreshToken(
            @PathVariable("username") String username,
            @RequestHeader("Authorization") String refreshToken
    ) {

        if (parametersAreInvalid(username, refreshToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var token = service.refreshToken(username, refreshToken);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        return token;
    }

    @PostMapping(value = "/createUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public AccountCredentialsDTO create(@RequestBody AccountCredentialsDTO credentials) {
        return service.create(credentials);
    }

    private boolean parametersAreInvalid(String username, String refreshToken) {
        return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
    }

    private static boolean isInvalidCredentials(AccountCredentialsDTO credentials) {
        return credentials == null ||
                StringUtils.isBlank(credentials.getPassword()) ||
                StringUtils.isBlank(credentials.getUsername());
    }
}
