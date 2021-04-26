package org.notabarista.service;

import java.util.Optional;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.abstr.IDeleteService;

public interface IUserService extends IDeleteService<UserEntity, UserDTO> {

	Optional<UserDTO> findByUserIdentifier(String userIdentifier);

	UserDTO processUser(String userIdentifier) throws AbstractNotabaristaException;
	
}
