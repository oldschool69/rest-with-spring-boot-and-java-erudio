package br.com.oldschool69.rest_with_spring_boot_and_java.controllers.docs;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.security.AccountCredentialsDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AuthControllerDocs {
    @Operation(summary = "Authenticates an user and returns a token")
    ResponseEntity<?> signin(AccountCredentialsDTO credentials);

    @Operation(summary = "Refresh token for authenticated user and returns a token")
    ResponseEntity<?> refreshToken(
            String username,
            String refreshToken
    );

    @Operation(summary = "create a new user")
    AccountCredentialsDTO create(@RequestBody AccountCredentialsDTO credentials);
}
