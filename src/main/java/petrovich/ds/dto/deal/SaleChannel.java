package petrovich.ds.dto.deal;

import lombok.Getter;

/**
 * @author Andrei Vaneev
 * Creation date: 25.01.2022
 */
@Getter
public enum SaleChannel {
    SITE("site"), UT_1C("1c");

    private final String value;

    SaleChannel(String value) {
        this.value = value;
    }
}
