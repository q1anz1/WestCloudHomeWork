package cn.qianz.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qianz.cloudapicommon.client.DestinationSuperClient;
import qianz.cloudapicommon.pojo.PO.DestinationPO;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.DestinationService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DestinationController implements DestinationSuperClient {
    private final DestinationService destinationService;
    @GetMapping("/destination/get/{id}")
    public Result<?> getDestination(@PathVariable("id") Long id) {
        return destinationService.getDestination(id);
    }
}
