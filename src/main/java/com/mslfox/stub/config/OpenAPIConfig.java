package com.mslfox.stub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Эмуляция системы процессинга для микрокредитов")
                        .version("1.0")
                        .description("Эмулирует процессинг микрокредитов"));
    }
}