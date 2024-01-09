package petrovich.ds.dto.pdp.integration;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductActionInfo implements Serializable {
    private static final long serialVersionUID = -8606796879443957624L;

    private String productUUID;
    private BigDecimal pointsFromVendor;
    private BigDecimal actionCoefficient;
    private String accumulationType;

    public ProductActionInfo(String productUUID, BigDecimal actionCoefficient) {
        this.productUUID = productUUID;
        this.actionCoefficient = actionCoefficient;
    }

    public ProductActionInfo(String productUUID, BigDecimal actionCoefficient, String accumulationType) {
        this(productUUID, actionCoefficient);
        this.accumulationType = accumulationType;
    }

    public ProductActionInfo(String productUUID, BigDecimal pointsFromVendor, BigDecimal actionCoefficient) {
        this(productUUID, actionCoefficient);
        this.pointsFromVendor = pointsFromVendor;
    }

}
