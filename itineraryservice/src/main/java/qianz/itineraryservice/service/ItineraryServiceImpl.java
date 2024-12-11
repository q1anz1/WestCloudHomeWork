package qianz.itineraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.DestinationService;
import qianz.cloudapicommon.service.ItineraryService;
import qianz.itineraryservice.client.DestinationClient;

import java.util.Date;

/**
* ItineraryServiceImpl
* */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {
    private final DestinationClient destinationClient;

    @Override
    public Result<?> createItinerary(Long destinationId, Date time) {
        return destinationClient.getDestination(destinationId);
    }
}
