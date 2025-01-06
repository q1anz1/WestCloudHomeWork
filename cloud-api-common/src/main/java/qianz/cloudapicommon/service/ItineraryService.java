package qianz.cloudapicommon.service;

import qianz.cloudapicommon.pojo.result.Result;

import java.util.Date;

/**
 * ItineraryService
 * */

public interface ItineraryService {
    Result<?> createItinerary(Long destinationId, Date time);

    Result<?> getItineraryList();

    Result<?> deleteItinerary(Long itineraryId);
}
