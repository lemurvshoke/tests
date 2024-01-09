package petrovich.ds.dto.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductPriceGroupDTO {

    private String name;
    private List<ProductPriceDTO> products;
}
