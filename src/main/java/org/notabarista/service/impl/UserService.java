package org.notabarista.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.notabarista.converter.UserConverter;
import org.notabarista.dto.UserDTO;
import org.notabarista.dto.UserProfileDTO;
import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.repository.IUserRepository;
import org.notabarista.service.IUserRoleService;
import org.notabarista.service.IUserService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService extends AbstractDeleteService<UserEntity, UserDTO> implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	public Client client;
	
	@Override
	public Optional<UserDTO> findByUserIdentifier(String userIdentifier) {
		
		Optional<UserEntity> userEntityOpt = userRepository.findByUserIdentifier(userIdentifier);
		
		UserDTO userDto = null;
		if (userEntityOpt.isPresent()) {
			userDto = userConverter.createFromTarget(userEntityOpt.get());
		}
		
		return Optional.ofNullable(userDto);
	}

	@Override
	public UserDTO processUser(String userIdentifier) throws AbstractNotabaristaException {
		UserDTO user = null;

		Optional<UserDTO> userDTOOptional = findByUserIdentifier(userIdentifier);

		if (!userDTOOptional.isPresent()) {
			log.info("Processing new user with identifier: '" + userIdentifier + "'");

			// getting user info from Okta
			User userInfo = client.getUser(userIdentifier);

			// TODO add required default data to new user
			UserDTO userDTO = UserDTO.builder()
					.userIdentifier(userIdentifier)
					.email(userInfo.getProfile().getEmail())
					.firstName(userInfo.getProfile().getFirstName())
					.lastName(userInfo.getProfile().getLastName())
					.username(userInfo.getProfile().getEmail())
					//								 .userProfile(createDefaultUserProfile())
					.userRoles(getDefaultRoles())
					.createdAt(LocalDateTime.now())
					.modifiedAt(LocalDateTime.now())
					.createdBy("system")
					.modifiedBy("system")
					.build();
			try {
				user = insert(userDTO);
			} catch (Exception e) {
				throw new AbstractNotabaristaException("Error creating new user: " + e);
			}

			if(log.isInfoEnabled()) {
				log.info("Added new user: " + user);
			}
		} else {
			user = userDTOOptional.get();
		}

		return user;
	}

	private List<UserRoleDTO> getDefaultRoles() {
		List<UserRoleDTO> defaultUserRoles = new ArrayList<>();
		Optional<UserRoleDTO> userRoleDTOOptional = userRoleService.findByName("customer");
		if (userRoleDTOOptional.isPresent()) {
			defaultUserRoles.add(userRoleDTOOptional.get());
		}
		return defaultUserRoles;
	}

	private UserProfileDTO createDefaultUserProfile() {
		return UserProfileDTO.builder()
				.avatar("none")
				.phoneNumber("N/A")
				.userProfileData(null)
				.build();
	}

}
