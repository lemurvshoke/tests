package petrovich.ds.dto.mechanic;

import lombok.Getter;

@Getter
public enum FieldType {
    STRING("stringValue"),
    BOOLEAN("boolValue"),
    NUMBER("numericValue"),
    DATE("dateValue"),
    PRODUCT_GROUP("listValue"),
    ADD_GROUP_OPT("boolValue"),
    COUPON_EDITION("stringValue");

    FieldType(String method) {
        this.method = method;
    }

    private String method;
}
