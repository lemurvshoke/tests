package petrovich.ds.dto.mailing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MailingTemplateDTO {

    private String id;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    private String mindboxId;

    private boolean used;

    @NotEmpty
    private List<MailingTemplateFieldDTO> fields;

}
