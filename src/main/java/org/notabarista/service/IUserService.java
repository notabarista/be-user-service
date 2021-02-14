package org.notabarista.service;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.service.abstr.IBaseService;

import java.util.Optional;

public interface IUserService extends IBaseService<UserEntity, UserDTO> {

	Optional<UserDTO> findByUserIdentifier(String userIdentifier);
	Optional<UserDTO> createUser(UserDTO userDTO);
	
}
