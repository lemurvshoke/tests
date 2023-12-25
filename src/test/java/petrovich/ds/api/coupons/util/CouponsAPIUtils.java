package petrovich.ds.api.coupons.util;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import petrovich.ds.api.BaseAPITest;
import petrovich.ds.dto.coupon.CouponCampaignRequestDTO;
import petrovich.ds.dto.coupon.CouponEditionDTO;
import petrovich.ds.dto.coupon.CouponEditionRequestDTO;
import petrovich.ds.dto.mailing.CampaignMailingDTO;
import petrovich.ds.dto.mechanic.MechanicDTO;
import petrovich.ds.enums.coupon.CouponCampaignType;
import java.io.File;

import static io.restassured.RestAssured.given;

public class CouponsAPIUtils extends BaseAPITest {

    public CouponEditionRequestDTO couponEditionPOST;
    public CouponCampaignRequestDTO issueCampaignPOST;
    public CouponCampaignRequestDTO applyCampaignPOST;
    MechanicDTO issueMechanicPOST;
    MechanicDTO applyMechanicPOST;
    CampaignMailingDTO issueMailingPOST;
    CouponEditionDTO couponEditionPOSTResponse;
    CouponEditionDTO couponEditionPUTResponse;

    @SneakyThrows
    public <T> T bodyFromDTO(String loyalty, String path, Class<T> clazz) {
        return getObjectMapper().readValue(
                new File("src/test/resources/coupons/" + loyalty + '/' + path), clazz);
    }

    @Step("Создаем тираж купонной акции")
    public void createCouponEdition(CouponEditionRequestDTO body) {
        given(withBearerToken)
                .body(body)
                .when()
                .post("/coupon/edition")
                .then()
                .statusCode(200);
    }

    @Step("Создаем черновик акции выдачи/ применения купонов")
    public void createIssueOrApplyCampaign(CouponCampaignType type, CouponCampaignRequestDTO body) {
        given(withBearerToken)
                .queryParam("type", type)
                .body(body)
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
                .ignoringFields("externalId", "issuingCampaign.mailings.id", "issuingCampaign.mailings.sendDate")
                .isEqualTo(couponEditionPOSTResponse);
    }

//    @SneakyThrows
//    public void deleteCouponEdition(String path) {
//        // Удаляем купонный тираж
//        given(withBearerToken)
//                .when()
//                .delete("/coupon/edition/" + bodyFromDTO(path, CouponEditionRequestDTO.class).id)
//                .then()
//                .statusCode(200);
//    }

    public void setUpDTOs(String loyalty) {
        // Мапинг json в dto object для передачи в body запроса
        couponEditionPOST = bodyFromDTO(loyalty,"couponEditionPOST.json", CouponEditionRequestDTO.class);
        // Сеттинг дат: старт = сегодня, дата конца = сегодня + 3 дня
        couponEditionPOST.startDate = isToday();
        couponEditionPOST.endDate = isTodayPlus3Days();

        couponEditionPOSTResponse = bodyFromDTO(loyalty,"couponEditionPOSTResp.json", CouponEditionDTO.class);
        couponEditionPOSTResponse.startDate = isToday();
        couponEditionPOSTResponse.endDate = isTodayPlus3Days();
        couponEditionPOSTResponse.applyingCampaign.startDate = isToday();
        couponEditionPOSTResponse.applyingCampaign.endDate = isTodayPlus3Days();
        couponEditionPOSTResponse.issuingCampaign.startDate = isToday();
        couponEditionPOSTResponse.issuingCampaign.endDate = isTodayPlus3Days();

        couponEditionPUTResponse = bodyFromDTO(loyalty,"couponEditionPUTResp.json", CouponEditionDTO.class);
        couponEditionPUTResponse.startDate = isToday();
        couponEditionPUTResponse.endDate = isTodayPlus3Days();

        issueCampaignPOST = bodyFromDTO(loyalty,"issueCampaignPOST.json", CouponCampaignRequestDTO.class);
        issueCampaignPOST.startDate = isToday();
        issueCampaignPOST.endDate = isTodayPlus3Days();

        issueMechanicPOST = bodyFromDTO(loyalty,"issueMechanicPOST.json", MechanicDTO.class);

        applyCampaignPOST = bodyFromDTO(loyalty,"applyCampaignPOST.json", CouponCampaignRequestDTO.class);
        applyCampaignPOST.startDate = isToday();
        applyCampaignPOST.endDate = isTodayPlus3Days();

        applyMechanicPOST = bodyFromDTO(loyalty,"applyMechanicPOST.json", MechanicDTO.class);

        issueMailingPOST = bodyFromDTO(loyalty,"issueMailingPOST.json", CampaignMailingDTO.class);
        issueMailingPOST.sendDate = isToday();

    }
}
