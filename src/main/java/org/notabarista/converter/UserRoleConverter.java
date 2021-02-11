package org.notabarista.converter;

import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter implements GenericConverter<UserRoleEntity, UserRoleDTO> {

	@Autowired
	private UserActionConverter userActionConverter;
	
	@Override
	public UserRoleEntity createFrom(UserRoleDTO dto) {
		return UserRoleEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.description(dto.getDescription())
				.userActions(userActionConverter.createFromDtos(dto.getUserActions()))
				
				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserRoleDTO createFrom(UserRoleEntity entity) {

		return UserRoleDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.description(entity.getDescription())
				.userActions(userActionConverter.createFromEntities(entity.getUserActions()))
				
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserRoleDTO updateEntity(UserRoleEntity entity, UserRoleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
