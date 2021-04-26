package org.notabarista.service;

import java.util.List;
import java.util.Set;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.entity.CanAccessDetails;
import org.notabarista.exception.AbstractNotabaristaException;

public interface IUserAccessService {

	Set<UserActionDTO> getAllActionsForRoles(List<String> roleList) throws AbstractNotabaristaException;

	<T> Boolean canAccess(String userIdentifier, String action, String resource, String microserviceName)
			throws AbstractNotabaristaException;

	void checkAccess(CanAccessDetails accessDetails) throws AbstractNotabaristaException;
	
}
