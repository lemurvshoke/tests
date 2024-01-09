package petrovich.ds.dto.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode
@NoArgsConstructor
public class CouponCampaignRequestDTO {

    public String description;
    public String editionId;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate endDate;
    public String id;
    public String name;
    public String shortName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate startDate;
    public String type;
}
