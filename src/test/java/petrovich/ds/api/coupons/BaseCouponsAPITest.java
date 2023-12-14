package petrovich.ds.api.coupons;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import petrovich.ds.api.BaseAPITest;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BaseCouponsAPITest extends BaseAPITest {
    // Удалить черновик акции (тиража)
    public void deleteDraftCouponEdition() {
        given()
                .header("Authorization", "Bearer " + getAdminToken())
                .when()
                .delete(API_URL_DEV + "/coupon/edition/" + campaignId)
                .then()
                .statusCode(200);
    }

    // Попытка удалить активную акцию - 400 ошибка
    public void tryToDeleteActiveCouponEdition() {
        String errorText = given()
                .header("Authorization", "Bearer " + getAdminToken())
                .when()
                .delete(API_URL_DEV + "/coupon/edition/" + campaignId)
                .then()
                .statusCode(400)
                .extract()
                .response()
                .jsonPath()
                .getString("globalErrors[0]");
        Assertions.assertEquals("Тираж уже выпущен в LMS. Удаление невозможно", errorText);
    }
}
