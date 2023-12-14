package ui.campaigns.coupons.util;

import ui.campaigns.coupons.BaseCouponCampaignTest;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FriendsTestSteps extends BaseCouponCampaignTest {
    // Промокоды КД: создание основной акции Скидка: Выдача + гашение (одноразовые купоны)
    public static void createFriendsDiscountGiveTakeX1Coupon() {
        // Добавить механику акции
        addMainMechanic();
        // Тип акции: Скидка
        setActionAsDiscount();
        // Программа лояльности: КД
        $("[formcontrolname='loyalty']").click();
        $("[formcontrolname='loyalty']").pressEnter();
        setDatepicker();
        // Добавить акцию
        $(".loading-button").click();
        // Проверка значений полей оснойной механики
        checkMainGiveTakeMechanicIsCreated();
        // Проверить что кнопки управления активны
        checkEditDeleteButtonsAreEnabledForMainMechanic();
    }
}
