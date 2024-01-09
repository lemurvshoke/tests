package petrovich.ds.dto.segment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import petrovich.ds.dto.customer.CustomerDTO;
import petrovich.ds.enums.mailing.MindboxValidationStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentDataDTO implements Serializable {

    private static final long serialVersionUID = 1814956326365181588L;

    private String id;

    private String campaignId;

    //promo data
    private BigDecimal limit;
    private BigDecimal value;
    private BigDecimal dokupi;
    private String city;
    private String channel;
    //customer data
    private CustomerDTO customer;
    private boolean validInDS;
    private boolean valid;
    private boolean blocked;
    private boolean organization;
    private LocalDateTime blockDate;
    //product data
    private List<ProductGroup> productGroups;
    private String comment;
    //for test mailing
    private String testRecipientPhone;
    private MindboxValidationStatus validInMB = MindboxValidationStatus.NOT_CHECKED;

    @JsonIgnore
    public BigDecimal value() {
        if (value != null) {
            return value;
        }
        return null;
    }

    @JsonIgnore
    public ProductGroup getProductGroup(String id) {
        if (productGroups != null) {
            return productGroups.stream()
                    .filter(productGroup -> id.equals(productGroup.getId()))
                    .findFirst().orElse(null);
        }
        return null;
    }
}
