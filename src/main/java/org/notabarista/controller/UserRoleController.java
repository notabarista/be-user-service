package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.UserRoleEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class UserRoleController extends AbstractDeleteController<UserRoleEntity, UserRoleDTO> {

}
