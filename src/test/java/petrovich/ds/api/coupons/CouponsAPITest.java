package petrovich.ds.api.coupons;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import petrovich.ds.dto.coupon.CouponEditionDTO;
import petrovich.ds.dto.coupon.CouponEditionRequestDTO;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class CouponsAPITest extends BaseCouponsAPITest {

    String createPath = "src/test/resources/couponEditionPOSTrequest.json";
    String editPath = "src/test/resources/couponEditionPUTrequest.json";
    String editPathResponse = "src/test/resources/couponEditionPUTresponse.json";


    @SneakyThrows
    public CouponEditionRequestDTO readRequestFromFile(String path) {
        return getObjectMapper().readValue(new URL("file:" + path), CouponEditionRequestDTO.class);
    }

    @SneakyThrows
    public CouponEditionDTO readResponseFromFile(String path) {
        return getObjectMapper().readValue(new URL("file:" + path), CouponEditionDTO.class);
    }


    //    @AfterEach
    public void cleanUp() {
        when()
                .delete("/coupon/edition/"
                        + (readRequestFromFile(createPath)).id)
                .then()
                .statusCode(200);
    }

    @Test
    public void createCouponEditionTest() {
        given(loginWithBearerToken)
                .body(readRequestFromFile(createPath))
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200);
    }

    @Test
    @SneakyThrows
    public void createAndEditCouponEditionTest() {
        given(loginWithBearerToken)
                .body(readRequestFromFile(createPath))
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200);


        Assertions.assertEquals(String.valueOf(readResponseFromFile(editPathResponse)),
                given(loginWithBearerToken)
                        .body(readRequestFromFile(editPath))
                        .when()
                        .put("/coupon/edition/"
                                + (readRequestFromFile(createPath)).id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body()
                        .asPrettyString());
    }

    @Test
    public void deleteActiveCouponEditionTest() {
        Assertions.assertEquals("Тираж уже выпущен в LMS. Удаление невозможно",
                when()
                        .delete("/coupon/edition/" + readRequestFromFile(createPath).id)
                        .then()
                        .statusCode(400)
                        .extract()
                        .response()
                        .body()
                        .jsonPath()
                        .getString("globalErrors[0]"));
    }
}
