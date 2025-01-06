package qianz.itineraryservice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
import java.util.List;

/**
* ItineraryServiceImpl
* */
@Slf4j
@Service
public class ItineraryServiceImpl implements ItineraryService {
    private final DestinationClient destinationClient;
    private final ItineraryMapper itineraryMapper;
    private final HttpServletRequest httpServletRequest;

    public ItineraryServiceImpl(@Qualifier("qianz.itineraryservice.client.DestinationClient") DestinationClient destinationClient, ItineraryMapper itineraryMapper, HttpServletRequest httpServletRequest) {
        this.destinationClient = destinationClient;
        this.itineraryMapper = itineraryMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Result<?> createItinerary(Long destinationId, Date time) {
        Result<?> result = destinationClient.getDestination(destinationId);
        if (result.getBase().getCode() != 200) throw new ParamInvalidException(result.getBase().getMsg());
        DestinationPO destinationPO = (DestinationPO) result.getData();
        ItineraryPO itineraryPO = new ItineraryPO(JwtUtil.getUserId(httpServletRequest), destinationPO.getId(), destinationPO.getName(), new Date(System.currentTimeMillis()));
        itineraryMapper.insertItineraryPO(itineraryPO);
        return Result.ok(itineraryPO);
    }

    @Override
    public Result<?> getItineraryList() {
        Long userId = JwtUtil.getUserId(httpServletRequest);
        List<ItineraryPO> itineraryList = itineraryMapper.getItineraryListByUserId(userId);
        return Result.ok(itineraryList);
    }

    @Override
    public Result<?> deleteItinerary(Long itineraryId) {
        Long userId = JwtUtil.getUserId(httpServletRequest);
        ItineraryPO itineraryPO = itineraryMapper.getItineraryByItineraryId(itineraryId);
        if (itineraryPO == null || !itineraryPO.getUserId().equals(userId)) return Result.error(HttpStatus.FORBIDDEN.value(), "行程不存在");
        itineraryMapper.deleteItineraryByItineraryId(itineraryId);
        return Result.ok();
    }
}
