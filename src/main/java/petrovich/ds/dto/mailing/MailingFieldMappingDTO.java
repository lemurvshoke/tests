package petrovich.ds.dto.mailing;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class MailingFieldMappingDTO {

    private String id;
    @NotEmpty
    private String name;
    private boolean manual;
    private String value;
    private boolean skip;
    private boolean skipAvailable;
}
