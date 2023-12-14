package ui.campaigns.coupons;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.campaigns.coupons.util.FriendsReusedSteps.createFriendsDiscountGiveTakeX1Coupon;
import static ui.campaigns.coupons.util.UnionReusedSteps.createUnionDiscountGiveTakeX1Coupon;

@DisplayName("Промокоды")
public class CouponsActionsTest extends BaseCouponCampaignTest {

    @Test @SneakyThrows
    @DisplayName("СО: создание акции Скидка: выдача + применение (одноразовые купоны)")
    public void createUnionDiscountGiveTakeX1CouponTest() {
        goToCouponsTab();
        createUnionDiscountGiveTakeX1Coupon();
        checkX1TimeAndDiscountAreVisible();
        $(byText("Союз отважных")).shouldBe(visible);
        checkMainGiveTakeMechanicIsCreated();
        createGiveMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(0);
        setGivePurchaseSum(10000);
        createTakeMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(1);
        $(byText("Скидка (проценты)")).click();
        setTakePurchaseSumAndDiscount(20000, 10);
        createGiveMailing();
        activateCompany();
    }

    @Test @SneakyThrows
    @DisplayName("КД: создание акции Скидка: выдача + применение (одноразовые купоны)")
    public void createFriendsDiscountGiveTakeX1CouponTest() {
        goToCouponsTab();
        createFriendsDiscountGiveTakeX1Coupon();
        checkX1TimeAndDiscountAreVisible();
        $(byText("Клуб друзей Петровича")).shouldBe(visible);
        checkMainGiveTakeMechanicIsCreated();
        createGiveMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(0);
        setGivePurchaseSum(10000);
        createTakeMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(1);
        $(byText("Скидка (проценты)")).click();
        setTakePurchaseSumAndDiscount(20000, 10);
        createGiveMailing();
        activateCompany();
    }










}


