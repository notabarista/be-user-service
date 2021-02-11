package org.notabarista.converter;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements GenericConverter<UserEntity, UserDTO> {

	@Autowired
	private UserProfileConverter userProfileConverter;
	
	@Autowired
	private UserRoleConverter userRoleConverter;
	
	@Override
	public UserEntity createFrom(UserDTO dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.userIdentifier(dto.getUserIdentifier())
				.username(dto.getUsername())
				.email(dto.getEmail())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				
				.userProfile(userProfileConverter.createFrom(dto.getUserProfile()))
				.userRoles(userRoleConverter.createFromDtos(dto.getUserRoles()))

				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserDTO createFrom(UserEntity entity) {

		return UserDTO.builder()
				.id(entity.getId())
				.userIdentifier(entity.getUserIdentifier())
				.username(entity.getUsername())
				.email(entity.getEmail())
				.firstName(entity.getFirstName())
				.lastName(entity.getLastName())
				
				.userProfile(userProfileConverter.createFrom(entity.getUserProfile()))
				.userRoles(userRoleConverter.createFromEntities(entity.getUserRoles()))
				
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserDTO updateEntity(UserEntity entity, UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
