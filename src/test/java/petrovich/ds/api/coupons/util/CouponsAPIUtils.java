package petrovich.ds.api.coupons.util;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import petrovich.ds.api.BaseAPITest;
import petrovich.ds.dto.CampaignDTO;
import petrovich.ds.dto.coupon.CouponCampaignRequestDTO;
import petrovich.ds.dto.coupon.CouponEditionDTO;
import petrovich.ds.dto.coupon.CouponEditionRequestDTO;
import petrovich.ds.dto.mailing.CampaignMailingDTO;
import petrovich.ds.dto.mechanic.EditMechanicDTO;
import petrovich.ds.dto.mechanic.MechanicDTO;

import java.io.File;

import static io.restassured.RestAssured.given;
import static petrovich.ds.enums.coupon.CouponCampaignType.APPLY;
import static petrovich.ds.enums.coupon.CouponCampaignType.ISSUE;

public class CouponsAPIUtils extends BaseAPITest {

    CouponEditionRequestDTO couponEditionPOST;
    CouponEditionRequestDTO couponEditionPUT;
    CouponCampaignRequestDTO issueCampaignPOST;
    CouponCampaignRequestDTO applyCampaignPOST;
    MechanicDTO issueMechanicPOST;
    MechanicDTO applyMechanicPOST;
    CampaignMailingDTO issueMailingPOST;
    CouponEditionDTO couponEditionPOSTResponse;
    CouponEditionDTO couponEditionPUTResponse;


    @SneakyThrows
    public <T> T makeBodyFromDTO(String path, Class<T> clazz) {
        return getObjectMapper().readValue(new File("src/test/resources/" + path), clazz);
    }

    @Step("Создаем тираж купонной акции")
    public void createCouponEdition() {
        given(withBearerToken)
                .body(couponEditionPOST)
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200);
    }

    @Step("Создаем черновик акции выдачи/ применения купонов")
    public void createIssueCampaign() {
        given(withBearerToken)
                .queryParam("type", ISSUE)
                .body(issueCampaignPOST)
                .when()
                .post("/coupon/campaign")
                .then()
                .statusCode(200);
    }

    @Step("Создаем черновик акции выдачи/ применения купонов")
    public void createApplyCampaign() {
        given(withBearerToken)
                .queryParam("type", APPLY)
                .body(applyCampaignPOST)
                .when()
                .post("/coupon/campaign")
                .then()
                .statusCode(200);
    }

    @Step("Заполняем механику на выдачу купонов")
    public void populateIssueMechanic(String id) {
        given(withBearerToken)
                .pathParam("id", id)
                .body(issueMechanicPOST)
                .when()
                .post("/campaign/{id}/mechanic")
                .then()
                .statusCode(200);
    }

    @Step("Заполняем механику на применение купонов")
    public void populateApplyMechanic(String id) {
        given(withBearerToken)
                .pathParam("id", id)
                .body(applyMechanicPOST)
                .when()
                .post("/campaign/{id}/mechanic")
                .then()
                .statusCode(200);
    }

    @Step("Добавляем рассылку")
    public void addMailingToCampaign(String campaignId) {
        given(withBearerToken)
                .pathParam("campaignId", campaignId)
                .body(issueMailingPOST)
                .when()
                .post("/campaign/{campaignId}/mailing")
                .then()
                .statusCode(200);
    }

    @Step("Активируем персональную акцию")
    public void startCampaign(String id) {
        given(withBearerToken)
                .pathParam("id", id)
                .when()
                .post("/coupon/edition/{id}/start")
                .then()
                .statusCode(200);
    }

    @Step("Проверяем, что персональная акция создана")
    public void checkCouponEditionIsCreated(String id) {
        Assertions.assertThat(given(withBearerToken)
                        .pathParam("id", id)
                        .when()
                        .get("/coupon/edition/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(CouponEditionDTO.class))
                .usingRecursiveComparison()
                .ignoringFields("externalId", "issuingCampaign.mailings.id")
                .isEqualTo(couponEditionPOSTResponse);
    }

    @SneakyThrows
    public void deleteCouponEdition(String path) {
        // Удаляем купонный тираж
        given(withBearerToken)
                .when()
                .delete("/coupon/edition/" + makeBodyFromDTO(path, CouponEditionRequestDTO.class).id)
                .then()
                .statusCode(200);
    }

    public void setBody() {
        // Мапинг json в dto object для передачи в body запроса
        // Сеттинг дат: старт = сегодня, дата конца = сегодня + 3 дня
        couponEditionPOST = makeBodyFromDTO("coupons/couponEditionPOST.json",
                CouponEditionRequestDTO.class);
        couponEditionPOST.setStartDate(isToday());
        couponEditionPOST.setEndDate(isTodayPlus3Days());

        couponEditionPOSTResponse = makeBodyFromDTO("coupons/couponEditionPOSTResp.json",
                CouponEditionDTO.class);
        couponEditionPOSTResponse.setStartDate(isToday());
        couponEditionPOSTResponse.setEndDate(isTodayPlus3Days());

        couponEditionPUT = makeBodyFromDTO("coupons/couponEditionPUT.json",
                CouponEditionRequestDTO.class);
        couponEditionPUT.setStartDate(isToday());
        couponEditionPUT.setEndDate(isTodayPlus3Days());

        couponEditionPUTResponse = makeBodyFromDTO("coupons/couponEditionPUTResp.json",
                CouponEditionDTO.class);
        couponEditionPUTResponse.setStartDate(isToday());
        couponEditionPUTResponse.setEndDate(isTodayPlus3Days());

        issueCampaignPOST = makeBodyFromDTO("coupons/issueCampaignPOST.json",
                CouponCampaignRequestDTO.class);
        issueCampaignPOST.setStartDate(isToday());
        issueCampaignPOST.setEndDate(isTodayPlus3Days());


        issueMechanicPOST = makeBodyFromDTO("coupons/issueMechanicPOST.json",
                MechanicDTO.class);

        applyCampaignPOST = makeBodyFromDTO("coupons/applyCampaignPOST.json",
                CouponCampaignRequestDTO.class);
        applyCampaignPOST.setStartDate(isToday());
        applyCampaignPOST.setEndDate(isTodayPlus3Days());

        applyMechanicPOST = makeBodyFromDTO("coupons/applyMechanicPOST.json",
                MechanicDTO.class);

        issueMailingPOST = makeBodyFromDTO("coupons/issueMailingPOST.json",
                CampaignMailingDTO.class);
        issueMailingPOST.setSendDate(isToday());

    }
}
