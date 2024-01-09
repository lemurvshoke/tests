package petrovich.ds.dto.mechanic;

import lombok.*;
import petrovich.ds.dto.segment.ProductGroup;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MechanicDTO {

    private Mechanic mechanic;
    private List<ProductGroup> groups;
}
