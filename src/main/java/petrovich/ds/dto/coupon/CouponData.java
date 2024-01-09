package petrovich.ds.dto.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponData {

    private String number;
    private String campaignUUID;
    private String message;
    private boolean valid;
    private boolean applied;
}
