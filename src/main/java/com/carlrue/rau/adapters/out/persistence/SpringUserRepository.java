package com.carlrue.rau.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUserRepository extends JpaRepository<UserEntity, Long> {
}
