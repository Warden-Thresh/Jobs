package com.warden.service;

import com.warden.exception.UserNotFound;
import com.warden.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by Warden on 2017/4/4.
 */
public interface UserService {
    //添加用户
    public void addUser(UserEntity user);
    //修改用户
    public UserEntity updateUser(UserEntity user) throws UserNotFound;
    //根据用户id删除
    public UserEntity deleteUser(int id) throws UserNotFound;
    //查询单个用户
    public  UserEntity getUser(int id);
    public  UserEntity findByNickname(String nickname);
    //查询所有用户
    public List<UserEntity> getUsers();
    public int [] getRole(int id);
    public void flush();
}
