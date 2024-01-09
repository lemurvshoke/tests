package petrovich.ds.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 2309457943468533310L;
    // customer data
    private String uuid;
    private String code;
    private String cardNumber;
    private String name;
    private String mailingName;
    private String url;
    private String phone;
    private String mail;
    private String inn;
    private boolean isActuallyParticipate;
    private boolean isNoticed;
    @JsonIgnore
    private boolean isCommon = false;
    //
    private BigDecimal totalSum;
    private BigDecimal coefficient;

}
