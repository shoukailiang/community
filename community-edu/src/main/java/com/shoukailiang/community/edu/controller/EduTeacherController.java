package com.shoukailiang.community.edu.controller;


import com.shoukailiang.community.edu.service.IEduTeacherService;
import com.shoukailiang.community.entities.EduTeacher;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/edu-teacher")
public class EduTeacherController {

    @Autowired
    private IEduTeacherService teacherService;

    @ApiOperation("查询所有讲师列表")
    @GetMapping("/findAll")
    public ResultVO findAll(){

        List<EduTeacher> teacherList = teacherService.list();
        return ResultVOUtil.success(teacherList);
    }

}
