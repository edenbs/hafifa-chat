package com.hatraa.hafifa.chat.web.filters;

import com.hatraa.hafifa.chat.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/chat/{id}/*")
public class ChatPermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int chatID = Integer.parseInt(request.getParameter("id"));
        boolean isAllowed = ((User)request.getAttribute("user")).getChats().stream().anyMatch(x -> x.getId() == chatID);

        if (!isAllowed) {
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
