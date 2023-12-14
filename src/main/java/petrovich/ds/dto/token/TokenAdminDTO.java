package petrovich.ds.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@Data
@AllArgsConstructor
public class TokenAdminDTO {

    @NonNull
    String access_token;
}
