package com.xforceplus.data.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xforceplus.data.bean.User;
import com.xforceplus.data.dao.VehicleUserRepository;

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
        return "unitInformation/main";
    }
    
    @RequestMapping("/managecar")
    public String managecar() {
    	return "vehicleManagement/managecar";
    }
    
    @RequestMapping("/addunitinformation")
    public String addunitinformation() {
    	return "unitInformation/addunitinformation";
    }
    
    @RequestMapping("/updateunitinformation/{id}")
    public String updateunitinformation(HttpSession session,@PathVariable int id) {
    	session.setAttribute("updateid", id);
		return "unitInformation/updateunitinformation";
    }
    
    @RequestMapping("/homePage")
    public String homePage(Map<String,Object> map,@RequestParam(value = "userName",required = false) String userName, @RequestParam(value = "password",required = false) String password,HttpSession session){
        User user = userLogin.findByUserNameAndPassword(userName,password);
        if(user == null){
            map.put("error","用户名密码错误！");
            map.put("status",false);
            return "login";
        }else {
        	session.setAttribute("loginName", user.getUserName());
            return "homePage";
        }
    }
}
