package petrovich.ds.campaign.mechanic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
public class RuleData implements Serializable {

    private static final long serialVersionUID = 5062182300714029906L;

    @NotEmpty
    private String ruleTemplate;

    @Valid
    @NotEmpty
    private List<TemplateField> variables;

    private String rule;

    public TemplateField templateVariable(String value) {
        if (variables == null) {
            return null;
        }
        Optional<TemplateField> optional = variables.stream()
                .filter(templateField -> templateField.getTemplateVariable().equals(value))
                .findFirst();
        return optional.orElse(null);
    }

    public TemplateField templateVariableHashed(String value) {
        if (variables == null) {
            return null;
        }
        Optional<TemplateField> optional = variables.stream()
                .filter(templateField -> value.equals(templateField.getTemplateVariable()) ||
                        value.equals(DigestUtils.sha1Hex(
                                Optional.ofNullable(templateField.getValue()).orElse(""))))
                .findFirst();
        return optional.orElse(null);
    }

    /**
     * поиск переменных по типу поля
     *
     * @param type тип {@link FieldType}
     * @return список переменных
     */
    public Stream<TemplateField> variablesByType(FieldType type) {
        if (variables == null) {
            return Stream.empty();
        }
        return variables.stream().filter(templateField -> type.equals(templateField.getFieldType()));
    }

    public Stream<TemplateField> variablesByType(List<FieldType> types) {
        if (types == null || types.isEmpty() || variables == null) {
            return Stream.empty();
        }
        return variables.stream().filter(templateField -> types.contains(templateField.getFieldType()));
    }

    public Map<String, TemplateField> variablesMap() {
        return variables.stream()
                .collect(Collectors.toMap(TemplateField::getTemplateVariable, Function.identity(), (f1, f2) -> f2));
    }
}
