package petrovich.ds.api.coupons;

import com.google.gson.Gson;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import petrovich.ds.api.BaseAPITest;
import petrovich.ds.dto.coupon.CouponEditionDTO;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class CouponsAPITest extends BaseAPITest {

    String createPathRequest = "src/test/resources/couponEditionPOSTrequest.json";
    String createPathResponse = "src/test/resources/couponEditionPOSTresponse.json";
    String editPathRequest = "src/test/resources/couponEditionPUTrequest.json";
    String editPathResponse = "src/test/resources/couponEditionPUTresponse.json";

//    @SneakyThrows
//    public CouponEditionRequestDTO readRequestFromFile(String path) {
//        return getObjectMapper().readValue(new URL("file:" + path), CouponEditionRequestDTO.class);
//    }

    @SneakyThrows
    public CouponEditionDTO getCouponEditionDTO(String path) {
        return getObjectMapper()
                .readValue(new URL("file:" + path), CouponEditionDTO.class);
    }

    @SneakyThrows
    public void deleteCouponEdition(String path) {
        given(loginWithBearerToken)
                .when()
                .delete("/coupon/edition/" + (getCouponEditionDTO(path).id))
                .then()
                .statusCode(200);
    }

    @Test
    @SneakyThrows
    public void createCouponEditionTest() {
        // СМОТРЕТЬ ЭТОТ ТЕСТ
        JSONAssert.assertEquals(new String(readAllBytes(get(createPathResponse))),
                given(loginWithBearerToken)
                        .body(readAllBytes(get(createPathRequest)))
                        .when()
                        .post("/coupon/edition")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString(),
                JSONCompareMode.STRICT_ORDER);

        // КАК БЫЛО

//        CouponEditionDTO expcted = getObjectMapper().readValue(
//                readAllBytes(get(createPathResponse)), CouponEditionDTO.class);
//
//        CouponEditionDTO actual = given(loginWithBearerToken)
//                .body(readAllBytes(get(createPathRequest)))
//                .when()
//                .post("/coupon/edition")
//                .then()
//                .statusCode(200)
//                .extract()
//                .body()
//                .as(CouponEditionDTO.class);
//
//        Assertions.assertEquals(expcted, actual);
    }


    @Test
    @SneakyThrows
    public void createAndEditCouponEditionTest() {
        JSONAssert.assertEquals(new String(readAllBytes(get(createPathResponse))),
                given(loginWithBearerToken)
                        .body(readAllBytes(get(createPathRequest)))
                        .when()
                        .post("/coupon/edition")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString(),
                JSONCompareMode.STRICT_ORDER);

        JSONAssert.assertEquals(new String(readAllBytes(get(editPathResponse))),
                given(loginWithBearerToken)
                        .body(readAllBytes(get(editPathRequest)))
                        .when()
                        .put("/coupon/edition/" + (getCouponEditionDTO(createPathRequest).id))
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString(),
                JSONCompareMode.STRICT_ORDER);
    }

    @Disabled
    @Test
    @SneakyThrows
    public void deleteActiveCouponEditionTest() {

        Assertions.assertEquals(new String(readAllBytes(get(createPathResponse))),
                given(loginWithBearerToken)
                        .body(readAllBytes(get(createPathRequest)))
                        .when()
                        .post("/coupon/edition")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body()
                        .asString());

        Assertions.assertEquals("Тираж уже выпущен в LMS. Удаление невозможно",
                when()
                        .delete("/coupon/edition/" + (getCouponEditionDTO(createPathRequest).id))
                        .then()
                        .statusCode(400)
                        .extract()
                        .response()
                        .body()
                        .jsonPath()
                        .getString("globalErrors[0]"));
    }

    @Test
    @SneakyThrows
    public void createCouponCampaignTest() {

        JSONAssert.assertEquals(new String(readAllBytes(get(createPathResponse))),
                given(loginWithBearerToken)
                        .body(readAllBytes(get(createPathRequest)))
                        .when()
                        .post("/coupon/edition")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString(),
                JSONCompareMode.STRICT_ORDER);
    }
}
