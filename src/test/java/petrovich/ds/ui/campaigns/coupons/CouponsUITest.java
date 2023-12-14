package petrovich.ds.ui.campaigns.coupons;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static petrovich.ds.ui.campaigns.coupons.util.BothLoyaltyReusedSteps.create2loyaltyDiscountCampaignX1Coupon;
import static petrovich.ds.ui.campaigns.coupons.util.FriendsReusedSteps.createFriendsDiscountCampaignX1Coupon;
import static petrovich.ds.ui.campaigns.coupons.util.UnionReusedSteps.createUnionDiscountCampaignX1Coupon;


@DisplayName("Промокоды")
public class CouponsUITest extends BaseCouponsUITest {

    // КЛУБ ДРУЗЕЙ
    @Test
    @SneakyThrows
    @DisplayName("КД: создание акции Скидка: выдача + применение (одноразовые купоны)")
    public void createFriendsDiscountGiveTakeX1Coupon() {
        goToCouponsTab();
        createFriendsDiscountCampaignX1Coupon();
        checkX1TimeAndDiscountAreVisible();
        checkMainGiveTakeMechanicIsCreated();
        createGiveMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(0);
        setGivePurchaseSum(10000);
        createTakeMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(1);
        $(byText("Скидка (проценты)")).click();
        setTakePurchaseSumAndDiscount(20000, 10);
        createGiveMailing();
        activateCampaignAndWaitForLoading();
        stopActiveCouponEdition();
    }

    @Test
    @SneakyThrows
    @DisplayName("КД: попытка удалить активную акцию Скидка: выдача + применение (одноразовые купоны)")
    public void createAndTryToDeleteFriendsDiscountGiveTakeX1Coupon() {
        goToCouponsTab();
        createFriendsDiscountCampaignX1Coupon();
        checkX1TimeAndDiscountAreVisible();
        checkMainGiveTakeMechanicIsCreated();
        createGiveMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(0);
        setGivePurchaseSum(10000);
        createTakeMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(1);
        $(byText("Скидка (проценты)")).click();
        setTakePurchaseSumAndDiscount(20000, 10);
        createGiveMailing();
        activateCampaignAndWaitForLoading();
        tryToDeleteActiveCouponEdition();
    }

    // СОЮЗ ОТВАЖНЫХ
    @Test
    @SneakyThrows
    @DisplayName("СО: создание акции Скидка: выдача + применение (одноразовые купоны)")
    public void createUnionDiscountGiveTakeX1Coupon() {
        goToCouponsTab();
        createUnionDiscountCampaignX1Coupon();
        checkX1TimeAndDiscountAreVisible();
        checkMainGiveTakeMechanicIsCreated();
        createGiveMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(0);
        setGivePurchaseSum(10000);
        createTakeMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(1);
        $(byText("Скидка (проценты)")).click();
        setTakePurchaseSumAndDiscount(20000, 10);
        createGiveMailing();
        activateCampaignAndWaitForLoading();
    }

    // ВСЕ УЧАСТНИКИ ПЛ
    @Test
    @SneakyThrows
    @DisplayName("КД+СО: остановить активную акцию Скидка: выдача + применение (одноразовые купоны)")
    public void createAndStop2LoyaltyDiscountGiveTakeX1Coupon() {
        goToCouponsTab();
        create2loyaltyDiscountCampaignX1Coupon();
        checkX1TimeAndDiscountAreVisible();
        checkMainGiveTakeMechanicIsCreated();
        createGiveMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(0);
        setGivePurchaseSum(10000);
        createTakeMechanic();
        selectFirstGiveOrTakeMechanicFromDropdown(1);
        $(byText("Скидка (проценты)")).click();
        setTakePurchaseSumAndDiscount(20000, 10);
        createGiveMailing();
        activateCampaignAndWaitForLoading();
        stopActiveCouponEdition();
    }


