package qianz.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qianz.cloudapicommon.service.UserService;
import qianz.userservice.anno.VerifyRegisterBO;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.userservice.mapper.UserMapper;
import qianz.cloudapicommon.pojo.BO.RegisterBO;
import qianz.cloudapicommon.pojo.PO.UserPO;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.cloudapicommon.util.JwtUtil;

/**
* UserServiceImpl
* */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    @VerifyRegisterBO
    public Result<?> register(RegisterBO registerBO) {
        String username = registerBO.getUsername();
        String password = registerBO.getPassword();
        if (userMapper.selectUserPOByUsername(username) != null) throw new ParamInvalidException("用户名已存在");
        userMapper.insertUserPO(username, passwordEncoder.encode(password));
        return Result.ok();
    }

    @Override
    public Result<?> login(String username, String password) {
        UserPO userPO = userMapper.selectUserPOByUsername(username);
        if (userPO == null) throw new ParamInvalidException("用户不存在");
        if (!(passwordEncoder.matches(password, userPO.getPassword()))) throw new ParamInvalidException("密码错误");
        // 用户名和密码正确后，发送JWT令牌
        String jwt = JwtUtil.creatToken(userPO.getId());
        return Result.ok(jwt);
    }
}
