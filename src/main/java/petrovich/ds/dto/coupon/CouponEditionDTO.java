package petrovich.ds.dto.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import petrovich.ds.dto.User;
import petrovich.ds.enums.campaign.CampaignType;
import petrovich.ds.enums.coupon.CouponLoyalty;
import petrovich.ds.enums.coupon.CouponRedemptionType;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CouponEditionDTO {
    public String id;
    public String externalId;
    public String name;
    public String description;
    public User owner;
    public CouponRedemptionType redemptionType;
    public CouponLoyalty loyalty;
    public boolean needGiveaway;
    public CampaignType redemptionCampaignType;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate endDate;
    public boolean editable;
    public boolean deletable;

    public CouponCampaignDTO issuingCampaign;
    public CouponCampaignDTO applyingCampaign;

    public boolean started;
    public boolean stopped;

    public boolean getEditable() {
        return externalId == null && !started && !stopped;
    }

    public boolean getDeletable() {
        return externalId == null && !started && !stopped;
    }

}
