package petrovich.ds.dto.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import petrovich.ds.campaign.mechanic.Mechanic;
import petrovich.ds.campaign.segment.SegmentMeta;
import petrovich.ds.dto.mailing.CampaignMailingDTO;
import petrovich.ds.enums.campaign.CampaignStatus;
import petrovich.ds.enums.campaign.CampaignType;

import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
public class CouponCampaignDTO {
    public String id;
    public String name;
    public String shortName;
    public String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate endDate;
    public CampaignStatus status;
    public CampaignType type;
    public SegmentMeta segment;
    public Mechanic mechanic;
    public Collection<CampaignMailingDTO> mailings;


}
