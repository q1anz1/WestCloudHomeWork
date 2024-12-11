package qianz.cloudapicommon.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qianz.cloudapicommon.pojo.result.Result;

public interface DestinationService {
    @GetMapping("user/destination/get")
    Result<?> getDestination(@RequestParam("destination_id")Long id);
}
