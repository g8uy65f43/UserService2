package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.io.LineProcessor;
import com.google.gson.JsonObject;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.entity.User;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.LoginFailedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.properties.LiffProperties;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private LiffProperties liffProperties;
    @Autowired
    private UserMapper userMapper;


    public static final String LINE_LOGIN = "https://api.line.me/v2/profile";
    @Override
    public User lineLogin(UserLoginDTO userLoginDTO) {
        JSONObject dataJsonObject = getLiffId(userLoginDTO.getAccessToken());
        String liffId = dataJsonObject.getString("userId");
        if (liffId ==null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        User user = userMapper.getByLiffId(liffId);
        if (user ==null){
            user = User.builder().phone(userLoginDTO.getOs()).openid(liffId).name(dataJsonObject.getString("displayName")).avatar(dataJsonObject.getString("pictureUrl")).createTime(LocalDateTime.now()).build();
            userMapper.insert(user);
        }
        log.info("@@{}{}",liffId,userLoginDTO.getOs());
        userMapper.updateOs(liffId,userLoginDTO.getOs());

        return user;
    }
private JSONObject  getLiffId(String code){
    Map<String, String> map = new HashMap<>();
    map.put("liffId",liffProperties.getAppid());
    map.put("js_code","Bearer "+code);
    String json = HttpClientUtil.myDoGet(LINE_LOGIN,map);
    JSONObject jsonObject = JSON.parseObject(json);



    return jsonObject;
};
    }
