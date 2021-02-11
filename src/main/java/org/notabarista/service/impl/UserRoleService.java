package org.notabarista.service.impl;

import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.UserRoleEntity;
import org.notabarista.service.IUserRoleService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends AbstractDeleteService<UserRoleEntity, UserRoleDTO> implements IUserRoleService {

}
