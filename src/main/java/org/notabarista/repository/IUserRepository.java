package org.notabarista.repository;

import java.util.Optional;

import org.notabarista.entity.UserEntity;

public interface IUserRepository extends IAbstractRepository<UserEntity> {

	Optional<UserEntity> findByUserIdentifier(String userIdentifier);

}
