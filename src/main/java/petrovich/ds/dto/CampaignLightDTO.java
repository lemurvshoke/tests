package petrovich.ds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import petrovich.ds.campaign.mechanic.TemplateField;
import petrovich.ds.enums.campaign.CampaignStatus;
import petrovich.ds.enums.campaign.CampaignType;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
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
