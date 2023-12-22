package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }
    @PostMapping
    @ApiOperation("従業員追加")
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("従業員追加{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    };
    @GetMapping("/page")
    @ApiOperation("員工分頁查詢")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("員工分頁查詢,參數為{}",employeePageQueryDTO )  ;
       PageResult pageResult =employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }
    @PostMapping("/status/{states}")
    @ApiOperation("員工帳號狀態值變更")
    public Result startOrStop(@PathVariable Integer states, Long id){
        log.info("啟用禁用員工帳號{}{}",states,id);
        employeeService.startOrStop(states,id);
       return Result.success();
    }
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
       Employee employee = employeeService.getByid(id);
       employee.setPassword("****");
        return  Result.success(employee);
    }
    @PutMapping
    public Result<Employee> update(@RequestBody  Employee employee){
        employeeService.update(employee);
return Result.success();
    }

}
