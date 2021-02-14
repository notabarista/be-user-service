package org.notabarista.service.impl;

import org.notabarista.converter.UserConverter;
import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.repository.IUserRepository;
import org.notabarista.service.IUserService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractDeleteService<UserEntity, UserDTO> implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public Optional<UserDTO> findByUserIdentifier(String userIdentifier) {
		
		Optional<UserEntity> userEntityOpt = userRepository.findByUserIdentifier(userIdentifier);
		
		UserDTO userDto = null;
		if (userEntityOpt.isPresent()) {
			userDto = userConverter.createFrom(userEntityOpt.get());
		}
		
		return Optional.ofNullable(userDto);
	}

	@Override
	public Optional<UserDTO> createUser(UserDTO userDTO) {
		UserDTO userDto = null;
		if (null != userDTO) {
			Optional<UserEntity> userEntityOpt = userRepository.findByUserIdentifier(userDTO.getUserIdentifier());
			if (!userEntityOpt.isPresent()) {
				UserEntity userEntity = userConverter.createFrom(userDTO);
				UserEntity savedUserEntity = userRepository.save(userEntity);
				// TODO add checks
				userDto = userConverter.createFrom(savedUserEntity);
			}
		}

		return Optional.ofNullable(userDto);
	}

}
