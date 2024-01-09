package petrovich.ds.api.campaigns;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import petrovich.ds.enums.campaign.CampaignType;

@Epic("Скидки")
public class DiscountsAPITest extends DiscountsAPIUtils {

    @Test
    @Feature("Скидка на покупку на суммму от")
    @Story("КД: скидка видна в предрассчете корзины")
    public void createDiscountCampaignTest() {
        setupCampaignDTOs("friends", CampaignType.DISCOUNT);
        createCampaign(campaignPOST);
        addEmptySegment("658eb20f56f24d7c980be299");
        addSegment("658eb20f56f24d7c980be299", addCustomerPOST);
        addMechanic("658eb20f56f24d7c980be299", addMechanicDiscountSumPOST);
        startCampaign("658eb20f56f24d7c980be299");
        saleProcess(cartProcessPOST);
    }
}
