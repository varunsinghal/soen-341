package com.soen.empower.config;

import com.soen.empower.entity.User;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = userService.findByName(authentication.getName());
        request.getSession().setAttribute("user_id", user.getId());
        request.getSession().setAttribute("name", user.getName());

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_TEACHER".equals(auth.getAuthority()))
                request.getSession().setAttribute("teacher_id", user.getTeacher().getId());
            else if ("ROLE_PARENT".equals(auth.getAuthority()))
                request.getSession().setAttribute("parent_id", user.getParent().getId());
            response.sendRedirect("/user");
        }
    }

}
