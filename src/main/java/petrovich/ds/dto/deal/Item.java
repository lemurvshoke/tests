package petrovich.ds.dto.deal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petrovich.ds.dto.campaign.Action;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = -1840310322182424571L;

    private List<Action> actions = new ArrayList<>();
    private BigDecimal newPrice;

    @NotNull
    private String uuid;
    private String supplierUUID;

    @NotNull
    private BigDecimal amount;
    @NotNull
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal totalSum = BigDecimal.ZERO;
    private BigDecimal sliderDiscount = BigDecimal.ZERO;

    @NotNull
    private int rowNumber;

    private boolean bonusSKU;
    private boolean service;

    private Set<String> groupIds = new HashSet<>();

    public void addAction(Action action) {
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
        this.actions.add(action);
    }

    public boolean notContainsCampaign(String campaignId) {
        if (actions != null) {
            return actions.stream().noneMatch(action -> action.getCampaignId().equals(campaignId));
        }
        return true;
    }

    public boolean containsCampaign(String campaignId) {
        if (actions != null) {
            return actions.stream().anyMatch(action -> action.getCampaignId().equals(campaignId));
        }
        return false;
    }

    public boolean notContainsCampaign() {
        return actions == null || actions.isEmpty();
    }

    public void reduceAmount(BigDecimal amount) {
        //получаем "цену" исходя из totalSum
        //уменьшаем кол-во товаров
        //пересчитываем totalSum по "цене"
        //т.о. все изменения стоимости товаров сохранили пропорционально кол-ву товаров
        BigDecimal priceFromTotalSum = this.getTotalSum().divide(this.getAmount(),2, RoundingMode.HALF_UP);
        this.setAmount(this.getAmount().subtract(amount));
        this.setTotalSum(this.getAmount().multiply(priceFromTotalSum));
    }

    public BigDecimal getCurrentPrice() {
        return newPrice != null ? newPrice : price;
    }

    public BigDecimal getDiscountAmount() {
        if (this.newPrice == null) {
            return BigDecimal.ZERO;
        }
        return this.price.subtract(this.newPrice).multiply(this.amount);
    }

    @Override
    public String toString() {
        return "Item{" +
                "uuid='" + uuid + '\'' +
                ", supplierUUID='" + supplierUUID + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", totalSum=" + totalSum +
                ", bonusSKU=" + bonusSKU +
                ", service=" + service +
                '}';
    }
}
