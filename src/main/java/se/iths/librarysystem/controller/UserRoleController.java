package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.dto.UserWithRole;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.RoleValidator;
import se.iths.librarysystem.validatorservice.UserValidator;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserRoleController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleValidator roleValidator;

    public UserRoleController(UserService userService, UserValidator userValidator, RoleValidator roleValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleValidator = roleValidator;
    }

    @GetMapping("{id}/role")
    public ResponseEntity<List<Role>> getUserRole(@PathVariable Long id) {
        List<Role> roles = userService.getUserRoles(id);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PatchMapping("{userId}/role/{roleId}")
    public ResponseEntity<UserWithRole> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userValidator.validId(userId);
        roleValidator.validId(roleId);
        UserWithRole user = userService.addRoleToUser(userId, roleId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
