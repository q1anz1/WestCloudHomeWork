package qianz.itineraryservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public Result<?> createItinerary(@RequestParam("destination_id")Long destinationId, @RequestParam("start_time")Date time) {
        return itineraryService.createItinerary(destinationId, time);
    }
}
