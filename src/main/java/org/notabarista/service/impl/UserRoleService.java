package org.notabarista.service.impl;

import org.notabarista.converter.UserRoleConverter;
import org.notabarista.dto.UserRoleDTO;
import org.notabarista.entity.UserRoleEntity;
import org.notabarista.repository.IUserRoleRepository;
import org.notabarista.service.IUserRoleService;
import org.notabarista.service.abstr.impl.AbstractDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService extends AbstractDeleteService<UserRoleEntity, UserRoleDTO> implements IUserRoleService {

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleConverter userRoleConverter;

    @Override
    public Optional<UserRoleDTO> findByName(String name) {
        UserRoleDTO userRoleDTO = null;
        Optional<UserRoleEntity> userRoleEntityOptional = userRoleRepository.findByName(name);
        if (userRoleEntityOptional.isPresent()) {
            userRoleDTO = userRoleConverter.createFromTarget(userRoleEntityOptional.get());
        }

        return Optional.ofNullable(userRoleDTO);
    }

}
