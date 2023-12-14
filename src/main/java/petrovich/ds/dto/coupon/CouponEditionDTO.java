package petrovich.ds.data.dto.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import petrovich.ds.data.dto.User;
import petrovich.ds.data.enums.coupon.CouponLoyalty;
import petrovich.ds.data.enums.coupon.CouponRedemptionType;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CouponEditionDTO {
    private String id;
    private String externalId;
    private String name;
    private String description;
    private User owner;
    private CouponRedemptionType redemptionType;
    private CouponLoyalty loyalty;
    private boolean needGiveaway;
    private CampaignType redemptionCampaignType;
    private LocalDate startDate;
    private LocalDate endDate;

    private CouponCampaignDTO issuingCampaign;
    private CouponCampaignDTO applyingCampaign;

    private boolean started;
    private boolean stopped;

    public boolean getEditable() {
        return externalId == null && !started && !stopped;
    }

    public boolean getDeletable() {
        return externalId == null && !started && !stopped;
    }

}
