package com.decimal.rbac.controller;

import com.decimal.rbac.model.rest.request.RoleAssignmentRequest;
import com.decimal.rbac.service.RoleAssignmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("assignment")
@RestController
public class AssignmentController {

    private final RoleAssignmentService roleAssignmentService;

    public AssignmentController(RoleAssignmentService roleAssignmentService) {
        this.roleAssignmentService = roleAssignmentService;
    }

    @PostMapping
    public Map<String, String> assignUserToRole(@RequestBody RoleAssignmentRequest request){
        roleAssignmentService.addUserToRole(request.getRoleId(), request.getUserId());
        return Map.of("message","Role has been assigned to the user.");
    }
}