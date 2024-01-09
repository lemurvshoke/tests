package petrovich.ds.dto.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petrovich.ds.enums.coupon.CouponApplyStatus;

import java.io.Serializable;
import java.util.Objects;

import static petrovich.ds.enums.coupon.CouponApplyStatus.CONDITIONS_NOT_MET;


@Getter
@Setter
@NoArgsConstructor
public class SaleCoupon implements Serializable {

    private static final long serialVersionUID = 5765132890170491959L;

    private String id;
    private String editionId;
    private String number;
    /**
     * ID акции гашения.
     */
    private String campaignId;
    private CouponApplyStatus applyStatus = CONDITIONS_NOT_MET;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleCoupon coupon = (SaleCoupon) o;
        return number.equals(coupon.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
