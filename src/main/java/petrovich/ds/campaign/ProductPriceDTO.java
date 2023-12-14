package ru.ptr.ds.data.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ptr.ds.data.campaign.segment.Product;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDTO {

    private Product product;
    private BigDecimal price;
}