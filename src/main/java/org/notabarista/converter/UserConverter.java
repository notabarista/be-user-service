package org.notabarista.converter;

import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements GenericJPAConverter<UserEntity, UserDTO> {

	@Autowired
	private UserProfileConverter userProfileConverter;
	
	@Autowired
	private UserRoleConverter userRoleConverter;
	
	@Override
	public UserEntity createFromSource(UserDTO dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.userIdentifier(dto.getUserIdentifier())
				.username(dto.getUsername())
				.email(dto.getEmail())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())

				.userProfile(null != dto.getUserProfile() ? userProfileConverter.createFromSource(dto.getUserProfile()) : null)
				.userRoles(userRoleConverter.createFromSources(dto.getUserRoles()))

				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserDTO createFromTarget(UserEntity entity) {

		return UserDTO.builder()
				.id(entity.getId())
				.userIdentifier(entity.getUserIdentifier())
				.username(entity.getUsername())
				.email(entity.getEmail())
				.firstName(entity.getFirstName())
				.lastName(entity.getLastName())
				
				.userProfile(null != entity.getUserProfile() ? userProfileConverter.createFromTarget(entity.getUserProfile()) : null)
				.userRoles(userRoleConverter.createFromTargets(entity.getUserRoles()))
				
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserDTO updateSource(UserEntity entity, UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
