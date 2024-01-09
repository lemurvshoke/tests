package petrovich.ds.dto.campaign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FullDescription {

    private String value;
    private List<ProductPriceGroupDTO> productGroups;

    public static FullDescription empty() {
        return new FullDescription();
    }
}
