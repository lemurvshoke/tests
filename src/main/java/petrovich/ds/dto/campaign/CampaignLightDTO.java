package petrovich.ds.dto.campaign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import petrovich.ds.dto.mechanic.TemplateField;
import petrovich.ds.dto.User;
import petrovich.ds.enums.campaign.CampaignStatus;
import petrovich.ds.enums.campaign.CampaignType;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CampaignLightDTO {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private String ruleTemplateRus;
    private CampaignType type;
    private CampaignStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
    private User owner;
    private boolean isCommon;
    private boolean isProductGroupExists;
    private List<String> cities = new ArrayList<>();

    @JsonIgnore
    private List<String> cityIds = new ArrayList<>();
    @JsonIgnore
    private String ruleTemplate;
    @JsonIgnore
    private List<TemplateField> variables;
}
