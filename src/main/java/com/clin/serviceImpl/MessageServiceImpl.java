package com.clin.serviceImpl;

import com.clin.dao.MessageDao;
import com.clin.entity.Message;
import com.clin.service.MessageService;

import javax.annotation.Resource;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;
    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }
}
