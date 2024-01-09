package petrovich.ds.dto.mechanic;

import lombok.Data;
import lombok.NoArgsConstructor;
import petrovich.ds.dto.campaign.CampaignDTO;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class EditMechanicDTO {

    private CampaignDTO campaign;
    private List<String> warnings = new ArrayList<>();
}
