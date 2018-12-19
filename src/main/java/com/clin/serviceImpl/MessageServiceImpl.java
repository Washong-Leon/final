package com.clin.serviceImpl;

import com.clin.dao.MessageDao;
import com.clin.entity.Message;
import com.clin.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;
    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }
}
