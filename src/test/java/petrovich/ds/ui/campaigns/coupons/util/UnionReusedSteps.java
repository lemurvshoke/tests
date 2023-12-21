package petrovich.ds.ui.campaigns.coupons.util;

import lombok.SneakyThrows;
import org.openqa.selenium.Keys;
import petrovich.ds.ui.campaigns.coupons.CouponsUIMethods;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class UnionReusedSteps extends CouponsUIMethods {
    @SneakyThrows
    // Промокоды СО: создание основной акции Скидка: одноразовые купоны
    public static void createUnionDiscountCampaignX1Coupon() {
        // Добавить механику акции
        addMainMechanic();
        // Тип акции: Скидка
        // Программа лояльности: СО
        $("[formcontrolname='loyalty']").click();
        $("[formcontrolname='loyalty']").press(Keys.ARROW_DOWN).pressEnter();
        setDatepicker();
        // Добавить акцию
        $(".loading-button").click();
        // Проверка значений полей оснойной механики
        checkMainGiveTakeMechanicIsCreated();
        $(byText("Союз отважных")).shouldBe(visible);
        // Проверить что кнопки управления активны
        checkEditDeleteButtonsAreEnabledForMainMechanic();
    }
}
