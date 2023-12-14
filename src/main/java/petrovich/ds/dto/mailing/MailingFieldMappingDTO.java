package petrovich.ds.data.dto.mailing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MailingFieldMappingDTO {

    private String id;
    @NotEmpty
    private String name;
    private boolean manual;
    private String value;
    private boolean skip;
}
