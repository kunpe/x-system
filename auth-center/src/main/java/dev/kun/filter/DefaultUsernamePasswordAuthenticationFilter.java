package dev.kun.filter;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kun.entity.Role;
import dev.kun.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DefaultUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;
    public DefaultUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        dev.kun.entity.User user = new dev.kun.entity.User();
        user.setUsername(authResult.getName());
        user.setRoles((List<Role>) authResult.getAuthorities());
        response.addHeader("Authorization", UUID.randomUUID().toString());
        this.response(response, HttpStatus.OK.value(), "登录成功");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        this.response(response, HttpStatus.FORBIDDEN.value(), "登录失败");
    }

    public static void response(HttpServletResponse response, int httpStatus, String message) {
        response.setContentType("applcation/json;charset=utf-8");
        response.setStatus(httpStatus);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map resultMap = new HashMap();
        resultMap.put("code", httpStatus);
        resultMap.put("msg", message);
        writer.write(JSONUtil.toJsonStr(resultMap));
        writer.flush();
        writer.close();
    }
}
