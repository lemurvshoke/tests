package petrovich.ds.ui.campaigns.coupons.util;

import lombok.SneakyThrows;
import petrovich.ds.ui.campaigns.coupons.BaseCouponsUITest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.ARROW_DOWN;

public class FriendsReusedSteps extends BaseCouponsUITest {
    @SneakyThrows
    // Промокоды КД: создание основной акции Скидка: одноразовые купоны
    public static void createFriendsDiscountCampaignX1Coupon() {
        // Добавить механику акции
        addMainMechanic();
        // Тип акции: Скидка
        // Программа лояльности: КД
        $("[formcontrolname='loyalty']").click();
        $("[formcontrolname='loyalty']").press(ARROW_DOWN).press(ARROW_DOWN).pressEnter();
        setDatepicker();
        // Добавить акцию
        $(".loading-button").click();
        // Проверка значений полей оснойной механики
        checkMainGiveTakeMechanicIsCreated();
        $(byText("Клуб друзей Петровича")).shouldBe(visible);
        // Проверить что кнопки управления активны
        checkEditDeleteButtonsAreEnabledForMainMechanic();
    }
}
