package org.notabarista.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserActionEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserAccessService;
import org.notabarista.service.IUserService;
import org.notabarista.service.util.IBackendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserAccessService implements IUserAccessService {


	@Autowired
	private IUserService usersService;
	
//	private IRoleActionsRepository sfjnf;
//	get all roles
	
	@Override
	public <T> Boolean canAccess(String userId, String actionName, Class<T> clazz, List<T> entities) throws AbstractNotabaristaException {
			
//		return checkAccess(request, actionName, clazz);
		
		return null;
	}

	@Override
	public List<UserActionEntity> getAllActionsForRoles(List<String> roleList, String modelName, String actionName,
			String userName) throws AbstractNotabaristaException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private <T> Boolean checkAccess(HttpServletRequest request, String actionName, Class<T> clazz)
			throws AbstractNotabaristaException {

		String userIdentifier = request.getHeader("uid");
		Optional<UserDTO> user = usersService.findByUserIdentifier(userIdentifier);
		
		
		if (user.isPresent()) {
			
		} else {

//			check with OKTA sdk that the user exists

			
		}
		
//		
//		
//		if (log.isDebugEnabled()) {
//			log.debug("Email from header:" + emailHeader);
//		}
//
//		List<UserActionEntity> userActions = getAllActionsForRoles(roles, clazz.getSimpleName().toLowerCase(), actionName,
//				userName);
//		if (log.isDebugEnabled()) {
//			log.debug("User actions:" + userActions);
//		}
//		
////		find first based on searched actionName -> then it has access
////		userActions.parallelStream().filter(ua -> ua.ge)
//
//		if (!) {
//			log.warn("Insufficent right to execute.");
//			return false;
//		}
		return true;
	}
	
	
	
}
