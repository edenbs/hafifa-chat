package com.hatraa.hafifa.chat.web.configuration;

import com.hatraa.hafifa.chat.web.filters.CORSFilter;
import com.hatraa.hafifa.chat.web.filters.JwtFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ChatInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ChatConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/api/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter[] singleton = {
                new CORSFilter(),
                new JwtFilter()
        };

        return singleton;
    }
}
