package org.notabarista.converter;

import org.notabarista.dto.UserProfileDTO;
import org.notabarista.entity.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter implements GenericJPAConverter<UserProfileEntity, UserProfileDTO> {

	@Autowired
	private UserProfileDataConverter userProfileDataConverter;
	
	@Override
	public UserProfileEntity createFromSource(UserProfileDTO dto) {
		return UserProfileEntity.builder()
				.id(dto.getId())
				.phoneNumber(dto.getPhoneNumber())
				.avatar(dto.getAvatar())
				.userProfileData(userProfileDataConverter.createFromSources(dto.getUserProfileData()))
				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserProfileDTO createFromTarget(UserProfileEntity entity) {
		return UserProfileDTO.builder()
				.id(entity.getId())
				.phoneNumber(entity.getPhoneNumber())
				.avatar(entity.getAvatar())
				.userProfileData(userProfileDataConverter.createFromTargets(entity.getUserProfileData()))
				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserProfileDTO updateSource(UserProfileEntity entity, UserProfileDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
