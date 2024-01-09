package petrovich.ds.dto.segment;

import lombok.Getter;
import lombok.Setter;
import petrovich.ds.dto.mechanic.FieldType;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ColumnMap implements Serializable {

    private static final long serialVersionUID = -7009928452066092778L;

    public ColumnMap() {
    }

    public ColumnMap(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }

    public ColumnMap(String ru, String en, FieldType type) {
        this.ru = ru;
        this.en = en;
        this.type = type;
    }

    private String ru;
    private String en;
    private FieldType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnMap columnMap = (ColumnMap) o;
        return Objects.equals(ru, columnMap.ru) &&
                Objects.equals(en, columnMap.en) &&
                type == columnMap.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ru, en, type);
    }
}
