package org.notabarista.converter;

import org.notabarista.dto.NothingDTO;
import org.notabarista.entity.NothingEntity;
import org.springframework.stereotype.Component;

@Component
public class NothingConverter implements GenericConverter<NothingEntity,NothingDTO>{

	@Override
	public NothingEntity createFrom(NothingDTO dto) {
		// TODO Auto-generated method stub
		return new NothingEntity();
	}

	@Override
	public NothingDTO createFrom(NothingEntity entity) {

//		NothingDTO nnn = new NothingDTObuilder()
//				
//				.build();
		
		
		return new NothingDTO();
	}

	@Override
	public NothingDTO updateEntity(NothingEntity entity, NothingDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
