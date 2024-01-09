package petrovich.ds.dto.pdp.integration;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerCartActionData implements Serializable {
    private static final long serialVersionUID = 1513965159907654582L;

    private BigDecimal sumOfOnePoint;
    private List<ProductActionInfo> items;
    private String customerStatus;
}