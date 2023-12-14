package ui.coupon;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;
import ui.BaseUiTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseCouponCampaignTest extends BaseUiTest {
    // Переход на вкладку купоны
    public static void goToCouponsTab() {
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

    // Проверка общих полей для основных акций
    public static void checkMainGiveTakeMechanicIsCreated() {
        $(".ds-page-title").shouldHave(text("Выдача и гашение купонов " + LocalDate.now()));
        $(".ds-page-subtitle").shouldHave(text("Акция на выдачу и гашение " + LocalDate.now()));
        $$(".ds-property-value").get(3).shouldHave(text(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))).shouldBe(visible);
        // Дата окончания акции = 3 число следующего месяца
        $$(".ds-property-value").get(4).shouldHave(text(LocalDate.of(
                                2023, Month.of(LocalDate.now().getMonthValue()), 3)
                        .plusMonths(1)
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .shouldBe(visible);
        $$(".ds-property-value").get(5).shouldHave(text("Администратор"));
    }

        // Создание механики выдачи купонов
        public void createGiveMechanic() {
            addGiveMechanic();
            $$(byText("Черновик")).shouldBe(size(1));
            $(".ds-section-title").shouldHave(text("Выдача промокодов"));
            $$(byText("Изменить")).shouldBe(size(2));
            $$(byText("Изменить")).get(1).shouldBe(enabled);
            $$(byText("Удалить")).shouldBe(size(2));
        }

        // Создание механики гашения купонов
        public void createTakeMechanic() {
            addTakeMechanic();
            $$(byText("Черновик")).shouldBe(size(2));
            $$(".ds-section-title").get(1).shouldHave(text("Применение промокодов"));
            $$(byText("Изменить")).shouldBe(size(3));
            $$(byText("Изменить")).get(2).shouldBe(enabled);
            $$(byText("Удалить")).shouldBe(size(3));
        }

        // Добавление механики выдачи и проверка полей
        public void addGiveMechanic () {
            $(byText("Создать механику")).click();
            $("[role='dialog']").should(enabled);
            $(".mat-dialog-title").shouldHave(text("Новая акция на выдачу"));
            $("[formcontrolname='shortName']").val("Give: Короткое название");
            $("[formcontrolname='description']").val("Give: Описание");
            $("[form='coupon-mechanic-create-form']").click();
        }

        // Добавление механики гашения и проверка полей
        public void addTakeMechanic() {
            $(byText("Создать механику")).click();
            $(".mat-dialog-title").shouldHave(text("Новая акция на применение"));
            $("[formcontrolname='shortName']").val("Take: Короткое название");
            $("[formcontrolname='description']").val("Take: Описание");
            $("[form='coupon-mechanic-create-form']").click();
        }

        // Выбор 1 опции в дропдауне акций на выдачу и гашение
        public void selectFirstGiveOrTakeMechanicFromDropdown() {
            $("[formcontrolname='ruleTemplate']").click();
            $("[formcontrolname='ruleTemplate']").pressEnter();
        }

        // Выбор 2 опции в дропдауне акций на выдачу и гашение
        public void selectSecondGiveOrTakeMechanicFromDropdown() {
            $("[formcontrolname='ruleTemplate']").click();
            $("[formcontrolname='ruleTemplate']").press(Keys.DOWN).pressEnter();
        }

        // Выбор типа основной акции - Баллы
        public void setActionAsPoints() {
            $("[formcontrolname='redemptionCampaignType']").click();
            $("[formcontrolname='redemptionCampaignType']").pressEnter();
        }

        // Выбор типа основной акции - Скидка
        public static void setActionAsDiscount() {
            $("[formcontrolname='redemptionCampaignType']").click();
            $("[formcontrolname='redemptionCampaignType']").press(Keys.DOWN).pressEnter();
        }

        public static void checkEditDeleteButtonsAreEnabledForMainMechanic () {
            $$(byText("Изменить")).shouldBe(size(1));
            $(byText("Изменить")).shouldBe(enabled);
            $$(byText("Удалить")).shouldBe(size(0));
            $(byText("Удалить")).shouldBe(hidden);
        }

        // Выбор даты начала и даты окончания
        public static void setDatepicker() {
            // Дата начала = сегодня
            $("[formcontrolname='startDate']").click();
            $(".mat-calendar-body-today").click();
            // Дата окончания = 3 число следующего месяца
            $("[formcontrolname='endDate']").click();
            $(".mat-calendar-next-button").click();
            $(byText("3")).click();
        }

        // Добавление основной механики
        public static void addMainMechanic() {
            $(byText("Добавить")).click();
            $(".mat-dialog-title").shouldHave(text("Новая акция с промокодами"));
            $("[formcontrolname='name']").val("Выдача и гашение купонов " + LocalDate.now());
            $("[formcontrolname='description']").val("Акция на выдачу и гашение " + LocalDate.now());
        }
    }

