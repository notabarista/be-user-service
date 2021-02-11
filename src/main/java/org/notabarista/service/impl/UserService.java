package org.notabarista.service.impl;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.service.IUserService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractDeleteService<UserEntity, UserDTO> implements IUserService {

}
