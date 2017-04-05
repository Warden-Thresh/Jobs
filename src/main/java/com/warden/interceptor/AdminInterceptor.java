package com.warden.interceptor;

import com.warden.model.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Warden on 2017/3/31.
 */
public class AdminInterceptor implements HandlerInterceptor {
    private final String ADMINSESSION = "admin";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object sessionObj = request.getSession().getAttribute(ADMINSESSION);
        if(sessionObj!=null) {
            UserEntity userEntity = (UserEntity) request.getSession().getAttribute(ADMINSESSION);
            System.out.println(userEntity.getNickname()+"\n"+userEntity.getPassword());
            return true;
        }
        response.sendRedirect("login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
