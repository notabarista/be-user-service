package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserActionDTO;
import org.notabarista.entity.UserActionEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class UserActionController extends AbstractDeleteController<UserActionEntity, UserActionDTO> {

}
