package petrovich.ds.ui.campaigns.coupons;

import petrovich.ds.ui.BaseUITest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.Keys.ARROW_DOWN;

public class CouponsUIMethods extends BaseUITest {
    // Переход на вкладку купоны
    public void goToCouponsTab() {
        $(byText("Акции с промокодами")).click();
        $(".ds-page-title").shouldHave(text("Акции с промокодами"));
        $(byText("Название")).shouldBe(visible);
        $(byText("Период действия")).shouldBe(visible);
        $(byText("Тип бонуса")).shouldBe(visible);
        $(byText("ПЛ")).shouldBe(visible);
        $(byText("Тип промокодов")).shouldBe(visible);
        $(byText("Выдача промокодов")).shouldBe(visible);
        $(byText("Ответственный")).shouldBe(visible);
    }

    // Добавление основной механики
    public static void addMainMechanic() {
        $(byText("Добавить")).click();
        $(".mat-dialog-title").shouldHave(text("Новая акция с промокодами"));
        $("[formcontrolname='name']").val("Выдача и применение купонов " + LocalDate.now());
        $("[formcontrolname='description']").val("Акция на выдачу и применение " + LocalDate.now());
    }

    // Выбор типа основной акции - Баллы (дефолтное значение = Скидка)
    public static void setCampaignAsPoints() {
        $("[formcontrolname='redemptionCampaignType']").click();
        $("[formcontrolname='redemptionCampaignType']").pressEnter();
    }

