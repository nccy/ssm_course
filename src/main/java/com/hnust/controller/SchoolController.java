package com.hnust.controller;

import com.hnust.entity.Result;
import com.hnust.entity.School;
import com.hnust.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author 长夜
 * @date 2023/4/21 20:44
 */
@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    private Result result;
    @GetMapping("get_option")
    @ResponseBody
    public Result get_option()
    {
        List<School> schools=schoolService.selectAll();
        result.setData(schools);
        result.setMsg("success");
        result.setCode(200);
        return result;
    }
}
