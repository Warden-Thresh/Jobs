package com.warden.service;

import com.warden.model.JobEntity;
import com.warden.model.UserEntity;
import org.springframework.ui.ModelMap;

/**
 * Created by Warden on 2017/4/4.
 */
public interface LoginService {
    public String login(UserEntity loginUser, ModelMap modelMap);
}
