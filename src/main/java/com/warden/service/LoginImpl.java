package com.warden.service;

import com.warden.model.JobEntity;
import com.warden.model.UserEntity;
import com.warden.model.UserRoleEntity;
import com.warden.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by Warden on 2017/4/4.
 */
@Service
@SessionAttributes()
public class LoginImpl implements LoginService{
    @Autowired
    UserService userService;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public String login(UserEntity loginUser,ModelMap modelMap) {
        Integer id = loginUser.getUserId();
        String nickname = loginUser.getNickname();
        String password = loginUser.getPassword();
        System.out.println("用id："+id+"\n"+"用户名："+nickname);
        System.out.println("密码："+password);
        UserEntity userEntity = userService.findByNickname(nickname);
        int[]role = userService.getRole(id);
        System.out.println("chaxun到密码："+ userEntity.getPassword());
        if (userEntity.getPassword().equals(password)){

            modelMap.addAttribute("admin",userEntity );
            System.out.println("密码正确");
            return "redirect:/admin/users";

        }else {
            System.out.println("erro");
            return "/admin/login";
        }
    }
}
