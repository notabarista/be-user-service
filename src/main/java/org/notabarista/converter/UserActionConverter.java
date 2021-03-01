package org.notabarista.converter;

import org.notabarista.dto.UserActionDTO;
import org.notabarista.entity.UserActionEntity;
import org.springframework.stereotype.Component;

@Component
public class UserActionConverter implements GenericConverter<UserActionEntity, UserActionDTO> {

	@Override
	public UserActionEntity createFrom(UserActionDTO dto) {
		return UserActionEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.serviceName(dto.getServiceName())
				.path(dto.getPath())
				.action(dto.getAction())
				.entityName(dto.getEntityName())
				.description(dto.getDescription())
				
				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserActionDTO createFrom(UserActionEntity entity) {

		return UserActionDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.serviceName(entity.getServiceName())
				.path(entity.getPath())
				.action(entity.getAction())
				.entityName(entity.getEntityName())
				.description(entity.getDescription())
				
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserActionDTO updateEntity(UserActionEntity entity, UserActionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
