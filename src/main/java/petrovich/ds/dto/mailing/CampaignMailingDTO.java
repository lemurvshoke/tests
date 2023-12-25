package petrovich.ds.dto.mailing;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import petrovich.ds.enums.campaign.CampaignActivationType;
import petrovich.ds.enums.mailing.MailingType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignMailingDTO {

    private String id;
    private String campaignId;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate sendDate;
    private boolean sent;
    @NotNull
    private MailingType type;
    @NotNull
    private MailingTemplateDTO template;
    @Valid
    @NotEmpty
    private List<MailingFieldMappingDTO> mappings;
    private CampaignActivationType activationType;
    private Boolean isCopy;

    @JsonIgnore
    public Map<String, Boolean> getRequiredMap() {
        return Optional.ofNullable(template).map(MailingTemplateDTO::getFields).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(MailingTemplateFieldDTO::getName, MailingTemplateFieldDTO::isRequired, (v1, v2) -> v1));

    }

}
