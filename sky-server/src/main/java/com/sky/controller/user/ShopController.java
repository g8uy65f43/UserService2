package com.sky.controller.user;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "店鋪狀態相关接口")
@Slf4j
public class ShopController {

    private   static final String key ="SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("獲取店鋪狀態")
    public Result getStatus (){

        Integer status =(Integer) redisTemplate.opsForValue().get(key);
        log.info("獲取店鋪營業狀態",status== 1?"營業中":"打烊中");

        return Result.success(status);
    }
}
