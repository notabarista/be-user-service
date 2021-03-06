package org.notabarista.converter;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.entity.UserActionEntity;
import org.springframework.stereotype.Component;

@Component
public class UserActionConverter implements GenericJPAConverter<UserActionEntity, UserActionDTO> {

	@Override
	public UserActionEntity createFromSource(UserActionDTO dto) {
		return UserActionEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.serviceName(dto.getServiceName())
				.path(dto.getPath())
				.action(dto.getAction())
				.resource(dto.getResource())
				.description(dto.getDescription())
				
				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserActionDTO createFromTarget(UserActionEntity entity) {

		return UserActionDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.serviceName(entity.getServiceName())
				.path(entity.getPath())
				.action(entity.getAction())
				.resource(entity.getResource())
				.description(entity.getDescription())
				
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserActionDTO updateSource(UserActionEntity entity, UserActionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
