package com.hnust.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 长夜
 * @date 2023/4/14 15:03
 */
public class LoginInterceptor implements HandlerInterceptor {
    //用session实现拦截
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("---------------------------dooooo");
        if (session.getAttribute("user")==null) {
            System.out.println("---------------------------uooooo");
            response.sendRedirect("/user/login_page");
            System.out.println("-----------------------ago");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
