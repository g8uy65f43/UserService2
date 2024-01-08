package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@CrossOrigin(origins = "https://fancy-torrone-30a781.netlify.app", maxAge = 3600)
@RestController
@RequestMapping("/user/user")
@Api(tags = "客戶端相關街口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;



    @PostMapping("/login")
    @ApiOperation("登入")
public Result<UserLoginVO>login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("Line用戶登入:{}",userLoginDTO.getAccessToken());
        User user = userService.lineLogin(userLoginDTO);
        log.info("Line用戶登入!!!!!!!!!!!:{}",user);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());

        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

       UserLoginVO userLoginVO= UserLoginVO.builder().id(user.getId()).openid(user.getOpenid()).token(token).build();

        return Result.success(userLoginVO);
}
}




