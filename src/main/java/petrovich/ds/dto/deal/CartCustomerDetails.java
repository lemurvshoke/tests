package petrovich.ds.dto.deal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Andrei Vaneev
 * Creation date: 27.03.2020
 */
@Getter
@Setter
@ToString
public class CartCustomerDetails {
    private String userId;
    private String sessionId;
    private String cardNumber;
    private String phoneNumber;
    private String inn;
    private String kpp;
    private String contractorGuid;
    private String cardType;
    private String cardStatus;
    private BigDecimal cardPoints;
    @JsonProperty("isSliderAvailable")
    private boolean sliderAvailable;
    @JsonProperty("isAuthorizedByPin")
    private boolean authorizedByPin;

    @JsonIgnore
    public boolean hasCard() {
        return this.cardNumber != null && !this.cardNumber.isEmpty();
    }

    @JsonIgnore
    public boolean hasCustomerId() {
        return this.contractorGuid != null && !this.contractorGuid.isEmpty();
    }
}
