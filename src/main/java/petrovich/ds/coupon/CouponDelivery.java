package petrovich.ds.data.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class CouponDelivery {

    private LocalDateTime sendDate;
    private LocalDateTime errorDate;
    private String errorMessage;

}
