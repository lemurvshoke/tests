package petrovich.ds.dto.coupon;

import lombok.*;

@Data
@NoArgsConstructor
public class CouponEditionRequestDTO {

    public String description;
    public String endDate;
    public String id;
    public String loyalty;
    public String name;
    public boolean needGiveaway;
    public String redemptionCampaignType;
    public String redemptionType;
    public String startDate;
}
