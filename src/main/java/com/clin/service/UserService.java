package com.clin.service;

import com.clin.entity.Primessage;
import com.clin.entity.User;

import java.util.List;

public interface UserService {
    public User login(String Uid , String Upasswd);
    public List<User> query_friend(String Uid);
    public List<Primessage> query_msg(String Uid,String friendID);
}
