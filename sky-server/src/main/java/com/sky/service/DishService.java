package com.sky.service;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.beans.Transient;


public interface DishService {


    @Transient
    public void saveWithFlavor(DishDTO dishDTO);
}
