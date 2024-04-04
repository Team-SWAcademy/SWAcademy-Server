package carbonneutral.academy.api.controller.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatchOnboardingRes {

    private int id;
    private boolean isFinished;
}
