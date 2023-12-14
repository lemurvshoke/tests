package petrovich.ds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import petrovich.ds.campaign.FullDescription;
import petrovich.ds.campaign.mechanic.Mechanic;
import petrovich.ds.dto.mailing.CampaignMailingDTO;
import petrovich.ds.dto.segment.SegmentDTO;
import petrovich.ds.enums.campaign.CampaignActivationType;
import petrovich.ds.enums.campaign.CampaignStatus;
import petrovich.ds.enums.campaign.CampaignType;
import petrovich.ds.enums.mailing.MailingType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CampaignDTO {

    private String id;
    @NotEmpty
    @Size(min = 3, max = 255)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String shortName;
    @NotEmpty
    @Size(max = 255)
    private String description;
    private String descriptionTemplate;
    private FullDescription fullDescription;
    @NotNull
    private CampaignType type;
    private CampaignStatus status = CampaignStatus.DRAFT;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private LocalDateTime creationDate;
    private LocalDateTime activationDate;
    private LocalDateTime terminationDate;
    private SegmentDTO segment;
    private Mechanic mechanic;
    private List<CampaignMailingDTO> mailings;
    private boolean notificationEnabled = true;
    private boolean personalCabinetEnabled = true;

    @JsonIgnore
    public CampaignMailingDTO getMailingByType(MailingType type) {
        return Optional.ofNullable(mailings).orElse(List.of())
                .stream()
                .filter(m -> m.getType() == type)
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public CampaignMailingDTO getMailingById(String id) {
        return Optional.ofNullable(mailings).orElse(List.of())
                .stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public CampaignActivationType getActivationType() {
        return Optional.ofNullable(mailings).orElse(List.of())
                .stream()
                .filter(m -> m.getType() == MailingType.START)
                .map(CampaignMailingDTO::getActivationType)
                .findFirst()
                .orElse(CampaignActivationType.NONE);
    }
}
