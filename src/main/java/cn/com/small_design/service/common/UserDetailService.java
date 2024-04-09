package cn.com.small_design.service.common;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.utils.JwtUtils;
import cn.com.small_design.common.utils.RedisUtils;
import cn.com.small_design.dao.dao.UserMapper;
import cn.com.small_design.dao.dao.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author gejj
 * @createTime 2024年03月25日 15:22
 * @version 1.0
 */
@Service("userDetailService")
public class UserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String s) {
        //用户信息查询
        User user = userMapper.queryUserByUsername(s);

        //判断用户是否存在
        if(Objects.isNull(user)){
            logger.error("用户登录失败");
            throw new UsernameNotFoundException("用户名不存在");
        }

        //用户权限信息查询

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        //方法一：使用用户、角色、资源建立关系，直接使用角色控制权限
//        List<String> codeList = roleMapper.queryUserRole(user.getUsername());
//        //添加权限信息进入缓存
//        redisUtils.set(username, StringUtils.join(codeList,","),60 * 60);
//        //方法二：添加权限（资源表），通过建立用户、角色、权限、资源之间的关系，使用"权限"实现按钮级别的权限控制
//        List<String> codeList = authoritiesMapper.queryAuthoritiesList(user.getUsername());
//        codeList.forEach(code ->{
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code);
//            authorities.add(simpleGrantedAuthority);
//        });
        return new UserInfo(user,new ArrayList<>());
    }
}
