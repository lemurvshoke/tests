package petrovich.ds.enums.campaign;

import lombok.Getter;

@Getter
public enum CampaignType {
    POINTS("Баллы"),
    COUPONS("Купоны"),
    DISCOUNT("Скидка"),
    SPAM("Рассылка"),
    GIFT("Подарок"),
    ENDLESS("Бессрочная"),
    UNION("СО"),
    UNION_TEMP("Временная СО"),
    UNION_BONUS("Бонус СО");

    CampaignType(String value) {
        this.value = value;
    }

    private String value;
}
