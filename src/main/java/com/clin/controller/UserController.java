package com.clin.controller;

import com.clin.Do.ID;
import com.clin.entity.Primessage;
import com.clin.entity.User;
import com.clin.service.UserService;
import com.clin.util.RedisService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.clin.exception.LoginException;
import sun.awt.SunHints;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name="redisService")
    private RedisService redisService;

    /**
     * 登录
     * @param Uid
     * @param Upasswd
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam(value = "Uid")String Uid,
                        @RequestParam(value = "Upasswd")String Upasswd,
                        HttpSession session){
        User user=userService.login(Uid,Upasswd);
        if(user!=null){
            session.setAttribute("user",user);
            return "main";
        }
        return "login";
    }
    @RequestMapping(value="/im/{uid}")
    public String im(){
        return "im";
    }
    /**
     * 查询个人名字头像
     * @param session
     * @return
     */
    @RequestMapping("/query_myself")
    @ResponseBody
    public User query_myselft(HttpSession session){

        User user=(User)session.getAttribute("user");
        return user;
    }

    /**
     * 查询好友列表
     * @param session
     * @return
     */
    @RequestMapping("/query_myfriend")
    @ResponseBody
    public List<User> query_myfriend(HttpSession session){
        User user=(User)session.getAttribute("user");
        List<User> list=userService.query_friend(user.getUid());
return list;
    }

    /**
     * 根据用户ID跟对方ID 查询聊天记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/query_mes",method = RequestMethod.POST)
    @ResponseBody
    public List<Primessage> query_mes(@RequestBody ID id){
          String Uid=id.getUid();
          String friendID=id.getrUid();
        System.out.println(Uid);
        System.out.println(friendID);
        List<Primessage> list=userService.query_msg(Uid,friendID);
        return list;
    }
    @RequestMapping(value="/click")
    @ResponseBody
    public void click() throws IOException {
       /* WSServer wsServer=new WSServer();
        wsServer.sendAllFans("edc");*/
       redisService.sendMessage("1608030149","ccc");
    }
}
