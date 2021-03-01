package org.notabarista.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.dto.UserDTO;
import org.notabarista.dto.UserProfileDTO;
import org.notabarista.dto.UserRoleDTO;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserAccessService;
import org.notabarista.service.IUserRoleService;
import org.notabarista.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserAccessService implements IUserAccessService {

	@Autowired
	private IUserService usersService;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	public Client client;
	
	@Override
	public <T> Boolean canAccess(String userIdentifier, String action, Class<T> entity) throws AbstractNotabaristaException {
			
		Optional<UserDTO> user = usersService.findByUserIdentifier(userIdentifier);
		
		if (!user.isPresent()) {
			user = Optional.of(processNewUser(userIdentifier));
		}

		if (log.isDebugEnabled()) {
			log.debug("canAccess user id:" + user.get().getId() + "User identifier:" + user.get().getUserIdentifier());
		}
		if (log.isTraceEnabled()) {
			log.trace("canAccess user:" + user);
		}

		List<String> userRoles = user.get().getUserRoles().stream().map(ur -> ur.getName()).collect(Collectors.toList());
		Set<UserActionDTO> userActions = getAllActionsForRoles(userRoles);
		
		if (log.isDebugEnabled()) {
			log.debug("canAccess user roles:" + Arrays.asList(userRoles) + " User actions size:" + userActions.size());
		}
		return checkAccess(userActions, action, entity);
	}

	@Override
	public Set<UserActionDTO> getAllActionsForRoles(List<String> roleList) throws AbstractNotabaristaException {
		Set<UserActionDTO> userActions = new HashSet<>();

		roleList.forEach(role -> {
			Optional<UserRoleDTO> userRoleDTOOptional = userRoleService.findByName(role);
			if (userRoleDTOOptional.isPresent()) {
				userActions.addAll(userRoleDTOOptional.get().getUserActions());
			}
		});

		return userActions;
	}
	
	private <T> Boolean checkAccess(Set<UserActionDTO> userActions, String action, Class<T> entity)
			throws AbstractNotabaristaException {

//		find first based on searched action AND entity -> then it has access
		Optional<UserActionDTO> foundUserAction = userActions.parallelStream()
				.filter(ua -> ua.getAction().equals(action) && ua.getEntityName().equals(entity.getSimpleName().toLowerCase()))
				.findAny();

		if (!foundUserAction.isPresent()) {
			log.warn("Insufficient rights to execute.");
			return false;
		}

		if (log.isTraceEnabled()) {
			log.trace("checkAccess: true");
		}
		return true;
	}

	private UserDTO processNewUser(String userIdentifier) throws AbstractNotabaristaException {
		log.warn("Processing new user with identifier: '" + userIdentifier + "'");

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
								 .createdAt(Date.from(Instant.now()))
								 .modifiedAt(Date.from(Instant.now()))
								 .createdBy("system")
								 .modifiedBy("system")
								 .build();
		UserDTO user = null;
		try {
			user = usersService.insert(userDTO);
		} catch (Exception e) {
			throw new AbstractNotabaristaException("Error creating new user: " + e);
		}

		if(log.isInfoEnabled()) {
			log.info("Added new user: " + user);
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
