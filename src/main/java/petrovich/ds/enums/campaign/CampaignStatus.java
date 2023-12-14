package petrovich.ds.enums.campaign;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum CampaignStatus {
    /**
     * Черновик.
     */
    DRAFT("Черновик"),
    /**
     * Не действует (запланирована).
     */
    SCHEDULED("Запланирована"),
    /**
     * Действует.
     */
    ACTIVE("Действует"),
    /**
     * Завершена по расписанию, но продолжает работать для реализаций и возвратов.
     */
    IDLE("Действует в фоне"),
    /**
     * Завершена принудительно.
     */
    STOPPED("Остановлена"),
    /**
     * Завершена по расписанию.
     */
    DONE("Завершена");

    private final String value;

    CampaignStatus(String value) {
        this.value = value;
    }

    public String format() {
        return String.format("%s (%s)", this.name(), this.getValue());
    }

    public static final List<CampaignStatus> startedStatuses = Arrays.asList(ACTIVE, IDLE, SCHEDULED);
}

