package petrovich.ds.dto.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import petrovich.ds.dto.User;
import petrovich.ds.enums.campaign.CampaignType;
import petrovich.ds.enums.coupon.CouponLoyalty;
import petrovich.ds.enums.coupon.CouponRedemptionType;


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
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
    private boolean editable;
    private boolean deletable;

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
