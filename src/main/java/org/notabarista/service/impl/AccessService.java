package org.notabarista.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.notabarista.service.IAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

//@Service
//@Log4j2
public class AccessService implements IAccessService {

//	@Autowired
//	private ICommuniacationService communicationService;
//
//	@Override
//	public <T> Boolean canAccess(HttpServletRequest request, String actionName, Class<T> clazz, List<T> entities,
//			List<Integer> areaIds) throws AbstractBusinessException {
//		if (AbstractAreaEntity.class.isAssignableFrom(clazz)) {
//			return canAccessArea(request, actionName, clazz, entities, areaIds);
//		} else {
//			return canAccess(request, actionName, clazz);
//		}
//	}
//
//	@Override
//	public UserAccessRights getUserAccessRightsWithArea(String userName, String actionName, String modelName,
//			List<String> roleNames, List<Integer> areaIds) throws AbstractBusinessException {
//
//		if (log.isInfoEnabled()) {
//			log.info("Verify user access right with area.");
//		}
//		if (log.isDebugEnabled()) {
//			log.debug("Roles from header:" + roleNames);
//			log.debug("All action model name:" + modelName);
//			log.debug("All action action name:" + actionName);
//			log.debug("User name:" + userName);
//		}
//		try {
//			UserAreaRoleRights uarRights = new UserAreaRoleRights(userName, roleNames, areaIds, modelName, actionName);
//			Response<UserAccessRights> response = communicationService.<UserAccessRights>callPost(
//					MicroServices.AREA_MANAGEMENT_SERVICE, "/userarearole/hasRights", uarRights,
//					new ParameterizedTypeReference<Response<UserAccessRights>>() {
//					});
//			if (response.getStatus().equals(ResponseStatus.SUCCESS) && response.getData() != null
//					&& !CollectionUtils.isEmpty(response.getData())) {
//				Object data = response.getData().iterator().next();
//				return (UserAccessRights) data;
//			}
//		} catch (Exception e) {
//			log.error(e);
//			throw new AreaManagementServiceIsNotAccessible();
//		}
//		return null;
//	}
//
//	@Override
//	public UserAccessRights getAllActions(List<String> roleList, String modelName, String actionName, String userName)
//			throws UserManagementServiceNoFoundException {
//		if (log.isDebugEnabled()) {
//			log.debug("Roles from header:" + roleList);
//			log.debug("All action model name:" + modelName);
//			log.debug("All action action name:" + actionName);
//		}
//
//		try {
//			URI uri = communicationService.getServiceUri(MicroServices.USER_MANAGEMENT_SERVICE.getServiceName());
//			String path = uri.toString();
//			path = path + "/role/findActions";
//			if (log.isInfoEnabled()) {
//				log.info("User management path:" + path);
//			}
//			RestTemplate restTemplate = new RestTemplate();
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			HttpEntity<CheckUserRightsParameters> entity = new HttpEntity<>(
//					new CheckUserRightsParameters(modelName, userName, roleList, actionName), headers);
//
//			ResponseEntity<Response<UserAccessRights>> response = restTemplate.exchange(path, HttpMethod.POST, entity,
//					new ParameterizedTypeReference<Response<UserAccessRights>>() {
//					});
//
//			if (response.getStatusCodeValue() == 200 && !CollectionUtils.isEmpty(response.getBody().getData())) {
//				return response.getBody().getData().get(0);
//			}
//		} catch (Exception e) {
//			log.error(e);
//			throw new UserManagementServiceNoFoundException();
//		}
//		return null;
//	}
//
//	private <T> Boolean canAccessArea(HttpServletRequest request, String actionName, Class<T> clazz, List<T> entities,
//			List<Integer> areaIds) throws AbstractBusinessException {
//		List<String> roles = HeaderUtil.getRolesFromHeader(request);
//		String userName = HeaderUtil.getUserNameFromHeader(request);
//		if (log.isDebugEnabled()) {
//			log.debug("Action name:" + actionName);
//			log.debug("Roles from header:" + roles);
//		}
//		if (log.isTraceEnabled()) {
//			log.trace("User name from header:" + userName);
//		}
//
//		if (ActionName.READ.equalsIgnoreCase(actionName) && CollectionUtil.isEmpty(areaIds)) {
//			throw new AreaIdIsMissingException();
//		}
//
//		List<Integer> areaIdsLocal = ActionName.READ.equalsIgnoreCase(actionName) && areaIds != null ? areaIds
//				: getAreaId(clazz, entities);
//		UserAccessRights userAccessRights = this.getUserAccessRightsWithArea(userName, actionName,
//				clazz.getSimpleName(), roles, areaIdsLocal);
//
//		if (log.isDebugEnabled()) {
//			log.debug("User access rights from user management:" + userAccessRights);
//		}
//
//		if (!userAccessRights.getHasRights()) {
//			log.warn("Insufficent right to execute.");
//		}
//
//		if (!userAccessRights.getIsUserActive()) {
//			log.warn("The user is not active to execute.");
//		}
//		return userAccessRights.getHasRights() && userAccessRights.getIsUserActive();
//	}
//
//	private <T> Boolean canAccess(HttpServletRequest request, String actionName, Class<T> clazz)
//			throws AbstractBusinessException {
//
//		List<String> roles = HeaderUtil.getRolesFromHeader(request);
//		String userName = HeaderUtil.getUserNameFromHeader(request);
//		if (log.isDebugEnabled()) {
//			log.debug("Action name:" + actionName);
//			log.debug("Roles from header:" + roles);
//		}
//		if (log.isTraceEnabled()) {
//			log.trace("User name from header:" + userName);
//		}
//
//		UserAccessRights userAccessRights = getAllActions(roles, clazz.getSimpleName().toLowerCase(), actionName,
//				userName);
//		if (log.isDebugEnabled()) {
//			log.debug("User access rights from user management:" + userAccessRights);
//		}
//
//		if (!userAccessRights.getHasRights()) {
//			log.warn("Insufficent right to execute.");
//		}
//
//		if (!userAccessRights.getIsUserActive()) {
//			log.warn("The user is not active to execute.");
//		}
//		return userAccessRights.getHasRights() && userAccessRights.getIsUserActive();
//	}

}
