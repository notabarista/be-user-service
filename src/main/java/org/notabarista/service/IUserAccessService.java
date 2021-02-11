package org.notabarista.service;

import java.util.List;

import org.notabarista.entity.UserActionEntity;
import org.notabarista.exception.AbstractNotabaristaException;

public interface IUserAccessService {


	<T> Boolean canAccess(String userId, String actionName, Class<T> clazz, List<T> entities) throws AbstractNotabaristaException;

	List<UserActionEntity> getAllActionsForRoles(List<String> roleList, String modelName, String actionName, String userName)
			throws AbstractNotabaristaException;

	
	
}
