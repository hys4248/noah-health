package com.yhch.interceptor;


import com.yhch.bean.Constant;
import com.yhch.bean.Identity;
import com.yhch.service.PropertyService;
import com.yhch.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录认证拦截器（判断token有效性）
 * Created by ken on 2017/6/8.
 */
@Component
public class TokenCertifyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenCertifyInterceptor.class);

    @Autowired
    private PropertyService propertyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        logger.info("进入TokenCertifyInterceptor");

        // 验证token的有效性
        try {

            String token = request.getHeader("TOKEN");
            logger.info("获得的token： {}", token);

            Identity identity = TokenUtil.parseToken(token, propertyService.apiKeySecret);

            // 把identity存入session中(其中包含用户名、角色、过期时间戳等)
            request.getSession().setAttribute(Constant.IDENTITY, identity);

            logger.info("{}: token有效", identity.getUsername());

            return true;
        } catch (Exception e) {

            logger.info("TOKEN无效，转到登录界面");
            response.sendRedirect("/api/auth/login_denied");

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }
}
