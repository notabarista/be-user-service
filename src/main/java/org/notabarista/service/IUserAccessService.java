package org.notabarista.service;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.exception.AbstractNotabaristaException;

import java.util.List;
import java.util.Set;

public interface IUserAccessService {

	<T> Boolean canAccess(String userIdentifier, String action, Class<T> entity) throws AbstractNotabaristaException;

	Set<UserActionDTO> getAllActionsForRoles(List<String> roleList) throws AbstractNotabaristaException;

}
