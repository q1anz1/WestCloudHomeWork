package qianz.itineraryservice.client;

import org.springframework.stereotype.Component;
import qianz.cloudapicommon.pojo.PO.DestinationPO;
import qianz.cloudapicommon.pojo.result.Result;
@Component
public class DestinationClientHystrix implements DestinationClient{
    @Override
    public Result<?> getDestination(Long id) {
        return Result.ok(new DestinationPO(0L, "默认地址", "无描述"));
    }
}
