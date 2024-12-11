package cn.qianz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.DestinationService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationService destinationService;
    @GetMapping("/destination/get")
    public Result<?> getDestination(@RequestParam("destination_id")Long id) {
        return destinationService.getDestination(id);
    }
}
