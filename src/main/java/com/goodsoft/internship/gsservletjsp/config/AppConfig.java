package com.goodsoft.internship.gsservletjsp.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.goodsoft.internship.gsservletjsp")
@ServletComponentScan("com.goodsoft.internship.gsservletjsp.web")
public class AppConfig {
}
