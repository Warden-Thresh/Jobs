package com.warden.repository;

import com.warden.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Warden on 2017/4/3.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    public List<UserRoleEntity> findByUserId(int userId);

}