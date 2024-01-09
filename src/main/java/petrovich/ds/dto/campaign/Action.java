package petrovich.ds.dto.campaign;

import petrovich.ds.dto.deal.Sale;
import petrovich.ds.enums.campaign.LoyaltyType;
import petrovich.ds.enums.segment.ActionType;

import java.math.BigDecimal;
import java.util.Set;

//todo implement all types of action (points, discount, etc)
public interface Action {

    String getId();

    String getCampaignId();

    String getCustomerId();

    String getDealId();

    ActionType getActionType();

    LoyaltyType getLoyaltyType();

    Set<String> getProductGroup();

    boolean isIgnore();

    boolean isMainGroup();

    Action setMainGroup(boolean mainGroup);

    BigDecimal getPoints();

    BigDecimal getAccounted();

    Sale apply(Sale sale);
}
