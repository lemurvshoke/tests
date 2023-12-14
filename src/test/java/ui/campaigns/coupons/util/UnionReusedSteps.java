package ui.campaigns.coupons.util;

import org.openqa.selenium.Keys;
import ui.campaigns.coupons.BaseCouponCampaignTest;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UnionTestSteps extends BaseCouponCampaignTest {
    // Промокоды СО: создание основной акции Скидка: Выдача + гашение (одноразовые купоны)
    public static void createUnionDiscountGiveTakeX1Coupon() {
        // Добавить механику акции
        addMainMechanic();
        // Тип акции: Скидка
        setActionAsDiscount();
        // Программа лояльности: СО
        $("[formcontrolname='loyalty']").click();
        $("[formcontrolname='loyalty']").press(Keys.UP).pressEnter();
        setDatepicker();
        // Добавить акцию
        $(".loading-button").click();
        // Проверка значений полей оснойной механики
        checkMainGiveTakeMechanicIsCreated();
        // Проверить что кнопки управления активны
        checkEditDeleteButtonsAreEnabledForMainMechanic();
    }
}
