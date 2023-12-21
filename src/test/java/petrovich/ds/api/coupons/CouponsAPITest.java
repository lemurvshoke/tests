package petrovich.ds.api.coupons;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import petrovich.ds.dto.CampaignDTO;
import petrovich.ds.dto.coupon.CouponCampaignDTO;
import petrovich.ds.dto.coupon.CouponEditionDTO;
import petrovich.ds.dto.coupon.CouponEditionRequestDTO;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Купоны")
public class CouponsAPITest extends CouponsAPIMethods {

    @BeforeEach
    public void setUp() {
        setBody();
    }

    @Test
    @SneakyThrows
    @DisplayName("Тираж купонов: создать черновик")
    public void createCouponEditionTest() {

        assertThat(given(loginWithBearerToken)
                .body(couponEditionPOSTRequest)
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CouponEditionDTO.class))
                .usingRecursiveComparison()
                .isEqualTo(couponEditionPOSTResponse);
    }

    @Test
    @SneakyThrows
    @DisplayName("Тираж купонов: создать черновик и отредактировать")
    public void createAndEditCouponEditionTest() {

        assertThat(given(loginWithBearerToken)
                .body(couponEditionPOSTRequest)
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CouponEditionDTO.class))
                .usingRecursiveComparison()
                .isEqualTo(couponEditionPOSTResponse);

        assertThat(given(loginWithBearerToken)
                .body(couponEditionPOSTRequest)
                .when()
                .put("/coupon/edition/" + couponEditionPOSTResponse.id)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CouponEditionDTO.class))
                .usingRecursiveComparison()
                .isEqualTo(couponEditionPOSTResponse);
    }

//    @Disabled
//    @Test
//    @SneakyThrows
//    public void deleteActiveCouponEditionTest() {
//
//        Assertions.assertEquals(new String(readAllBytes(get(createPathResponse))),
//                given(loginWithBearerToken)
//                        .body(readAllBytes(get(createPathRequest)))
//                        .when()
//                        .post("/coupon/edition")
//                        .then()
//                        .statusCode(200)
//                        .extract()
//                        .response()
//                        .body()
//                        .asString());
//
//        Assertions.assertEquals("Тираж уже выпущен в LMS. Удаление невозможно",
//                when()
//                        .delete("/coupon/edition/"
//                                + makeBodyFromDTO(createPathRequest, CouponEditionRequestDTO.class).id)
//                        .then()
//                        .statusCode(400)
//                        .extract()
//                        .response()
//                        .body()
//                        .jsonPath()
//                        .getString("globalErrors[0]"));
//    }

    @Test
    @SneakyThrows
    @DisplayName("Акция выдача: создать черновик")
    public void createCouponCampaignTest() {

        assertThat(given(loginWithBearerToken)
                .body(issueCampaignPOSTRequest)
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CampaignDTO.class))
                .usingRecursiveComparison()
                .isEqualTo(issueCampaignPOSTResponse);
    }

    @SneakyThrows
    public void deleteCouponEdition(String path) {
        given(loginWithBearerToken)
                .when()
                .delete("/coupon/edition/" + makeBodyFromDTO(path, CouponEditionRequestDTO.class).id)
                .then()
                .statusCode(200);
    }
}
