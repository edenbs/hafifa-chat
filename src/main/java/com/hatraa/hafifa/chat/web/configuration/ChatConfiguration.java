package com.hatraa.hafifa.chat.web.configuration;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.FilterRegistration;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hatraa.hafifa.chat")
public class ChatConfiguration  extends WebMvcConfigurerAdapter {
}
