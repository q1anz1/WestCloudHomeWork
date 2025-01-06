package qianz.itineraryservice.controller;

import cn.hutool.extra.spring.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.ItineraryService;
import qianz.frequencyspringbootstarter.anno.FrequencyControl;
import qianz.frequencyspringbootstarter.anno.IpFrequencyControl;
import qianz.frequencyspringbootstarter.aop.FrequencyControlAspect;

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
    public Result<?> createItinerary(@RequestParam("destination_id")Long destinationId/*, @RequestParam("start_time")Date time*/) {
        return itineraryService.createItinerary(destinationId, new Date(System.currentTimeMillis()));
    }

    @IpFrequencyControl
    @GetMapping("/itinerary/list/get")
    public Result<?> getItineraryList() {
        SpringUtil.getBean(FrequencyControlAspect.class);
        return itineraryService.getItineraryList();
    }

    @PostMapping("/itinerary/delete")
    private Result<?> deleteItinerary(@RequestParam("itinerary_id")Long itineraryId) {
        return itineraryService.deleteItinerary(itineraryId);
    }
}
