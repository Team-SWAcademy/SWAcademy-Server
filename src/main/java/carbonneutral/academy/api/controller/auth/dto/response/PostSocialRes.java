package carbonneutral.academy.api.controller.auth.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSocialRes {

    @NotNull
    private int id;

    private boolean isFinished;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}
