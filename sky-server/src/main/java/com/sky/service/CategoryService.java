package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryService {


    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category category);

    void startOrStop(Integer states, Long id);

    void deleteById(Long id);

    void save(Category category);

    List<Category> list(Integer type);
}
