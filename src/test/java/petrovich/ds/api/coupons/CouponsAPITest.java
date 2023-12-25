package petrovich.ds.api.coupons;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import petrovich.ds.api.coupons.util.CouponsAPIUtils;

import static io.restassured.RestAssured.given;
import static java.nio.file.Paths.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static petrovich.ds.enums.coupon.CouponCampaignType.APPLY;
import static petrovich.ds.enums.coupon.CouponCampaignType.ISSUE;

@DisplayName("Купоны")
public class CouponsAPITest extends CouponsAPIUtils {

    @Test
    @DisplayName("КД: запускаем акцию одноразоввые купоны (выдача + применение)")
    public void CreateFriendsCouponEditionGiveTakeX1() {
        setUpDTOs("friends");
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        checkCouponEditionIsCreated("6576edb86d7f5a03e2b1c688");
    }

    @Test
    @SneakyThrows
    @DisplayName("СО: запускаем акцию одноразоввые купоны (выдача + применение)")
    public void CreateUnionCouponEditionGiveTakeX1() {
        setUpDTOs("union");
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        checkCouponEditionIsCreated("6576edb86d7f5a03e2b1c688");
    }







}
