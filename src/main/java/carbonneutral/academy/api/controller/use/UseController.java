package carbonneutral.academy.api.controller.use;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "use controller", description = "다회용기 이용 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/uses")
public class UseController {
}
