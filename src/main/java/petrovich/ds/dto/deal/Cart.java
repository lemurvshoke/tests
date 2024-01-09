package petrovich.ds.dto.deal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import petrovich.ds.dto.campaign.Action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Cart implements Serializable {

    private static final long serialVersionUID = 7627117023512450916L;

    private String uuid;
    private boolean receipt;
    private String trackId;
    /**
     * Флаг, что данная корзина пришла с сайта, а не создана из реализация от 1С.
     */
    private boolean fromSite;

    private Date date;

    private String number;

    private List<Item> products;

    private List<Action> actions;

    private BigDecimal totalSliderDiscount = BigDecimal.ZERO;
}