    // Вложенный класс с удалением акции (тиража) после каждого теста
    @Nested
    class TestsWithCampaignDeleteAfterRun {
        // КЛУБ ДРУЗЕЙ
        @Test
        @SneakyThrows
        @DisplayName("КД: попытка создания акции Скидка без рассылки: выдача + применение (одноразовые купоны)")
        public void tryToCreateFriendsDiscountGiveTakeX1CouponWithoutMailing() {
            goToCouponsTab();
            createFriendsDiscountCampaignX1Coupon();
            checkX1TimeAndDiscountAreVisible();
            checkMainGiveTakeMechanicIsCreated();
            createGiveMechanic();
            selectFirstGiveOrTakeMechanicFromDropdown(0);
            setGivePurchaseSum(10000);
            createTakeMechanic();
            selectFirstGiveOrTakeMechanicFromDropdown(1);
            $(byText("Скидка (проценты)")).click();
            setTakePurchaseSumAndDiscount(20000, 10);
            // Клик на кнопку Активировать
            $(".loading-button").click();
            $(byText("У акции на выдачу отсутствует рассылка о выдаче купонов!"))
                    .shouldBe(enabled, visible);
            deleteDraftCouponEdition();
        }

        // СОЮЗ ОТВАЖНЫХ
        @Test
        @SneakyThrows
        @DisplayName("СО: попытка создания акции Скидка: применение БЕЗ выдачи (одноразовые купоны)")
        public void tryToCreateUnionDiscountOnlyTakeX1Coupon() {
            goToCouponsTab();
            createUnionDiscountCampaignX1Coupon();
            checkX1TimeAndDiscountAreVisible();
            checkMainGiveTakeMechanicIsCreated();
            createTakeMechanicWithoutGive();
            selectFirstGiveOrTakeMechanicFromDropdown(0);
            $(byText("Скидка (проценты)")).click();
            setTakePurchaseSumAndDiscountWhenNoGiveMechanic(20000, 10);
            // Клик на кнопку Активировать
            $(".loading-button").click();
            $(byText("Невозможно активировать тираж - отстствует акция для выдачи купонов!"))
                    .should(appear, visible);
            deleteDraftCouponEdition();
        }

        @Disabled
        @Test
        @SneakyThrows
        @DisplayName("СО: попытка создания акции Скидка без рассылки: выдача + применение (одноразовые купоны)")
        public void tryToCreateUnionDiscountGiveTakeX1CouponWithoutMailing() {
            goToCouponsTab();
            createFriendsDiscountGiveTakeX1Coupon();
            checkX1TimeAndDiscountAreVisible();
            checkMainGiveTakeMechanicIsCreated();
            createGiveMechanic();
            selectFirstGiveOrTakeMechanicFromDropdown(0);
            setGivePurchaseSum(10000);
            createTakeMechanic();
            selectFirstGiveOrTakeMechanicFromDropdown(1);
            $(byText("Скидка (проценты)")).click();
            setTakePurchaseSumAndDiscount(20000, 10);
            // Клик на кнопку Активировать
            $(".loading-button").click();
            $(byText("У акции на выдачу отсутствует рассылка о выдаче купонов!"))
                    .should(appear, visible);
            deleteDraftCouponEdition();
        }

        // ВСЕ УЧАСТНИКИ ПЛ
        @Test
        @SneakyThrows
        @DisplayName("КД+СО: попытка создания акции Скидка: выдача БЕЗ применения (одноразовые купоны)")
        public void tryToCreate2LoyaltyDiscountGiveTakeX1Coupon() {
            goToCouponsTab();
            create2loyaltyDiscountCampaignX1Coupon();
            checkX1TimeAndDiscountAreVisible();
            checkMainGiveTakeMechanicIsCreated();
            createGiveMechanic();
            selectFirstGiveOrTakeMechanicFromDropdown(0);
            setGivePurchaseSum(10000);
            // Клик на кнопку Активировать
            $(".loading-button").click();
            $(byText("Невозможно активировать тираж - отстствует акция для гашения купонов!"))
                    .should(appear, visible);
            deleteDraftCouponEdition();
        }

    }
}


