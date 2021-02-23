package org.notabarista.repository;

import org.notabarista.entity.UserRoleEntity;

import java.util.Optional;

public interface IUserRoleRepository extends IAbstractRepository<UserRoleEntity> {

    Optional<UserRoleEntity> findByName(String name);

}
