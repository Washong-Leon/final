package com.clin.dao;

import com.clin.entity.Primessage;
import org.springframework.web.bind.annotation.RequestBody;

public interface PriMsgDao {
    public int insertMsg(Primessage primessage);
}
