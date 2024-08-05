package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.MealService;

import com.sky.vo.SetmealVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/setmeal")
public class MealController {
    @Autowired
    private MealService mealService;
    @PostMapping
    public Result setmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐{}",setmealDTO);
        mealService.saveWithDish(setmealDTO);


        return Result.success();
    }
    @GetMapping("page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = mealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result delete(@RequestParam List<Long> ids){
        mealService.deleteBatch(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = mealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        mealService.update(setmealDTO);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售停售")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        mealService.startOrStop(status, id);
        return Result.success();
    }

}
