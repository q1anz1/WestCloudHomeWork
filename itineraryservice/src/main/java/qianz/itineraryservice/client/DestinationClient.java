package qianz.itineraryservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import qianz.cloudapicommon.service.DestinationService;
@FeignClient("DestinationClient")
public interface DestinationClient extends DestinationService {
}
