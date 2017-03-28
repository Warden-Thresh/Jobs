package com.warden.repository;

import com.warden.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Warden on 2017/3/25.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}