package qianz.itineraryservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.cloudapicommon.pojo.PO.DestinationPO;
import qianz.cloudapicommon.pojo.PO.ItineraryPO;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.ItineraryService;
import qianz.cloudapicommon.util.JwtUtil;
import qianz.itineraryservice.client.DestinationClient;
import qianz.itineraryservice.mapper.ItineraryMapper;

import java.util.Date;

/**
* ItineraryServiceImpl
* */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {
    private final DestinationClient destinationClient;
    private final ItineraryMapper itineraryMapper;
    private final HttpServletRequest httpServletRequest;

    @Override
    public Result<?> createItinerary(Long destinationId, Date time) {
        Result<?> result = destinationClient.getDestination(destinationId);
        if (result.getBase().getCode() != 200) throw new ParamInvalidException(result.getBase().getMsg());
        DestinationPO destinationPO = (DestinationPO) result.getData();
        ItineraryPO itineraryPO = new ItineraryPO(JwtUtil.getUserId(httpServletRequest), destinationId, destinationPO.getName(), new Date(System.currentTimeMillis()));
        itineraryMapper.insertItineraryPO(itineraryPO);
        return Result.ok(itineraryPO);
    }
}
