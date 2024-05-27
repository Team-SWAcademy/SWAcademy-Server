package carbonneutral.academy.api.controller.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatchInfoReq {


  
    @NotNull
    private String editNickname;
}
