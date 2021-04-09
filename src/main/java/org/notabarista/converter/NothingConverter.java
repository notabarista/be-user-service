package org.notabarista.converter;

import org.notabarista.dto.NothingDTO;
import org.notabarista.entity.NothingEntity;
import org.springframework.stereotype.Component;

@Component
public class NothingConverter implements GenericJPAConverter<NothingEntity, NothingDTO> {

	@Override
	public NothingEntity createFromSource(NothingDTO dto) {
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
	public NothingDTO createFromTarget(NothingEntity entity) {

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
	public NothingDTO updateSource(NothingEntity entity, NothingDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
