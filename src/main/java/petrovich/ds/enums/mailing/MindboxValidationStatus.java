package petrovich.ds.enums.mailing;

import lombok.Getter;

@Getter
public enum MindboxValidationStatus {
    VALID("Валидный"),
    INVALID("Невалидный"),
    NOT_AVAILABLE("Недоступен"),
    NOT_CHECKED("Не проверен");

    private String value;

    MindboxValidationStatus(String value) {
        this.value = value;
    }
}
