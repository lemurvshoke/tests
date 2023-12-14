package petrovich.ds.data.dto.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import petrovich.ds.data.campaign.mechanic.Mechanic;
import petrovich.ds.data.campaign.segment.SegmentMeta;
import petrovich.ds.data.dto.mailing.CampaignMailingDTO;
import petrovich.ds.data.enums.campaign.CampaignStatus;


import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
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
