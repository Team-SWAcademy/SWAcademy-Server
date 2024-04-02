package carbonneutral.academy.user.controller;

import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * test용
     */
    @GetMapping
    public BaseResponse<String> getHello() {
        return BaseResponse.onSuccess("Hello, World!");
    }

}
