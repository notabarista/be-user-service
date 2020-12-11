package org.notabarista.converter;

import org.notabarista.dto.NothingDTO;
import org.notabarista.entity.NothingEntity;
import org.springframework.stereotype.Component;

@Component
public class NothingConverter implements GenericConverter<NothingEntity, NothingDTO> {

	@Override
	public NothingEntity createFrom(NothingDTO dto) {
		return NothingEntity.builder().id(dto.getId())

				.name(dto.getName())
				.description(dto.getDescription())
				.someNumber(dto.getSomeNumber())
				.isActive(dto.getIsActive())

				.createdAt(dto.getCreatedAt())
				.createdBy(dto.getCreatedBy())
				.modifiedAt(dto.getModifiedAt())
				.modifiedBy(dto.getModifiedBy())
				.build();
	}

	@Override
	public NothingDTO createFrom(NothingEntity entity) {

		return NothingDTO.builder()
				.id(entity.getId())

				.name(entity.getName())
				.description(entity.getDescription())
				.someNumber(entity.getSomeNumber())
				.isActive(entity.getIsActive())

				.createdAt(entity.getCreatedAt())
				.createdBy(entity.getCreatedBy())
				.modifiedAt(entity.getModifiedAt())
				.modifiedBy(entity.getModifiedBy())
				.build();
	}

	@Override
	public NothingDTO updateEntity(NothingEntity entity, NothingDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
