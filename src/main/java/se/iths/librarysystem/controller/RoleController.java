package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.service.RoleService;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        Iterable<RoleEntity> roleEntities = roleService.getAllRoles();
        List<Role> roles = new ArrayList<>();
        roleEntities.forEach(role -> roles.add(modelMapper.map(role, Role.class)));

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        RoleEntity roleEntity = roleService.getRoleById(id).orElseThrow(() -> new IdNotFoundException("role", id));
        Role role = modelMapper.map(roleEntity, Role.class);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);
        RoleEntity savedRoleEntity = roleService.createRole(roleEntity);
        Role savedRole = modelMapper.map(savedRoleEntity, Role.class);
        return ResponseEntity
                .created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequest().build().toString() + savedRoleEntity.getId()))
                .body(savedRole);
    }

}
