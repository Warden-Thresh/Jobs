package com.warden.repository;

import com.warden.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Warden on 2017/3/25.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.nickname=:qNickname, us.firstName=:qFirstName, us.lastName=:qLastName, us.password=:qPassword where us.userId=:qId")
    void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstName,
                    @Param("qLastName") String LastName, @Param("qPassword") String password, @Param("qId") Integer userId);

    UserEntity findByNickname(String nickname);
}