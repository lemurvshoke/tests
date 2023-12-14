package petrovich.ds.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.restassured.specification.RequestSpecification;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static java.time.format.DateTimeFormatter.ofPattern;


public class BaseAPITest {

    static final String BASE_URL_DEV = "http://dmz-decision01.stdp.ru";
    static final String BASE_URL_STAGE = "http://ui.js.a.kluatr.ru";
    public static final String API_URL_DEV = "http://dmz-decision01.stdp.ru/api/ds";
    static final String API_URL_STAGE = "http://ui.js.a.kluatr.ru/api/ds";
    public static String campaignId;
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String DATE_TIME_PATTEN = "dd.MM.yyyy HH:mm:ss";

    private final ObjectMapper objectMapper;

    public BaseAPITest() {
        this.objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_PATTERN));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        Locale locale = Locale.forLanguageTag("ru");
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ofPattern(DATE_PATTERN)));
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ofPattern(DATE_TIME_PATTEN)));
        objectMapper.registerModule(javaTimeModule);

//        objectMapper.registerModule(new JavaTimeModule());
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(LocalDate.class, new LocalDateSerializer(ofPattern(DATE_PATTERN, locale)));
//        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ofPattern(DATE_TIME_PATTEN, locale)));
//        objectMapper.registerModule(module);
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }


//    public RequestSpecification loginWithBearer =
//            given()
//                    .baseUri(API_URL_DEV)
//                    .header("Authorization", "Bearer " + getAdminToken());

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


}