    // Проверка общих полей для основных акций
    public static void checkMainGiveTakeMechanicIsCreated() {
        campaignId = getCampaignId();
        $(".ds-page-title").shouldHave(text("Выдача и применение купонов " + LocalDate.now()));
        $(".ds-page-subtitle").shouldHave(text("Акция на выдачу и применение " + LocalDate.now()));
        $$(".ds-property-value").get(3).shouldHave(text(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))).shouldBe(visible);
        // Дата окончания акции = 3 число следующего месяца
        $$(".ds-property-value").get(4).shouldHave(text(LocalDate.of(
                                2023, Month.of(LocalDate.now().getMonthValue()), 1)
                        .plusMonths(1)
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .shouldBe(visible);
        $$(".ds-property-value").get(5).shouldHave(text("Администратор"));
    }

    // Варианты методов проверки механики основной акции
    public void checkX1TimeAndDiscountAreVisible() {
        $(byText("Одноразовый")).shouldBe(visible);
        $(byText("Скидка")).shouldBe(visible);
    }

    public void checkXManyTimeAndPointsAreVisible() {
        $(byText("Многоразовый")).shouldBe(visible);
        $(byText("Баллы")).shouldBe(visible);
    }

    public void checkX1TimeAndPointsAreVisible() {
        $(byText("Одноразовый")).shouldBe(visible);
        $(byText("Баллы")).shouldBe(visible);
    }

    public void checkXManyTimeAndDiscountAreVisible() {
        $(byText("Многоразовый")).shouldBe(visible);
        $(byText("Скидка")).shouldBe(visible);
    }

    // Создание механики выдачи купонов
    public void createGiveMechanic() {
        addGiveOrTakeMechanic("Новая акция на выдачу", "Give: Короткое название", "Give: Описание");
        $(".ds-section-title").shouldHave(text("Выдача промокодов"));
        checkCampaignIsDraftAndEditDeleteButtonsAreEnabled(1, 2, 1);
    }

    // Создание механики применения купонов
    public void createTakeMechanic() {
        addGiveOrTakeMechanic("Новая акция на применение", "Take: Короткое название", "Take: Описание");
        $$(".ds-section-title").get(1).shouldHave(text("Применение промокодов"));
        checkCampaignIsDraftAndEditDeleteButtonsAreEnabled(2, 3, 2);
    }

    public void createTakeMechanicWithoutGive() {
        addTakeMechanicWithoutGive("Новая акция на применение", "Take: Короткое название", "Take: Описание");
        $(".ds-section-title").shouldHave(text("Применение промокодов"));
        checkCampaignIsDraftAndEditDeleteButtonsAreEnabled(1, 2, 1);
    }

    // Добавление механики выдачи или применения и проверка полей
    public void addGiveOrTakeMechanic(String title, String shortName, String description) {
        $(byText("Создать механику")).click();
        $(".mat-dialog-title").shouldHave(text(title));
        $("[formcontrolname='shortName']").val(shortName);
        $("[formcontrolname='description']").val(description);
        $("[form='coupon-mechanic-create-form']").click();
    }

    public void addTakeMechanicWithoutGive(String title, String shortName, String description) {
        $$(byText("Создать механику")).get(1).click();
        $(".mat-dialog-title").shouldHave(text(title));
        $("[formcontrolname='shortName']").val(shortName);
        $("[formcontrolname='description']").val(description);
        $("[form='coupon-mechanic-create-form']").click();
    }

//     Выбор 1 шаблона акции в дропдауне акций на применение
//     Передать 0 для дропдауна шаблона акции на Выдачу
//     Передать 1 для дропдауна шаблона акции на Применение
    public void selectFirstGiveOrTakeMechanicFromDropdown(int dropdownNumber) {
        $$("[formcontrolname='ruleTemplate']").get(dropdownNumber).click();
        $$("[formcontrolname='ruleTemplate']").get(dropdownNumber).pressEnter();
        $(byText("Общие настройки механики")).shouldBe(visible);
        $(".mat-button-disabled").shouldBe(disabled);
    }

    // Выбор 2 шаблона акции в дропдауне акций на применение
    public void selectSecondGiveOrTakeMechanicFromDropdown(int dropdownNumber) {
        $$("[formcontrolname='ruleTemplate']").get(dropdownNumber).click();
        $$("[formcontrolname='ruleTemplate']").get(dropdownNumber).press(ARROW_DOWN).click();
        $(byText("Общие настройки механики")).shouldBe(visible);
        $(".mat-button-disabled").shouldBe(disabled);
    }

    // Создание рассылки акции выдачи
    public void createGiveMailing() throws InterruptedException {
        // Переход на вкладку коммуникации
        $(byText("Коммуникации")).click();
        $(".ds-section-title").shouldHave(text("Рассылки акции выдачи"));
        // Всплывающее окно Механика успешно запущена исчезло
        $(".snackbar-success").should(disappear, Duration.ofSeconds(10));
        // Кликнуть Добавить рассылку
        $(byText("Добавить")).click();
        $(".mat-dialog-title").shouldHave(text("Добавить рассылку"));
        // Выбор типа рассылки
        $("[placeholder='Выберите тип']").click();
        // Выбор шаблона рассылки
        $("[placeholder='Выберите тип']").pressTab();
        $(byText("1.1.4")).click();
        // Кликнуть чекбокс Заполнить вручную
        $(".mat-checkbox-inner-container").click();
        // Ввести значение в поле шаблона и проверить, что рассылка создалась
        $("[formcontrolname='value']").val("куку вася!").pressEnter();
        $(byText("Информация о выдаче купона")).shouldBe(visible);
        $(byText("1.1.4")).shouldBe(visible);
        $(byText("Не отправлена")).shouldBe(visible);
    }

    // Основная механика: проверить активность кнопок Изменить/ Удалить
    public static void checkEditDeleteButtonsAreEnabledForMainMechanic() {
        $(".loading-button").shouldBe(interactable);
        $$(byText("Изменить")).shouldBe(size(1));
        $(byText("Изменить")).shouldBe(interactable);
        $$(byText("Удалить")).shouldBe(size(0));
        $(byText("Удалить")).shouldBe(hidden);
    }

    // Механика выдачи: установить сумму, активировать и проверить активацию
    public void setGivePurchaseSum(int sum) {
        $("[formcontrolname='value']").val(String.valueOf(sum)).pressEnter();
        $("[type='submit']").click();
        $(byText("Посмотреть правило")).shouldBe(interactable);
    }

    // Механика применения: установить сумму, скидку, активировать и проверить активацию
    public void setTakePurchaseSumAndDiscount(int sum, int discount) {
        $$("[formcontrolname='value']").get(1).val(String.valueOf(sum)).pressEnter();
        $$("[formcontrolname='value']").get(2).val(String.valueOf(discount)).pressEnter();
        $("[type='submit']").click();
        $(byText("Посмотреть правило")).shouldBe(interactable);
    }

    // Механика применения (отсутствует акция выдачи): установить сумму, скидку, активировать и проверить активацию
    public void setTakePurchaseSumAndDiscountWhenNoGiveMechanic(int sum, int discount) {
        $("[formcontrolname='value']").val(String.valueOf(sum)).pressEnter();
        $$("[formcontrolname='value']").get(1).val(String.valueOf(discount)).pressEnter();
        $("[type='submit']").click();
        $(byText("Посмотреть правило")).shouldBe(interactable);
    }

    // Проверить статус акции = черновик и кнопки Изменить/ Удалить
    public void checkCampaignIsDraftAndEditDeleteButtonsAreEnabled(int draftSize, int editSize, int deleteSize) {
        $$(byText("Черновик")).shouldBe(size(draftSize));
        $$(byText("Изменить")).filterBy(interactable).shouldBe(size(editSize));
        $$(byText("Удалить")).filterBy(interactable).shouldBe(size(deleteSize));
    }


}

