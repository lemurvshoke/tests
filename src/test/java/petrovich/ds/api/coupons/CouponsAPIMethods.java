package petrovich.ds.api.coupons;

import lombok.SneakyThrows;
import petrovich.ds.api.BaseAPITest;
import petrovich.ds.dto.CampaignDTO;
import petrovich.ds.dto.coupon.CouponCampaignDTO;
import petrovich.ds.dto.coupon.CouponCampaignRequestDTO;
import petrovich.ds.dto.coupon.CouponEditionDTO;
import petrovich.ds.dto.coupon.CouponEditionRequestDTO;

import java.io.File;

public class CouponsAPIMethods extends BaseAPITest {

    CouponEditionRequestDTO couponEditionPOSTRequest;
    CouponEditionRequestDTO couponEditionPUTRequest;
    CouponEditionDTO couponEditionPOSTResponse;
    CouponEditionDTO couponEditionPUTResponse;
    CouponCampaignRequestDTO issueCampaignPOSTRequest;
    CampaignDTO issueCampaignPOSTResponse;
    CouponCampaignDTO issueCampaignPUTResponse;

    @SneakyThrows
    public <T> T makeBodyFromDTO(String path, Class<T> clazz) {
        return getObjectMapper().readValue(new File("src/test/resources/" + path), clazz);
    }

    public void setBody() {
        // Мапинг json в dto object для передачи в body запроса
        // Сеттинг дат: старт = сегодня, дата конца = сегодня + 3 дня
        couponEditionPOSTRequest = makeBodyFromDTO("coupons/couponEditionPOST.json",
                CouponEditionRequestDTO.class);
        couponEditionPOSTRequest.setStartDate(isToday());
        couponEditionPOSTRequest.setEndDate(isTodayPlus3Days());

        couponEditionPOSTResponse = makeBodyFromDTO("coupons/couponEditionPOSTResp.json",
                CouponEditionDTO.class);
        couponEditionPOSTResponse.setStartDate(isToday());
        couponEditionPOSTResponse.setEndDate(isTodayPlus3Days());

        couponEditionPUTRequest = makeBodyFromDTO("coupons/couponEditionPUT.json",
                CouponEditionRequestDTO.class);
        couponEditionPUTRequest.setStartDate(isToday());
        couponEditionPUTRequest.setEndDate(isTodayPlus3Days());

        couponEditionPUTResponse = makeBodyFromDTO("coupons/couponEditionPUTResp.json",
                CouponEditionDTO.class);
        couponEditionPUTResponse.setStartDate(isToday());
        couponEditionPUTResponse.setEndDate(isTodayPlus3Days());

        issueCampaignPOSTRequest = makeBodyFromDTO("coupons/couponCampaignIssuePOST.json",
                CouponCampaignRequestDTO.class);
        issueCampaignPOSTRequest.setStartDate(isToday());
        issueCampaignPOSTRequest.setEndDate(isTodayPlus3Days());

        issueCampaignPOSTResponse = makeBodyFromDTO("coupons/couponCampaignIssuePOSTResp.json",
                CampaignDTO.class);
        issueCampaignPOSTResponse.setStartDate(isToday());
        issueCampaignPOSTResponse.setEndDate(isTodayPlus3Days());


    }
}
