package org.notabarista.service;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.abstr.IBaseService;
import org.notabarista.service.abstr.IDeleteService;

import java.util.Optional;

public interface IUserService extends IDeleteService<UserEntity, UserDTO> {

	Optional<UserDTO> findByUserIdentifier(String userIdentifier);

	UserDTO processUser(String userIdentifier) throws AbstractNotabaristaException;
	
}
