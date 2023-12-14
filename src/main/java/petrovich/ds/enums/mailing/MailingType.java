package petrovich.ds.enums.mailing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MailingType {

    START("Информация о старте акции"),
    REMINDER("Напоминание о старте акции"),
    POINTS("Статистика по баллам"),
    SALES("Статистика продаж"),
    DISCOUNT("Статистика по скидке"),
    CONDITIONAL("Информация о выполнении условий акции"),
    FINISH("Информация о завершении акции"),
    COUPONS("Информация о выдаче купона");

    private final String description;
}
