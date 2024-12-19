package qianz.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qianz.cloudapicommon.pojo.BO.RegisterBO;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.UserService;

/**
* UserController
* */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/register")
    public Result<?> register(@RequestParam("username")String username, @RequestParam("password")String password) {
        return userService.register(new RegisterBO(username, password));
    }

    @PostMapping("/user/login")
    public Result<?> login(@RequestParam("username")String username, @RequestParam("password")String password) {
        return userService.login(username, password);
    }

}
