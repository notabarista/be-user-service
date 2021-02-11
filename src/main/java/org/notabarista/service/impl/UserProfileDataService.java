package org.notabarista.service.impl;

import org.notabarista.dto.UserProfileDataDTO;
import org.notabarista.entity.UserProfileDataEntity;
import org.notabarista.service.IUserProfileDataService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDataService extends AbstractDeleteService<UserProfileDataEntity, UserProfileDataDTO>
		implements IUserProfileDataService {

}
