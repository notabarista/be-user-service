package org.notabarista.service;

import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.UserRoleEntity;
import org.notabarista.service.abstr.IBaseService;

import java.util.Optional;

public interface IUserRoleService extends IBaseService<UserRoleEntity, UserRoleDTO> {

    Optional<UserRoleDTO> findByName(String name);

}
