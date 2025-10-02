package com.sbungle.sbunglebe.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.sbungle.sbunglebe")
public class OpenFeignConfig {
}
