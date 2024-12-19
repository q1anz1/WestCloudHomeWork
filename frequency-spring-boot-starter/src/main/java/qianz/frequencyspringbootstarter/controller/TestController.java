package qianz.frequencyspringbootstarter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qianz.frequencyspringbootstarter.anno.IpFrequencyControl;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    @IpFrequencyControl(time = 10, frequency = 2)
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
