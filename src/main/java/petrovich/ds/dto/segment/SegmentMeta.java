package petrovich.ds.dto.segment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentMeta implements Serializable {

    private static final long serialVersionUID = 4810222912343984933L;

    private String name;
    private DynamicSegmentConfig dynamicConfig;

    private List<String> headers;
    private List<ColumnMap> columns;

    private boolean isCommon = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentMeta that = (SegmentMeta) o;
        return isCommon == that.isCommon &&
                Objects.equals(name, that.name) &&
                Objects.equals(dynamicConfig, that.dynamicConfig) &&
                Objects.equals(headers, that.headers) &&
                Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dynamicConfig, headers, columns, isCommon);
    }
}
