package org.notabarista.service.impl;

import org.notabarista.dto.UserProfileDTO;
import org.notabarista.entity.UserProfileEntity;
import org.notabarista.service.IUserProfileService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends AbstractDeleteService<UserProfileEntity, UserProfileDTO>
		implements IUserProfileService {

}
