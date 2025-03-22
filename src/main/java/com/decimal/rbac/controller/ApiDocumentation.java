package com.decimal.rbac.controller;

import com.decimal.rbac.exceptions.BadRequestException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@Tag(name = "API Documentation")
public class ApiDocumentation {
    private static final String DOC_RESOURCE_FILE_PATH="classpath:ApiDoc.html";
    private final ResourceLoader resourceLoader;
    @Value("${springdoc.api-docs.path}")
    private String apiDocYamlPath;

    @GetMapping("documentation")
    public String getApiDocumentation(
            @RequestHeader String host
    ) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path(apiDocYamlPath).build().toUriString();
        Resource resource = resourceLoader.getResource(DOC_RESOURCE_FILE_PATH);
        try {
           return Files.readString(resource.getFile().toPath()).replace(
                   "%doc_uri%", url
           );
        } catch (IOException e) {
            throw new BadRequestException("Unable to load API Documentation. Please try again later.");
        }
    }
}
