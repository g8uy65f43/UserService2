package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("分類添加")
    public Result<Category> save(@RequestBody Category category){
        log.info("分類添加,參數為{}",category )  ;

        categoryService.save(category);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分類分頁查詢")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分類分頁查詢,參數為{}",categoryPageQueryDTO )  ;
        PageResult pageResult =categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }
    @PutMapping()
    public Result<CategoryDTO> updtate(@RequestBody Category category){
        log.info("分類修改,參數為{}",category )  ;
        categoryService.update(category);
          return Result.success();
    }
    @PostMapping("/status/{states}")
    @ApiOperation("分類帳號狀態值變更")
    public Result startOrStop(@PathVariable Integer states, Long id){
        log.info("啟用禁用分類{}{}",states,id);
        categoryService.startOrStop(states,id);
        return Result.success();
    }
    @DeleteMapping
    @ApiOperation("删除分类")
    public Result<String> deleteById(Long id){
        log.info("删除分类：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }
}
