package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.JwtTokenUtils;
import com.example.server.mapper.LogsMapper;
import com.example.server.mapper.UsersMapper;
import com.example.server.pojo.Logs;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Users;
import com.example.server.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private LogsMapper logsMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespBean login(String username, String password, String code,String ip, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码出入错误,请重新驶入");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员");
        }

        //更新Security用户登录对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        logsMapper.insert(new Logs(null,"后台登录",null,usersMapper.getIdByUserName(username),ip,((int)(System.currentTimeMillis()/1000))));
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> allInfo = usersMapper.selectAllByUid(1);
        allInfo.put("password","");
        map.put("token",tokenMap);
        map.put("allInfo",allInfo);
        return RespBean.success("登录成功", map);
    }
}
