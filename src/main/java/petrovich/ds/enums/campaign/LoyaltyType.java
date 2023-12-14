package petrovich.ds.enums.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum LoyaltyType {
    NONE("Персональные акции"),
    BIG_PURCHASE("За \"Большую покупку\""),
    EDLP("EDLP"),
    WELCOME_POINTS("Приветственные баллы"),
    // по механике акции "Спецусловия ВЛ" нужно делать 2 разных начисления, в зависимости от вида actionCoefficient
    MAJOR_LEAGUE_SPECIAL_CONDITIONS("Спецусловия ВЛ"),
    MAJOR_LEAGUE_PLUMBING("Сантехник"),
    MAJOR_LEAGUE_ELECTRICIAN("Электрик"),
    VENDOR("Поставщики"),
    BASE_MOTIVATION("Покупки"),
    BIRTHDAY("День рождения"),
    SLIDER_POINTS("За ползунок"),
    UNION("Союз отважных"),
    UNION_PERSONAL("Персональные акции СО"),
    UNION_WELCOME("Приветственные бонусы СО"),
    FRIENDS_CLUB("Клуб друзей"),
    COMMENT("За отзыв"),
    SBP("Оплата по СБП"),
    FRIENDS_CLUB_CORRECTION("Корректировка для УТ"),
    UNION_CORRECTION("Корректировка для LMS"),
    COMMENT_UNION("За отзыв"),
    GAME_FRIENDS("За игру"),
    GAME_UNION("За игру");

    private final String rusName;

    private static final List<LoyaltyType> UNION_TYPES = Arrays.asList(
            UNION,
            UNION_PERSONAL,
            UNION_WELCOME,
            UNION_CORRECTION,
            COMMENT_UNION,
            GAME_UNION
    );

    public static List<LoyaltyType> unionTypes() {
        return UNION_TYPES;
    }

    public boolean isUnionType() {
        return UNION_TYPES.contains(this);
    }
}
