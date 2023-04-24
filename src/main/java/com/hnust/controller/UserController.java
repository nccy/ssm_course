package com.hnust.controller;

import com.hnust.entity.Result;
import com.hnust.entity.User;
import com.hnust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 长夜
 * @date 2023/4/11 14:48
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Result result;
    //加载登录页面
    @GetMapping("/login_page")
    public String login_page() {
        return "login";
    }
    //退出并返回登录页面
    @GetMapping("/exit_page")
    public String exit_page(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login_page";
    }
    //登录逻辑处理
    @PostMapping("/login_solve")
    @ResponseBody
    public Result login_solve(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user);
        User res=userService.selectByEmail(user.getEmail());
        // 判断密码和邮箱是否正确
        if (user.getPassWord().equals(res.getPassWord())) {
            result.setMsg("success");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            result.setMsg("false");
        }
        result.setCode(200);
        result.setData(user);
        return result;
    }
    //获取用户名
    @GetMapping("/get_name")
    @ResponseBody
    public String get_name(HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        return user.getEmail();
    }
}
