package petrovich.ds.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@Setter
public class DiscountData {
    private BigDecimal totalPrice = ZERO;
    private BigDecimal couponsDiscount = ZERO;
    private BigDecimal spentPointsDiscount = ZERO;
    private BigDecimal sliderDiscount = ZERO;
    private BigDecimal personalTotalDiscount = ZERO;
    private PersonalDiscountDetails personalDiscountDetails;

    public BigDecimal getTotalPriceWithDiscount() {
        return this.totalPrice.subtract(this.getTotalDiscount());
    }

    public BigDecimal getTotalDiscount() {
        return this.personalTotalDiscount
                .add(this.couponsDiscount)
                .add(this.spentPointsDiscount)
                .add(this.sliderDiscount);
    }

    public void setDetails(String uuid, BigDecimal discount) {
        this.personalDiscountDetails = new PersonalDiscountDetails(uuid, discount);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PersonalDiscountDetails {
        private String uuid;
        private BigDecimal discount;
    }
}
