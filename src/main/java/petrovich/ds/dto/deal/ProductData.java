package petrovich.ds.dto.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;


@Getter
@Setter
@ToString
public class ProductData {
    private List<String> availableCampaigns;
    private String productGuid;
    private int rowNumber;
    private BigDecimal totalProductPrice = ZERO;
    private BigDecimal totalProductDiscount;
    @JsonProperty("isFixPrice")
    private boolean fixPrice;
    private boolean service;
    private List<ProductRow> details;

    @Getter
    @Setter
    @ToString
    public static class ProductRow {
        private String campaignUUID;
        private BigDecimal price;
        private BigDecimal firstPrice;
        private BigDecimal discount;
        private BigDecimal personalDiscount;
        private BigDecimal discountByCoupons;
        private BigDecimal discountByPoints;
        private BigDecimal discountBySlider;
        private BigDecimal qty;
    }
}
