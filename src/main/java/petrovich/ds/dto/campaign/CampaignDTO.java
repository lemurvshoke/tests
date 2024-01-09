package petrovich.ds.dto.campaign;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import petrovich.ds.dto.User;
import petrovich.ds.dto.mailing.CampaignMailingDTO;
import petrovich.ds.dto.mechanic.Mechanic;
import petrovich.ds.dto.segment.SegmentDTO;
import petrovich.ds.enums.campaign.CampaignStatus;
import petrovich.ds.enums.campaign.CampaignType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
public class CampaignDTO {

    public String id;
    @NotEmpty
    @Size(min = 3, max = 255)
    public String name;
    @NotEmpty
    @Size(min = 3, max = 50)
    public String shortName;
    @NotEmpty
    @Size(max = 255)
    public String description;
    public String descriptionTemplate;
    public FullDescription fullDescription;
    @NotNull
    public CampaignType type;
    public CampaignStatus status = CampaignStatus.DRAFT;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate startDate;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate endDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    public LocalDateTime creationDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    public LocalDateTime activationDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    public LocalDateTime terminationDate;
    public SegmentDTO segment;
    public Mechanic mechanic;
    public List<CampaignMailingDTO> mailings;
    public boolean notificationEnabled = true;
    public boolean personalCabinetEnabled = true;
    public User owner;
    public boolean needGiveaway;
    public boolean started;
    public boolean stopped;
    public boolean editable;
    public boolean deletable;
}
