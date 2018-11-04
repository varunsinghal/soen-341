package com.soen.empower.config;

import com.soen.empower.entity.Notification;
import com.soen.empower.entity.User;
import com.soen.empower.service.NotificationService;
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
import java.util.List;


@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    /**
     * On successful authentication, the session variables are set for the user depending upon the
     * role - ROLE_TEACHER or ROLE_PARENT.
     *
     * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     * @since 1.0
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = userService.findByName(authentication.getName());
        request.getSession().setAttribute("user_id", user.getId());
        request.getSession().setAttribute("name", user.getUsername());
        request.getSession().setAttribute("full_name", user.getFullName());
        request.getSession().setAttribute("notifications", notificationService.fetchAll(user.getId()));

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_TEACHER".equals(auth.getAuthority()))
                request.getSession().setAttribute("teacher_id", user.getTeacher().getId());
            else if ("ROLE_PARENT".equals(auth.getAuthority()))
                request.getSession().setAttribute("parent_id", user.getParent().getId());
            response.sendRedirect("/user");
        }
    }

}
