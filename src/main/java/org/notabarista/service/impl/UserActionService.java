package org.notabarista.service.impl;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.entity.UserActionEntity;
import org.notabarista.service.IUserActionService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.stereotype.Service;

@Service
public class UserActionService extends AbstractDeleteService<UserActionEntity, UserActionDTO> implements IUserActionService {

}
