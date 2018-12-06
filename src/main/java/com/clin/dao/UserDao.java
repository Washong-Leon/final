package com.clin.dao;

import com.clin.entity.Primessage;
import com.clin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public User login(@Param("Uid") String Uid,
                      @Param("Upasswd") String Upasswd);
    public List<User> query_friend(@Param("Uid") String Uid);
    public List<Primessage> query_mes(@Param("Uid") String Uid,
                                      @Param("friendID")String friendID);
}
