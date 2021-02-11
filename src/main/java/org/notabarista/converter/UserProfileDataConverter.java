package org.notabarista.converter;

import org.notabarista.dto.UserProfileDataDTO;
import org.notabarista.entity.UserProfileDataEntity;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDataConverter implements GenericConverter<UserProfileDataEntity, UserProfileDataDTO> {

	@Override
	public UserProfileDataEntity createFrom(UserProfileDataDTO dto) {
		return UserProfileDataEntity.builder()
				.id(dto.getId())
				.key(dto.getKey())
				.data(dto.getData())
//				.userProfileData(userProfileConverter.createFrom(dto.getUserProfile()))
				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.version(dto.getVersion())
				.build();
	}

	@Override
	public UserProfileDataDTO createFrom(UserProfileDataEntity entity) {

		return UserProfileDataDTO.builder()
				.id(entity.getId())
				.key(entity.getKey())
				.data(entity.getData())

				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.version(entity.getVersion())
				.build();
	}

	@Override
	public UserProfileDataDTO updateEntity(UserProfileDataEntity entity, UserProfileDataDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
