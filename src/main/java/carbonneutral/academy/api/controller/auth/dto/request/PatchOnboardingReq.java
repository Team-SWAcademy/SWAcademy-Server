package carbonneutral.academy.api.controller.auth.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatchOnboardingReq {

    @NotNull
    private String nickname;

    @NotNull
    private boolean gender;
}
