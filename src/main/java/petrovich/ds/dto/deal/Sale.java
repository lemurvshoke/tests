package petrovich.ds.dto.deal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petrovich.ds.dto.coupon.SaleCoupon;
import petrovich.ds.dto.pdp.integration.CustomerCartActionData;
import petrovich.ds.dto.pdp.sale.TriggerDocument;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Sale implements Serializable {

    private static final long serialVersionUID = -1764856049319650102L;

    private String id;
    private String trackId;
    private String mainDocumentUUID;
    private String mainOrderUUID;

    private String customerUUID;
    private String subdivisionUUID;
    private String cityUUID;
    private String channel;

    private LocalDateTime date;
    private LocalDateTime changedDate;
    private LocalDateTime triggerDate;

    private String status;

    private List<Cart> carts = new ArrayList<>();
    private List<Cart> siteCarts = new ArrayList<>();

    private boolean retail;
    private String phoneNumber;
    private String previousPhoneNumber;

    private boolean fromSite;
    private boolean refund;
    private BigDecimal totalSum;

    private String cardNumber;

    private String customerStatus;
    private String cardType;
    private boolean paidBySBP;
    private SaleCoupon coupon;
    private List<Item> allItems;
    private CustomerCartActionData aggregation;
    private TriggerDocument triggerDocument;
    private List<String> shipmentIds;
    private List<String> refundIds;
    private boolean refundWithoutShipment;
    private boolean preview;
}
