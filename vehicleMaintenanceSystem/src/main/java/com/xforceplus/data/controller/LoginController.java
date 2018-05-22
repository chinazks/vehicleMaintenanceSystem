package com.xforceplus.data.controller;


import com.xforceplus.data.bean.User;
import com.xforceplus.data.dao.VehicleUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/10/11.
 */
@Controller
public class LoginController {
    private Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private VehicleUserRepository userLogin;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    
    @RequestMapping("/homePage")
    public String homePage(Map<String,Object> map,@RequestParam(value = "userName",required = false) String userName, @RequestParam(value = "password",required = false) String password,HttpSession session){
        List<Map<String,String>> listMap = new ArrayList<>();//传到前端
        System.out.println(userName+password);
        User user = userLogin.findByUserNameAndPassword(userName,password);
        if(user == null){
            map.put("error","用户名密码错误！");
            map.put("status",false);
            return "login";
        }else {
            return "homePage";
        }
    }
}
