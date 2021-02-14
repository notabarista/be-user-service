package org.notabarista.service.impl;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import lombok.extern.log4j.Log4j2;
import org.notabarista.dto.UserActionDTO;
import org.notabarista.dto.UserDTO;
import org.notabarista.dto.UserRoleDTO;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserAccessService;
import org.notabarista.service.IUserRoleService;
import org.notabarista.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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
	public <T> Boolean canAccess(String userId, String actionName, String action, Class<T> clazz, List<T> entities) throws AbstractNotabaristaException {
			
		return checkAccess(userId, actionName, action, clazz);

	}

	@Override
	public Set<UserActionDTO> getAllActionsForRoles(List<String> roleList, String modelName, String actionName,
													 String userName) throws AbstractNotabaristaException {
		Set<UserActionDTO> userActions = new HashSet<>();

		roleList.forEach(role -> {
			Optional<UserRoleDTO> userRoleDTOOptional = userRoleService.findByName(role);
			if (userRoleDTOOptional.isPresent()) {
				userActions.addAll(userRoleDTOOptional.get().getUserActions());
			}
		});

		return userActions;
	}
	
	private <T> Boolean checkAccess(String userIdentifier, String actionName, String action, Class<T> clazz)
			throws AbstractNotabaristaException {

		Optional<UserDTO> user = usersService.findByUserIdentifier(userIdentifier);
		
		if (!user.isPresent()) {
			user = processNewUser(userIdentifier);
		}

		if (log.isDebugEnabled()) {
			log.debug("User identifier:" + userIdentifier);
		}

		List<String> userRoles = user.get().getUserRoles().stream().map(ur -> ur.getName()).collect(Collectors.toList());
		Set<UserActionDTO> userActions = getAllActionsForRoles(userRoles, clazz.getSimpleName().toLowerCase(), actionName,
				user.get().getUsername());
		if (log.isDebugEnabled()) {
			log.debug("User actions:" + Arrays.asList(userActions));
		}

//		find first based on searched actionName AND action -> then it has access
		Optional<UserActionDTO> foundUserAction = userActions.parallelStream().filter(ua -> ua.getName().equals(actionName) && ua.getAction().equals(action)).findAny();

		if (!foundUserAction.isPresent()) {
			log.warn("Insufficient rights to execute.");
			return false;
		}

		return true;
	}

	private Optional<UserDTO> processNewUser(String userIdentifier) {
		log.info("Processing new user id: '" + userIdentifier + "'");

		// get user info from Okta
		User userInfo = client.getUser(userIdentifier);

		// TODO add required default data to new user
		UserDTO userDTO = UserDTO.builder()
								 .userIdentifier(userIdentifier)
								 .email(userInfo.getProfile().getEmail())
								 .firstName(userInfo.getProfile().getFirstName())
								 .lastName(userInfo.getProfile().getLastName())
								 .username(userInfo.getProfile().getEmail())
								 .userProfile(null)
								 .userRoles(getDefaultRoles())
								 .createdAt(Date.from(Instant.now()))
								 .modifiedAt(Date.from(Instant.now()))
								 .createdBy("system")
								 .modifiedBy("system")
								 .build();
		Optional<UserDTO> user = usersService.createUser(userDTO);

		log.info("Processed new user id: '" + userIdentifier + "': " + user.get());

		return user;
	}

	private List<UserRoleDTO> getDefaultRoles() {
		// TODO is this enough?
		List<UserRoleDTO> defaultUserRoles = new ArrayList<>();
		Optional<UserRoleDTO> userRoleDTOOptional = userRoleService.findByName("default");
		if (userRoleDTOOptional.isPresent()) {
			defaultUserRoles.add(userRoleDTOOptional.get());
		}

		return defaultUserRoles;
	}


}
