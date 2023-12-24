package petrovich.ds.dto.mechanic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import petrovich.ds.campaign.mechanic.FieldType;
import petrovich.ds.campaign.mechanic.RuleData;
import petrovich.ds.campaign.mechanic.TemplateField;
import petrovich.ds.enums.campaign.CampaignSubtype;
import petrovich.ds.enums.campaign.LoyaltyType;
import petrovich.ds.enums.campaign.Multiplicity;
import petrovich.ds.enums.segment.ActionType;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class Mechanic implements Serializable {

    private static final long serialVersionUID = 7935164899118435372L;

    @Valid
    @NotNull
    private RuleData ruleData;

    @NotNull
    private Multiplicity multiplicity;
    //Nразовость, учитывается только если multiplicity == ONCE
    private int times;
    private boolean isForeach;
    private boolean isCumulative;

    private String cities;

    private ActionType type;
    private LoyaltyType loyaltyType = LoyaltyType.NONE;

    @NotNull
    private CampaignSubtype campaignSubtype;

    @Transient
    public List<String> getCityIds() {
        if (cities != null && !cities.isEmpty()) {
            return Arrays.asList(cities.split(";"));
        }
        return Collections.emptyList();
    }

    public LoyaltyType getLoyaltyType() {
        if (loyaltyType == null) {
            return LoyaltyType.NONE;
        }
        return loyaltyType;
    }

    @Transient
    @JsonIgnore
    public Set<TemplateField> getAllProductGroups() {
        return Optional.ofNullable(this.ruleData).stream()
                .flatMap(r -> r.getVariables().stream())
                .filter(f -> f.getFieldType() == FieldType.PRODUCT_GROUP)
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    public boolean isMulti() {
        return times > 1 || multiplicity == Multiplicity.ALWAYS;
    }
}
