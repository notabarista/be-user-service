package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserProfileDTO;
import org.notabarista.entity.UserProfileEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileController extends AbstractDeleteController<UserProfileEntity, UserProfileDTO> {

}
