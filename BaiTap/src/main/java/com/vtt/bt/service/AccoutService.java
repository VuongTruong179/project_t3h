package com.vtt.bt.service;

import com.vtt.bt.common.JWTUtil;
import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.LoginRequest;
import com.vtt.bt.model.NewPassword;
import com.vtt.bt.model.RegisterRequest;
import com.vtt.bt.model.entity.Rol;
import com.vtt.bt.model.entity.UserProfile;
import com.vtt.bt.model.entity.UserRol;
import com.vtt.bt.model.response.LoginResponse;
import com.vtt.bt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccoutService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private RolRepository rolRepository;


    @Autowired
    private UserRolRepository userRolRepository;


    public Object register(RegisterRequest request) {
        List<UserProfile> userProfiles = userProfileRepository.addUser(
                request.getUsername(), request.getEmail());
        if (userProfiles.size() > 0) {
            return new BaseResponse(BaseResponse.FAIL, "username or email is existed");
        } else {
            UserProfile userProfile = new UserProfile();
            userProfile.setUsername(request.getUsername());
            String newPassword = new BCryptPasswordEncoder().encode(request.getPassword());
            userProfile.setPassword(newPassword);
            userProfile.setAddress(request.getAddress());
            userProfile.setEmail(request.getEmail());
            userProfile.setFullname(request.getFullname());
            userProfile.setNumberphone(request.getNumberPhone());
            userProfile = userProfileRepository.save(userProfile);

            List<UserProfile> User = userProfileRepository.getUserByName(request.getUsername());
            for (UserProfile profile : User) {
                UserRol userRol = new UserRol();
                userRol.setUserOfId(profile.getId());
                userRol.setRolId(2);
                userRol = userRolRepository.save(userRol);
            }
            return new BaseResponse(BaseResponse.SUCCESS, "Register success", userProfile);
        }
    }

    public BaseResponse login(LoginRequest request) {
        List<UserProfile> userLogins = userProfileRepository.login(request.getUsername());
        BCryptPasswordEncoder endcod = new BCryptPasswordEncoder();
        for (UserProfile userLogin : userLogins) {
            if (userLogin.getUsername().equals(request.getUsername())) {
                if (endcod.matches(request.getPassword(), userLogin.getPassword())) {
                    //login thanh cong
                    BaseResponse res = new BaseResponse();
                    res.setCode(BaseResponse.SUCCESS);
                    //tra ve chuoi token (jwt) = endcode: userId, email, username
                    res.setMessage("Login success");
                    //sinh token
                    Map<String, Object> par = new HashMap<>();
                    par.put("email", userLogin.getEmail());
                    par.put("username", userLogin.getUsername());
                    String token = JWTUtil.getJWT(userLogin.getId() + "", par);
                    LoginResponse re = new LoginResponse(userLogin.getId(), userLogin.getUsername(), token);
                    res.setData(re);
                    return res;
                }
            }
        }
        //login that bai
        BaseResponse res = new BaseResponse();
        res.setCode(BaseResponse.FAIL);
        res.setMessage("Login fail");
        return res;
    }

    public Object changePassword(NewPassword request) {
        int userId = JWTUtil.getUserLogin();
        BCryptPasswordEncoder endcod = new BCryptPasswordEncoder();
        UserProfile user = userProfileRepository.getById(userId);
        if (endcod.matches(request.getOldPassword(), user.getPassword())) {
            if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
                userProfileRepository.changePassword(new BCryptPasswordEncoder().encode(request.getNewPassword()), userId);
                return new BaseResponse(BaseResponse.SUCCESS, "SUCCESS");
            }
        }
        BaseResponse res = new BaseResponse();
        res.setCode(BaseResponse.FAIL);
        res.setMessage("Change Password Fail");
        return res;

    }

    public Object getAllAccout() {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                List<UserProfile> userProfiles = userProfileRepository.getAllAccout();
                return userProfiles;
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, " ");
    }


    public Object deleteAccout(int Id) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                UserProfile userProfile = userProfileRepository.getAccout(Id);
                if (userProfile == null) {
                    return new BaseResponse(400, "Tài khoản không tồn tại ");
                } else {
                    userProfileRepository.deleteAccout(Id);
                    return new BaseResponse(BaseResponse.FAIL, "Success");
                }
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, " ");
    }

}
