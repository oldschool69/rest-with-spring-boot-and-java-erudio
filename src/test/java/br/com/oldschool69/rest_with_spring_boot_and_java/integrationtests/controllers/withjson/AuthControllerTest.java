package br.com.oldschool69.rest_with_spring_boot_and_java.integrationtests.controllers.withjson;

import br.com.oldschool69.rest_with_spring_boot_and_java.config.TestConfigs;
import br.com.oldschool69.rest_with_spring_boot_and_java.dto.AccountCredentialsDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.dto.PersonDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.dto.TokenDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.integrationtests.testcontainers.AbstractionIntegrationTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToEmptyObjectSerializer;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthControllerTest extends AbstractionIntegrationTest {

    private static TokenDTO token;

    @BeforeAll
    static void setUp(){
        token = new TokenDTO();
    }

    @Test
    @Order(1)
    void signin() {
        AccountCredentialsDTO credentials = new AccountCredentialsDTO("leandro", "admin123");

        token = given()
                .basePath("/auth/signin")
                .port(TestConfigs.SERVER_PORT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(credentials)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDTO.class);

        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());

    }

    @Test
    @Order(2)
    void refreshToken() {


        token = given()
                .basePath("/auth/refresh/")
                .port(TestConfigs.SERVER_PORT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("username", token.getUsername())
                .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + token.getRefreshToken())
                .when()
                .put("{username}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDTO.class);

        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());

    }
}