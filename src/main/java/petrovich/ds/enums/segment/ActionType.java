package petrovich.ds.data.enums.segment;

import lombok.Getter;

@Getter
public enum ActionType {
    POINTS("Баллы"),
    COUPONS("Купоны"),
    CASHBACK("Вернём деньги"),
    PERCENT("Проценты"),
    NEW_PRICE("Новая цена"),
    DISCOUNT("Скидка"),
    UNION("СО");

    ActionType(String value) {
        this.value = value;
    }

    private String value;
}