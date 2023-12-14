package petrovich.ds.dto.segment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import petrovich.ds.campaign.segment.ColumnMap;
import petrovich.ds.campaign.segment.DynamicSegmentConfig;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentDTO implements Serializable {

    private static final long serialVersionUID = 4810222912343984933L;

    private String name;
    private DynamicSegmentConfig dynamicConfig;

    private List<String> headers;
    private List<ColumnMap> columns;

    private boolean isCommon = false;
}
