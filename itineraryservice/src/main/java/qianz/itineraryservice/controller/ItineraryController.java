package qianz.itineraryservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qianz.cloudapicommon.pojo.PO.DestinationPO;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.ItineraryService;

import java.util.Date;

/**
* ItineraryController
* */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;
    @PostMapping("/itinerary/create")
    public Result<?> createItinerary(@RequestParam("destination_id")Long destinationId) {
        return itineraryService.createItinerary(destinationId, new Date(System.currentTimeMillis()));
    }
    /*@RequestParam("start_time")Date time*/
}
