package com.decimal.rbac.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Configuration
public class OpenApiConfig {

    public static final String USERS_TAG = "Users";
    public static final String ROLES_TAG = "Roles";
    public static final String PERMISSIONS_TAG = "Permissions";
    public static final String ASSIGNMENTS_TAG = "Assignments";

    List<String> CUSTOM_TAG_ORDER = List.of(
      USERS_TAG, ROLES_TAG,PERMISSIONS_TAG, ASSIGNMENTS_TAG
    );

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
                        .version(meta.version))
                .tags(
                        CUSTOM_TAG_ORDER.stream().map(t -> {
                            Tag tag = new Tag();
                            tag.setName(t);
                            return tag;
                        }).toList()
                );

    }
}
