package petrovich.ds.data.campaign.segment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import petrovich.ds.data.sale.model.EventType;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Andrei Vaneev
 * Creation date: 26.06.2020
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class DynamicSegmentConfig implements Serializable {

    private static final long serialVersionUID = 1810222012343984933L;

    private ConfigurationType type = ConfigurationType.CATEGORY;
    private EventType eventType;
    private CustomerCategory customerCategory = CustomerCategory.RETAIL;
    private CustomerType customerType = CustomerType.ALL;
    private boolean hasCard;
    private Set<String> cardTypes;
    private Integer actionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicSegmentConfig that = (DynamicSegmentConfig) o;
        return hasCard == that.hasCard &&
                type == that.type &&
                eventType == that.eventType &&
                customerCategory == that.customerCategory &&
                customerType == that.customerType &&
                Objects.equals(cardTypes, that.cardTypes) &&
                Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, eventType, customerCategory, customerType, hasCard, cardTypes, actionId);
    }

    public enum ConfigurationType {
        EVENT, CATEGORY
    }

    public enum CustomerCategory {
        RETAIL, FRIENDS_CLUB, UNION, ALL, LOYALTY_PROGRAM
    }

    public enum CustomerType {
        ALL, ORGANISATION, INDIVIDUAL
    }
}
