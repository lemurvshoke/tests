package ui;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class BaseUiTest {

    static final String BASE_URL_STAGE = "http://ui.js.a.kluatr.ru";
    static final String LOGIN_URL_STAGE = "http://ui.js.a.kluatr.ru/auth/login";
    static final String BASE_URL_DEV = "http://dmz-decision01.stdp.ru";
    static final String LOGIN_URL_DEV = "http://dmz-decision01.stdp.ru/auth/login";
    protected static final String API_URL_DEV = "http://dmz-decision01.stdp.ru/api/ds";
    protected static String campaignId;

    @BeforeEach
    public void loginAsAdmin() {
        open(LOGIN_URL_DEV);
        $("[formcontrolname='username']").val("test_administrator");
        $("[formcontrolname='password']").val("test_administrator");
        $(byText("Войти")).click();
    }

    public static String getAdminToken() {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Charset", "utf-8")
                .body("username=test_administrator&password=test_administrator")
                .when()
                .post(BASE_URL_DEV + "/api/uaa/auth/token")
                .then()
                .extract()
                .response()
                .body()
                .jsonPath()
                .getString("access_token");
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
                .toList();
       return splitUrl.get(splitUrl.size() - 2);
    }


}
