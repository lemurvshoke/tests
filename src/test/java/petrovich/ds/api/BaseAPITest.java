package petrovich.ds.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;


public class BaseAPITest {

    public static final String BASE_URL_DEV = "http://dmz-decision01.stdp.ru";
    public static final String BASE_URL_STAGE = "http://ui.js.a.kluatr.ru";
    public static final String API_URL_DEV = "http://dmz-decision01.stdp.ru/api/ds";
    public static final String API_URL_STAGE = "http://ui.js.a.kluatr.ru/api/ds";

    private final ObjectMapper objectMapper;

    public BaseAPITest() {
        this.objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public RequestSpecification loginWithBearerToken =
            given()
                    .baseUri(API_URL_DEV)
                    .header("Authorization", "Bearer " + getAdminToken())
                    .header("Content-Type", "application/json");

    // Получить токен Админа
    public String getAdminToken() {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Charset", "utf-8")
                .body("username=test_administrator&password=test_administrator")
                .when()
                .post(BASE_URL_DEV + "/api/uaa/auth/token")
                .then()
                .extract()
                .response()
                .body()
                .jsonPath()
                .getString("access_token");
    }

    @SneakyThrows
    public LocalDate isToday() {
        return LocalDate.parse(String.valueOf(LocalDate.now()),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @SneakyThrows
    public LocalDate isTodayPlus3Days() {
        return LocalDate.parse(String.valueOf(LocalDate.now().plusDays(3)),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
