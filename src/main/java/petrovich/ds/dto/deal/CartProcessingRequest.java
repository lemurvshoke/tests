package petrovich.ds.dto.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Класс запроса на расчет/пересчет скидок.
 *
 * @author Andrei Vaneev
 * Creation date: 19.03.2020
 */
@Getter
@Setter
@ToString
public class CartProcessingRequest {

    private String cityGuid;
    @JsonProperty("isMobile")
    private boolean mobile;
    private boolean fromSite;
    @NotEmpty
    private String trackId;
    @Valid
    @NotNull
    private CartCustomerDetails user;
    @Valid
    @NotNull
    private List<Product> products;
    @Valid
    @NotNull
    private List<Product> gifts;
    @Valid
    @NotNull
    private Discount discount;
    private String promocode;
    private SaleChannel channel = SaleChannel.SITE;

    @Getter
    @Setter
    public static class Product {
        @NotEmpty
        private String productGuid;
        @NotNull
        private BigDecimal retailPrice;
        @NotNull
        private BigDecimal currentPrice;
        @NotNull
        private BigDecimal qty;
        @JsonProperty("isService")
        private boolean service;
        private BigDecimal deliveryDiscount;
        private int rowNumber;
    }

    @Getter
    @Setter
    public static class Discount {
        @NotNull
        private BigDecimal sliderPosition;
        @NotNull
        private BigDecimal spentPoints;
        @NotNull
        private BigDecimal couponsDiscount;
    }
}
