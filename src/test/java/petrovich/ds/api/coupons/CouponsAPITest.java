package petrovich.ds.api.coupons;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import petrovich.ds.enums.coupon.CouponRedemptionType;

import static io.restassured.RestAssured.given;
import static java.nio.file.Paths.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static petrovich.ds.enums.coupon.CouponCampaignType.APPLY;
import static petrovich.ds.enums.coupon.CouponCampaignType.ISSUE;

@Epic("Промокоды")
public class CouponsAPITest extends CouponsAPIUtils {
    // Клуб Друзей
    @Test
    @Story("КД: запускаем акцию одноразовые промокоды (выдача + применение)")
    public void createFriendsCouponEditionGiveTakeX1() {
        setupDTOs("friends", CouponRedemptionType.SINGLE);
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        getAndCheckCouponEditionById("6576edb86d7f5a03e2b1c688");
    }

    @Test
    @DisplayName("КД: запускаем акцию многоразовые промокоды (выдача + применение)")
    public void createFriendsCouponEditionGiveTakeXN() {
        setupDTOs("friends", CouponRedemptionType.MULTIPLE);
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        getAndCheckCouponEditionById("6576edb86d7f5a03e2b1c688");
    }

    @Test
    @DisplayName("КД: акция доступна в ЛК сайта")
    public void checkFriendsCouponEditionXNIsAvailableInLK() {
        setupDTOs("friends", CouponRedemptionType.MULTIPLE);
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        getAndCheckApplyCampaignByCustomer("b2eb1411-299d-11e7-a207-00259038e9f2");
    }

    // Союз Отважных
    @Test
    @DisplayName("СО: запускаем акцию многоразовые промокоды (выдача + применение)")
    public void createUnionCouponEditionGiveTakeXN() {
        setupDTOs("friends", CouponRedemptionType.SINGLE);
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        getAndCheckCouponEditionById("6576edb86d7f5a03e2b1c688");
    }

    @Test
    @DisplayName("СО: запускаем акцию многоразовые промокоды (выдача + применение)")
    public void createUnionCouponEditionGiveTakeX1() {
        setupDTOs("union", CouponRedemptionType.MULTIPLE);
        createCouponEdition(couponEditionPOST);
        createIssueOrApplyCampaign(ISSUE, issueCampaignPOST);
        populateIssueMechanic("id678issue");
        createIssueOrApplyCampaign(APPLY, applyCampaignPOST);
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        getAndCheckApplyCampaignByCustomer("6576edb86d7f5a03e2b1c688");
    }


    // Обе программы лояльности






}
