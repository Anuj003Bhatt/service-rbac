package com.decimal.rbac.controller;

import com.decimal.rbac.model.enums.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;
import com.decimal.rbac.model.rest.request.AddPermission;
import com.decimal.rbac.model.rest.response.ListResponse;
import com.decimal.rbac.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("permissions")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PermissionsController {
    private final PermissionService permissionService;

    @GetMapping
    @Operation(summary = "List Permissions", description = "Fetch list of all permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched the list of permissions")
    })
    public ListResponse<PermissionDto> findAll(
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) @Parameter(hidden = true) Pageable pageable
    ) {
        return this.permissionService.getAllPermissions(pageable);
    }

    @GetMapping("{id}")
    @Operation(summary = "Permission By ID", description = "Fetch a Permission by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Permission with the given ID"),
            @ApiResponse(responseCode = "404", description = "No permission found for the given ID")
    })
    public PermissionDto findById(@PathVariable(name = "id") UUID id) {
        return this.permissionService.getPermissionById(id);
    }

    @GetMapping("_byName/{name}")
    @Operation(summary = "Permission By Name", description = "Fetch a Permission by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found permission with the given name"),
            @ApiResponse(responseCode = "404", description = "No permission found for the given name")
    })
    public PermissionDto findByName(@PathVariable(name = "name") String name) {
        return this.permissionService.getPermissionByName(name);
    }

    @GetMapping("_byAccess/{types}")
    @Operation(summary = "List Permission By Access Types", description = "Fetch a Permission by access types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully etched list of permissions with the given access types")
    })
    public ListResponse<PermissionDto> findByAccessTypes(
            @PathVariable(name = "types") List<String> types,
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) @Parameter(hidden = true) Pageable pageable
    ) {
        return this.permissionService.getPermissionByAccessTypes(
                types.stream().map(it -> PermissionType.valueOf(it.toUpperCase())).toList(),
                pageable
        );
    }

    @PostMapping
    @Operation(summary = "Add permission", description = "Create a new permission/grant/authority")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission created successfully")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionDto createPermission(@RequestBody AddPermission permission) {
        return permissionService.create(permission);
    }

}
