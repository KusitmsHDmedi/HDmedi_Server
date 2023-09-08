package com.kusithm.hdmedi_server.global.config;

import com.kusithm.hdmedi_server.HDmediServerApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackageClasses = HDmediServerApplication.class)
@Configuration
public class FeignClientConfig {
}