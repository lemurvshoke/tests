package petrovich.ds.dto.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petrovich.ds.dto.segment.Product;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDTO {

    private Product product;
    private BigDecimal price;
}
