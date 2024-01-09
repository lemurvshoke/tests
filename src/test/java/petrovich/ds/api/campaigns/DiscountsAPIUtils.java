package petrovich.ds.api.campaigns;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import petrovich.ds.api.BaseAPITest;
import petrovich.ds.dto.campaign.CampaignShortDTO;
import petrovich.ds.dto.campaign.CampaignDTO;
import petrovich.ds.dto.deal.CartProcessingRequest;
import petrovich.ds.dto.mechanic.MechanicDTO;
import petrovich.ds.dto.segment.SegmentDataDTO;
import petrovich.ds.enums.campaign.CampaignType;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DiscountsAPIUtils extends BaseAPITest {

    public CampaignDTO campaignPOST;
    public SegmentDataDTO addCustomerPOST;
    public MechanicDTO addMechanicDiscountSumPOST;
    public CartProcessingRequest cartProcessPOST;
    public CampaignShortDTO campaignShortDTO;

    @SneakyThrows
    public <T> T bodyFromDTO(String loyalty, String jsonFile, Class<T> clazz) {
        return getObjectMapper().readValue(
                new File("src/test/resources/campaigns/" + loyalty + '/' + jsonFile), clazz);
    }

    @Step("Настраиваем тестовые объекты")
    public void setupCampaignDTOs(String loyalty, CampaignType campaignType) {
        // Мапинг json в dto object для передачи в body запроса
        campaignPOST = bodyFromDTO(loyalty, "discountPOST.json", CampaignDTO.class);
        // Сеттинг дат: старт = сегодня, дата конца = сегодня + 3 дня
        campaignPOST.startDate = isToday();
        campaignPOST.endDate = isTodayPlus3Days();
        campaignPOST.type = campaignType;
        addCustomerPOST = bodyFromDTO(loyalty, "addSegmentPOST.json", SegmentDataDTO.class);
        addMechanicDiscountSumPOST = bodyFromDTO(loyalty, "addMechanicDiscountSumPOST.json", MechanicDTO.class);
        cartProcessPOST = bodyFromDTO(loyalty, "saleProcessPOST.json", CartProcessingRequest.class);
        campaignShortDTO = bodyFromDTO(loyalty, "campaignShortDTO.json", CampaignShortDTO.class);
    }

    @Step("Создаем персональную акцию")
    public void createCampaign(CampaignDTO body) {
        given(withBearerToken)
                .body(body)
                .when()
                .post("/campaign")
                .then()
                .statusCode(200);
    }

    @Step("Добавляем кастомеров в сегмент")
    public void addSegment(String campaignId, SegmentDataDTO body) {
        given(withBearerToken)
                .pathParam("id", campaignId)
                .body(body)
                .when()
                .post("/campaign/{id}/segment/add")
                .then()
                .statusCode(200);
    }

    @Step("Добавляем пустой сегмент")
    public void addEmptySegment(String campaignId) {
        given(withBearerToken)
                .pathParam("id", campaignId)
                .when()
                .post("/campaign/{id}/segment/empty")
                .then()
                .statusCode(200);
    }

    @Step("Добавляем механику")
    public void addMechanic(String campaignId, MechanicDTO body) {
        given(withBearerToken)
                .pathParam("id", campaignId)
                .body(body)
                .when()
                .post("/campaign/{id}/mechanic")
                .then()
                .statusCode(200);
    }

    @Step("Активируем персональную акцию")
    public void startCampaign(String id) {
        given(withBearerToken)
                .pathParam("id", id)
                .when()
                .post("/campaign/{id}/start")
                .then()
                .statusCode(200);
    }

    @Step("Делаем предрасчет корзины и проверяем, что акция отработала")
    public void saleProcess(CartProcessingRequest body) {
        List<CampaignShortDTO> campaigns = given(noAuth)
                .queryParam("preview", "false")
                .body(body)
                .when()
                .post("/sale/process")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList("campaigns", CampaignShortDTO.class);

        Assertions.assertThat(campaigns).contains(campaignShortDTO);
    }
}
