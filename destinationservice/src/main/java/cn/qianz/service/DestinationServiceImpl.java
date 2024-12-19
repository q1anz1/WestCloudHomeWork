package cn.qianz.service;

import cn.qianz.mapper.DestinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.cloudapicommon.pojo.PO.DestinationPO;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.service.DestinationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationMapper destinationMapper;

    @Override
    public Result<?> getDestination(Long id) {
        DestinationPO destinationPO = destinationMapper.selectDestinationPOById(id);
        if (destinationPO==null) throw new ParamInvalidException("目的地不存在");
        return Result.ok(destinationPO);
    }
}
