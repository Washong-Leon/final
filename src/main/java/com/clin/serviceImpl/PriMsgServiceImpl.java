package com.clin.serviceImpl;

import com.clin.dao.PriMsgDao;
import com.clin.entity.Message;
import com.clin.entity.Primessage;
import com.clin.service.PriMsgService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("priMsgService")
public class PriMsgServiceImpl implements PriMsgService {
    @Resource
    private PriMsgDao priMsgDao;


    @Override
    public void insert(Message message) {
        Primessage primessage=new Primessage();
        primessage.setPMcontent(message.getContent());
        java.sql.Timestamp timestamp=new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println("到了service");
        primessage.setPMdatetime(timestamp);
        primessage.setPMid("7");
        primessage.setRUid(message.getTo());
        primessage.setUid(message.getFrom());
         priMsgDao.insertMsg(primessage);
    }
}
