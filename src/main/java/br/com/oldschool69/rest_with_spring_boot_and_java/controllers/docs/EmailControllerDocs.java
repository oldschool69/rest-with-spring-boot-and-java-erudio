package br.com.oldschool69.rest_with_spring_boot_and_java.controllers.docs;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.request.EmailRequestDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface EmailControllerDocs {

    @Operation(summary = "Send an e-mail",
            description = "Send an e-mail providing details, subject and body",
            tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

            }
    )
    ResponseEntity<String> sendEmail(EmailRequestDTO emailRequestDTO);

    @Operation(summary = "Send e-mail with attachment",
            description = "Send an e-mail providing details, subject, body and attachment",
            tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

            }
    )
    ResponseEntity<String> sendEmail(String emailRequestJson, MultipartFile multipartFile);
}
