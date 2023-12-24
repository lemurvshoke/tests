package petrovich.ds.api.coupons;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import petrovich.ds.api.coupons.util.CouponsAPIUtils;
import petrovich.ds.dto.coupon.CouponEditionDTO;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static java.nio.file.Paths.get;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Купоны")
public class CouponsAPITest extends CouponsAPIUtils {

    @BeforeEach
    public void setUp() {
        setBody();
    }

    @Test
    @SneakyThrows
    @DisplayName("Запускаем акцию одноразоввые купоны (выдача + применение)")
    public void CreateCouponEditionGiveTakeX1() {
        createCouponEdition();
        createIssueCampaign();
        populateIssueMechanic("id678issue");
        createApplyCampaign();
        populateApplyMechanic("id678apply");
        addMailingToCampaign("id678issue");
        startCampaign("6576edb86d7f5a03e2b1c688");
        checkCouponEditionIsCreated("6576edb86d7f5a03e2b1c688");
    }







}
