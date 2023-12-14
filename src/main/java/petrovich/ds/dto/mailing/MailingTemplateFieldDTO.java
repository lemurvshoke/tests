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
public class MailingTemplateFieldDTO {

    private String id;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    private boolean required;
}
