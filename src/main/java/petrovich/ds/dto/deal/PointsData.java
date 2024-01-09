package petrovich.ds.dto.deal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petrovich.ds.dto.campaign.Action;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@Getter
@Setter
@NoArgsConstructor
public class PointsData {
    private BigDecimal totalGiftPoints = ZERO;
    private BigDecimal accruedGiftPoints = ZERO;
    private BigDecimal writeOfPoints = ZERO;
    private BigDecimal sumOfOnePoint = ZERO;
    private BigDecimal minSpentPoints = ZERO;
    private BigDecimal mainGiftPoints = ZERO;
    private BigDecimal personalPoints = ZERO;
    private List<PersonalPointsDetails> personalPointsDetails;
    private BigDecimal pointsForAction = ZERO;
    private BigDecimal pointsForBigPurchase = ZERO;
    private BigDecimal pointsForSlider = ZERO;
    private BigDecimal pointsOfVendor = ZERO;
    private BigDecimal pointsForMarkup = ZERO;
    private BigDecimal maxSpentPoints = ZERO;
    private BigDecimal maxSpentPointsServices = ZERO;
    private BigDecimal spentPoints = ZERO;
    private boolean payByPointsGoods;
    private boolean payByPointsOrder;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalPointsDetails {
        private String uuid;
        private BigDecimal points;

        public PersonalPointsDetails(Action action) {
            this.uuid = action.getCampaignId();
            this.points = action.getPoints();
        }
    }
}
