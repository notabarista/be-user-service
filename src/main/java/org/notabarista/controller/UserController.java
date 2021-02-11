package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractDeleteController<UserEntity, UserDTO> {

}
