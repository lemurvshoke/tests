package petrovich.ds.dto.pdp.sale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@EqualsAndHashCode
public class TriggerDocument implements Serializable {

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private final String uuid;
    private final Type type;
    private final String number;
    private final Date date;

    @JsonCreator
    public TriggerDocument(@JsonProperty("uuid") String uuid,
                           @JsonProperty("type") Type type,
                           @JsonProperty("number") String number,
                           @JsonProperty("date") Date date) {
        this.uuid = uuid;
        this.type = type;
        this.number = number;
        this.date = date;
    }

    public static TriggerDocument shipment(String uuid, String number, Date date) {
        return new TriggerDocument(uuid, Type.SHIPMENT, number, date);
    }

    public static TriggerDocument refund(String uuid, String number, Date date) {
        return new TriggerDocument(uuid, Type.REFUND, number, date);
    }

    public static TriggerDocument receipt(String uuid, String number, Date date) {
        return new TriggerDocument(uuid, Type.RECEIPT, number, date);
    }

    @Transient
    public String getName() {
        String name = "";
        switch (this.type) {
            case SHIPMENT:
                name = "Реализация";
                break;
            case REFUND:
                name = "Возврат";
                break;
            case RECEIPT:
                name = "Чек ККМ";
                break;
        }
        return String.format("%s %s от %s", name, this.number, DATE_FORMAT.format(this.date));
    }

    /**
     * Возвращает тип документа в виде целого числа.
     * <pre>
     * 1 - Реализация товаров услуг.
     * 2 - Возврат товаров от покупателя.
     * 3 - Чек ККМ.
     * </pre>
     */
    @Transient
    public int getTypeAsInt() {
        return this.type.ordinal() + 1;
    }

    public enum Type {
        SHIPMENT, REFUND, RECEIPT
    }
}
