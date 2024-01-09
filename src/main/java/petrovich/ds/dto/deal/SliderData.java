package petrovich.ds.dto.deal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class SliderData {
    private BigDecimal absolute = ZERO;
    private BigDecimal from = ZERO;
    private BigDecimal to = ZERO;
    private BigDecimal leftBorder = ZERO;
    private BigDecimal rightBorder = ZERO;
    private BigDecimal zeroValue = ZERO;
    private BigDecimal sumInZero = ZERO;
}
