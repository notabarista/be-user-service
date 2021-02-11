package org.notabarista.converter;

import org.notabarista.dto.UserProfileDTO;
import org.notabarista.entity.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter implements GenericConverter<UserProfileEntity, UserProfileDTO> {

	@Autowired
	private UserProfileDataConverter userProfileDataConverter;
	
	@Override
	public UserProfileEntity createFrom(UserProfileDTO dto) {
		return UserProfileEntity.builder()
				.id(dto.getId())
				.phoneNumber(dto.getPhoneNumber())
				.avatar(dto.getAvatar())
				.userProfileData(userProfileDataConverter.createFromDtos(dto.getUserProfileData()))
				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserProfileDTO createFrom(UserProfileEntity entity) {
		return UserProfileDTO.builder()
				.id(entity.getId())
				.phoneNumber(entity.getPhoneNumber())
				.avatar(entity.getAvatar())
				.userProfileData(userProfileDataConverter.createFromEntities(entity.getUserProfileData()))
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserProfileDTO updateEntity(UserProfileEntity entity, UserProfileDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
