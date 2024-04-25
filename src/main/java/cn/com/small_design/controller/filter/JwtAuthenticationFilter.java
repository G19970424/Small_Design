package cn.com.small_design.controller.filter;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.utils.JwtUtils;
import cn.com.small_design.common.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author gejj
 * @create 2024年03月26日 11:20
 * @version 1.0
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //从请求头中获取token
        String token = request.getHeader("token");

        //请求无需登录
        if(!StringUtils.hasText(token)){
            chain.doFilter(request,response);
            return;
        }

        String userId;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userId = claims.get("userId",String.class);
        } catch (Exception e) {
            //token解析超时、非法
            logger.info("token 非法");
            throw new RuntimeException("token 非法");
        }

        if(Objects.isNull(userId)){
            logger.info("user id 未查询到！");
            throw new RuntimeException("user id 未查询到！");
        }

        UserInfo user = redisUtils.getCacheObject("login:" + userId);

        //验证是否存在登录用户
        if(Objects.isNull(user)){
            //用户登录过期，请重新登录
            logger.info("用户未登录！");
            throw new RuntimeException("用户未登录");
        }
        //将用户安全信息存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }
}
