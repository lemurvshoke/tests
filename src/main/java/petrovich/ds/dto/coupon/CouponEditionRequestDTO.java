package petrovich.ds.dto.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode
@NoArgsConstructor
public class CouponEditionRequestDTO {

    public String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate endDate;
    public String id;
    public String loyalty;
    public String name;
    public boolean needGiveaway;
    public String redemptionCampaignType;
    public String redemptionType;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate startDate;
}
