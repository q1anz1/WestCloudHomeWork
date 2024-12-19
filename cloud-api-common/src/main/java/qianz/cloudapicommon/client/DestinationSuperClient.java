package qianz.cloudapicommon.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import qianz.cloudapicommon.pojo.PO.DestinationPO;
import qianz.cloudapicommon.pojo.result.Result;

@FeignClient(name = "destination-service")
public interface DestinationSuperClient {
    @GetMapping("/destination/get/{id}")
    Result<?> getDestination(@PathVariable("id") Long id);
}
