package com.decimal.rbac.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class OpenApiConfig {

    @Data
    public static class ApiDoc {
        private String title;
        private String description;
        private String version;
    }

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ObjectMapper mapper;

    @Value("${documentation.api.file}")
    private String apiDocFilePath;

    @Bean
    public ApiDoc getApiDocMetadata() throws IOException {
        Resource resource = resourceLoader.getResource(apiDocFilePath);
        return mapper.readValue(
                Files.readString(resource.getFile().toPath()),
                new TypeReference<>() {}
        );
    }

    @Bean
    public OpenAPI openAPI(
            ApiDoc meta
    ) {
        return new OpenAPI()
                .info(new Info().title(meta.title)
                        .description(meta.description)
                        .version(meta.version));
    }
}
