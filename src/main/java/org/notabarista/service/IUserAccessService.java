package org.notabarista.service;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.exception.AbstractNotabaristaException;

import java.util.List;
import java.util.Set;

public interface IUserAccessService {


	<T> Boolean canAccess(String userId, String actionName, String action, Class<T> clazz, List<T> entities) throws AbstractNotabaristaException;

	Set<UserActionDTO> getAllActionsForRoles(List<String> roleList, String modelName, String actionName, String userName)
			throws AbstractNotabaristaException;

	
	
}
