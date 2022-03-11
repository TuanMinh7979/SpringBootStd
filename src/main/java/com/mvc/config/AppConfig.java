package com.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class AppConfig {
}
