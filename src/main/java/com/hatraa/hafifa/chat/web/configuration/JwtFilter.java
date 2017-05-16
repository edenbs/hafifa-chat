package com.hatraa.hafifa.chat.web.configuration;

import com.hatraa.hafifa.chat.services.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Ebens on 4/3/17.
 */
@Component
public class JwtFilter implements Filter {

    @Autowired
    AuthService authService;

    @Override
    public void init(FilterConfig config) {
        ServletContext servletContext = config.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.configureBean(this, "authService");
    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)req;
        final HttpServletResponse response = (HttpServletResponse)res;

        if (request.getMethod() != "OPTIONS" &&
                !(request.getPathInfo().endsWith("/user/register") || request.getPathInfo().endsWith("/user/login"))) {
            final String authHeader = request.getHeader("Authorization");

            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            final String token = authHeader.substring(7);

            try {
                Claims claims = authService.decodeUser(token);
                request.setAttribute("claims", claims);
            }
            catch (SignatureException ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
