package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserProfileDataDTO;
import org.notabarista.entity.UserProfileDataEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile-data")
public class UserProfileDataController extends AbstractDeleteController<UserProfileDataEntity, UserProfileDataDTO> {

}
