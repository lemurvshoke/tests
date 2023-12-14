package petrovich.ds.data.campaign.mechanic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class TemplateField implements Serializable {

    private static final long serialVersionUID = 5781992407584652356L;

    private String rusName;

    @NotEmpty
    private String templateVariable;

    private FieldType fieldType;

    private String value;

    private String additionalValue;

    /**
     * Заполнено ли значение вручную или выбрано из сегмента
     */
    private boolean manual;
    /**
     * Флаг обозначающий что поле необязательно для заполнения
     */
    @Accessors(chain = true)
    private boolean notRequired;
    /**
     * часть URL, по которому нужно искать данные
     */
    @Accessors(chain = true)
    private String searchTerm;

    private boolean removable;

    private String migrationValue;
    /**
     * Связанное поле, если есть значение у указанного поля, то на фронте доступно к редактированию
     */
    private String connectTo;

    public TemplateField() {
    }

    public TemplateField(String rusName, String templateVariable, String value) {
        this.rusName = rusName;
        this.templateVariable = templateVariable;
        this.value = value;
    }

    public TemplateField(String rusName, String templateVariable) {
        this.rusName = rusName;
        this.templateVariable = templateVariable;
    }

    public TemplateField(String rusName, String templateVariable, FieldType fieldType) {
        this.rusName = rusName;
        this.templateVariable = templateVariable;
        this.fieldType = fieldType;
    }

    public TemplateField(String rusName, String templateVariable, FieldType fieldType, boolean notRequired) {
        this.rusName = rusName;
        this.templateVariable = templateVariable;
        this.fieldType = fieldType;
        this.notRequired = notRequired;
    }

    public TemplateField(String rusName, String templateVariable, FieldType fieldType, String value) {
        this.rusName = rusName;
        this.templateVariable = templateVariable;
        this.fieldType = fieldType;
        this.value = value;
    }

    public TemplateField(String rusName, String templateVariable, FieldType fieldType, boolean notRequired, String searchTerm) {
        this.rusName = rusName;
        this.templateVariable = templateVariable;
        this.fieldType = fieldType;
        this.notRequired = notRequired;
        this.searchTerm = searchTerm;
    }

    //todo: убрать migrationValue после окончательного перехода на новые товарные группы
    public String getValue() {
        return fieldType == FieldType.PRODUCT_GROUP && migrationValue != null ? migrationValue : value;
    }

    //todo something better \/
    @JsonIgnore
    public BigDecimal getNumericValue() {
        if (this.fieldType.equals(FieldType.NUMBER) && manual) {
            return value == null ? null : new BigDecimal(this.value);
        } else {
            return null;
        }
    }

    @JsonIgnore
    public String getStringValue() {
        if ((this.fieldType.equals(FieldType.STRING) || this.fieldType.equals(FieldType.COUPON_EDITION)) && manual) {
            return this.value != null ? this.value : "";
        } else {
            return null;
        }
    }

    @JsonIgnore
    public boolean getBoolValue() {
        if (this.fieldType.equals(FieldType.BOOLEAN) && manual) {
            return Boolean.parseBoolean(this.value);
        } else {
            return false;
        }
    }

    @JsonIgnore
    public Date getDateValue() throws ParseException {
        if (this.fieldType.equals(FieldType.DATE) && manual) {
            return SimpleDateFormat.getInstance().parse(this.value);
        } else {
            return null;
        }
    }

    @JsonIgnore
    public List<String> getListValue() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateField that = (TemplateField) o;
        return notRequired == that.notRequired &&
                Objects.equals(rusName, that.rusName) &&
                Objects.equals(templateVariable, that.templateVariable) &&
                fieldType == that.fieldType &&
                Objects.equals(searchTerm, that.searchTerm) &&
                Objects.equals(connectTo, that.connectTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rusName, templateVariable, fieldType, notRequired, searchTerm, connectTo);
    }
}
