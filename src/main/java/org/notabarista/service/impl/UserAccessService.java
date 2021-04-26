package org.notabarista.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.dto.UserDTO;
import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.CanAccessDetails;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.exception.InsufficientRightsException;
import org.notabarista.service.IUserAccessService;
import org.notabarista.service.IUserRoleService;
import org.notabarista.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserAccessService implements IUserAccessService {

	@Autowired
	private IUserService usersService;

	@Autowired
	private IUserRoleService userRoleService;

	@Override
	public void checkAccess(CanAccessDetails accessDetails) throws AbstractNotabaristaException {
		if (!canAccess(accessDetails.getUid(), accessDetails.getAction(), accessDetails.getResource(),
				accessDetails.getMicroserviceName()))
			throw new InsufficientRightsException();
	}

	@Override
	public <T> Boolean canAccess(String userIdentifier, String action, String resource, String microserviceName)
			throws AbstractNotabaristaException {

		UserDTO user = usersService.processUser(userIdentifier);

		if (log.isDebugEnabled()) {
			log.debug("canAccess user id:" + user.getId() + "User identifier:" + user.getUserIdentifier());
		}
		if (log.isTraceEnabled()) {
			log.trace("canAccess user:" + user);
		}

		List<String> userRoles = user.getUserRoles().stream().map(ur -> ur.getName()).collect(Collectors.toList());
		Set<UserActionDTO> userActions = getAllActionsForRoles(userRoles);

		if (log.isDebugEnabled()) {
			log.debug("canAccess user roles:" + Arrays.asList(userRoles) + " User actions size:" + userActions.size());
		}
		return checkAccess(userActions, action, resource, microserviceName);
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

	private Boolean checkAccess(Set<UserActionDTO> userActions, String action, String resource,
			String microserviceName) {

//		find first based on searched action AND entity -> then it has access
		Optional<UserActionDTO> foundUserAction = userActions.parallelStream()
				.filter(ua -> ua.getAction().equals(action) && ua.getResource().equalsIgnoreCase(resource)
						&& ua.getServiceName().equalsIgnoreCase(microserviceName))
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

}
