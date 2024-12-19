package qianz.itineraryservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import qianz.cloudapicommon.client.DestinationSuperClient;

@FeignClient(name = "destination-service", fallback = DestinationClientHystrix.class)
public interface DestinationClient extends DestinationSuperClient {
}
