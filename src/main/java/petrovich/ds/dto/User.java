package petrovich.ds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -4033655794074586605L;

    private String name;
    private String username;

    @JsonIgnore
    public String getEmail() {
        return String.format("%s@petrovich.ru", this.username);
    }

    @JsonIgnore
    public String getMailingFormat() {
        return String.format("%s (%s)", this.getName(), this.getUsername());
    }

}
