package petrovich.ds.dto.mechanic;

import lombok.*;
import petrovich.ds.dto.segment.ProductGroup;


import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
public class MechanicDTO {

    private Mechanic mechanic;
    private List<ProductGroup> groups;
}
