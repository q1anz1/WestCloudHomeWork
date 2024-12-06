package qianz.cloudapicommon.service;
import qianz.cloudapicommon.pojo.BO.RegisterBO;
import qianz.cloudapicommon.pojo.result.Result;
/**
 * UserService
 * */
public interface UserService {
    Result<?> register(RegisterBO registerBO);

    Result<?> login(String username, String password);
}
