package petrovich.ds.ui;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.Keys.*;


public class BaseUITest {

    static final String BASE_URL_DEV = "http://dmz-decision01.stdp.ru";
    static final String BASE_URL_STAGE = "http://ui.js.a.kluatr.ru";
    static final String LOGIN_URL_DEV = "http://dmz-decision01.stdp.ru/auth/login";
    static final String LOGIN_URL_STAGE = "http://ui.js.a.kluatr.ru/auth/login";
    public static String campaignId;

    @BeforeEach
    // Залогиниться как Админ перед началом каждого метода
    public void loginAsAdmin() {
        open(LOGIN_URL_DEV);
        $("[formcontrolname='username']").val("test_administrator");
        $("[formcontrolname='password']").val("test_administrator");
        $(byText("Войти")).click();
    }

    @AfterEach
    public void logOut() {
        $(byText("logout")).click();
    }

    // Выбор даты начала и даты окончания
    public static void setDatepicker() throws InterruptedException {
        // Дата начала = сегодня
        $("[formcontrolname='startDate']").click();
        $(".mat-calendar-body-today").click();
        // Дата окончания = 1 число следующего месяца
        $("[formcontrolname='endDate']").click();
        $(".mat-calendar-next-button").click();
        $(byText("1")).click();
    }

    // Активировать персональную акцию
    public void activateCampaignAndWaitForLoading() {
        $(".loading-button").click();
        $(byText("Остановить")).shouldBe(visible, Duration.ofSeconds(30));
    }

    // Получить id купонной акции (тиража)
    public static String getCampaignId() {
        List<String> splitUrl = Collections
                .list(new StringTokenizer(getWebDriver().getCurrentUrl(), "/"))
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());

        return splitUrl.get(splitUrl.size() - 2);
    }

    // Удалить черновик акции (тиража)
    public void deleteDraftCouponEdition() {
        $(".ds-page-title").click();
        $(byText("more_vert")).click();
        $(byText("delete")).click();
        $("[role='dialog']").shouldHave(text("Вы действительно хотите удалить акцию"));
        $(byText("Да")).click();
    }

    // Попытка удалить активную акцию
    public void tryToDeleteActiveCouponEdition() {
        $("[role='img']").click();
        $("[confirmtitle='Подтверждение удаления']").shouldBe(disabled);

    }

    public void stopActiveCouponEdition() {
        $("[confirmtitle='Подтверждение']").shouldBe(enabled).click();
        $("[role='dialog']").shouldHave(text("Вы действительно хотите остановить акцию"));
        $(byText("Да")).click();
    }


}
