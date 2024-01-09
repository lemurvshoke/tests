package petrovich.ds.dto.campaign;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CampaignShortDTO {
    public String uuid;
    public String name;
    public String type;
    public String description;
}
