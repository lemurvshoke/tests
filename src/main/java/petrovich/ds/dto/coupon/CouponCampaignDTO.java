package petrovich.ds.dto.coupon;

import lombok.*;
import lombok.experimental.Accessors;
import petrovich.ds.campaign.mechanic.Mechanic;
import petrovich.ds.campaign.segment.SegmentMeta;
import petrovich.ds.dto.mailing.CampaignMailingDTO;
import petrovich.ds.enums.campaign.CampaignStatus;
import petrovich.ds.enums.campaign.CampaignType;
import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CouponCampaignDTO {
    private String id;
    private String name;
    private String shortName;
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;
    private CampaignStatus status;

    private CampaignType type;
    private SegmentMeta segment;
    private Mechanic mechanic;
    private Collection<CampaignMailingDTO> mailings;
}
