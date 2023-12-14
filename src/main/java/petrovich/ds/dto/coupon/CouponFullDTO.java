package petrovich.ds.data.dto.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import petrovich.ds.data.coupon.CouponDelivery;
import petrovich.ds.data.enums.coupon.CouponRedemptionType;
import petrovich.ds.data.enums.coupon.CouponStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CouponFullDTO implements Serializable {

    private static final long serialVersionUID = -468747777548272313L;
    private String id;
    private String externalId;
    private String editionId;
    private String number;
    private CouponStatus status = CouponStatus.NEW;

    private String customerId;
    private String customerPhone;
    private String customerCode;
    private String cardNumber;
    private String campaignId;
    private String saleId;
    private CouponRedemptionType redemptionType;
    private boolean personal;

    private LocalDateTime issueDate = LocalDateTime.now();
    private LocalDateTime redemptionDate;

    private String loyaltyType;
    private CouponDelivery delivery;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponFullDTO coupon = (CouponFullDTO) o;
        return number.equals(coupon.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
