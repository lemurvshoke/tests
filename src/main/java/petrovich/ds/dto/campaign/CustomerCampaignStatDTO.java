package petrovich.ds.dto.campaign;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Setter
public class CustomerCampaignStatDTO {

    public String id;
    public String name;
    public String type;
    public String startDate;
    public String endDate;
    public String status;
    public String description;
    public boolean participating;
    public boolean activationRequired;
    public int availableActivations;
    public List<String> cities;
    public Double profitValue;
    public String profitType;
    public Double accountedSum;
    public Double accountedAmount;
    public Integer accountedLimit;
    public String accountedLimitType;
    public boolean isCumulative;
    public boolean isAnyCity;

}
