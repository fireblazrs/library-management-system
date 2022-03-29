package se.iths.librarysystem.validatorservice;

import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.InvalidValueException;
import se.iths.librarysystem.service.RoleService;

public class RoleValidator extends LibraryValidator {

    RoleService roleService;

    public RoleValidator(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void validId(Long id) {
        if(id == null || id < 1L)
            throw new InvalidValueException(id + " is an invalid id.", "/roles/");
    }

    @Override
    public void idExists(Long id) {
        roleService.findById(id).orElseThrow(() -> new IdNotFoundException("role", id));
    }
}
