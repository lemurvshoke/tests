package petrovich.ds.ui.campaigns.coupons.util;

import lombok.SneakyThrows;
import petrovich.ds.ui.campaigns.coupons.BaseCouponsUITest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BothLoyaltyReusedSteps extends BaseCouponsUITest {
    // Промокоды КД+СО: создание основной акции Скидка: одноразовые купоны
    @SneakyThrows
    public static void create2loyaltyDiscountCampaignX1Coupon() {
        // Добавить механику акции
        addMainMechanic();
        // Тип акции: Скидка
        // Программа лояльности: Все участники ПЛ
        setDatepicker();
        // Добавить акцию
        $(".loading-button").click();
        // Проверка значений полей оснойной механики
        checkMainGiveTakeMechanicIsCreated();
        $(byText("Все участники ПЛ")).shouldBe(visible);
        // Проверить что кнопки управления активны
        checkEditDeleteButtonsAreEnabledForMainMechanic();
    }
}
