package org.notabarista.service;

import java.util.Optional;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.service.abstr.IBaseService;

public interface IUserService extends IBaseService<UserEntity, UserDTO> {

	public Optional<UserDTO> findByUserIdentifier(String userIdentifier);
	
}
