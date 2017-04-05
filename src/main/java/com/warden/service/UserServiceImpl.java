package com.warden.service;

import com.warden.exception.UserNotFound;
import com.warden.model.UserEntity;
import com.warden.model.UserRoleEntity;
import com.warden.repository.UserRepository;
import com.warden.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by Warden on 2017/4/4.
 */
@Service
@SessionAttributes("admin")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void addUser(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user) throws UserNotFound {
        UserEntity userUpdate = userRepository.findOne(user.getUserId());
        if (userUpdate == null)
            throw new UserNotFound();
        if (user.getNickname() != null)
            userUpdate.setNickname(user.getNickname());
        if (user.getPassword() !=null)
            userUpdate.setPassword(user.getPassword());
        userRepository.save(userUpdate);
        return userUpdate;
    }


    @Override
    public UserEntity deleteUser(int id) throws UserNotFound {
        UserEntity userDelete = userRepository.findOne(id);
        if (userDelete == null)
            throw new UserNotFound();
        userRepository.delete(userDelete);
        return userDelete;
    }
    @Override
    public UserEntity getUser(int id) {
        UserEntity userEntity = userRepository.findOne(id);
        return userEntity;
    }

    @Override
    public UserEntity findByNickname(String nickname) {
        UserEntity userEntity = userRepository.findByNickname(nickname);
        return userEntity;
    }

    @Override
    public List<UserEntity> getUsers() {
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public int[] getRole(int id) {
        int [] role = new int[3];
        List<UserRoleEntity> roleList = userRoleRepository.findByUserId(id);
        for (int i = 0; i<roleList.size(); i++){
            role[i] = roleList.get(i).getRoleId();
            System.out.println(roleList.get(i).getRoleId());
        }


        return role;
    }

    @Override
    public void flush() {
        userRepository.flush();
    }
}

