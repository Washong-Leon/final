package com.clin.serviceImpl;

import com.clin.dao.UserDao;
import com.clin.entity.Primessage;
import com.clin.entity.User;
import com.clin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(String Uid, String Upasswd) {
        return userDao.login(Uid,Upasswd);
    }

    @Override
    public List<User> query_friend(String Uid) {
        return userDao.query_friend(Uid);
    }

    @Override
    public List<Primessage> query_msg(String Uid, String friendID) {
        return userDao.query_mes(Uid,friendID);
    }
}
