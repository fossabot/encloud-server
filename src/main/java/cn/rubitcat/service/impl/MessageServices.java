package cn.rubitcat.service.impl;

import cn.rubitcat.dao.database.IMessageDao;
import cn.rubitcat.domain.database.Message;
import cn.rubitcat.service.IMessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServices implements IMessageServices {
    @Autowired
    IMessageDao messageDao;

    @Override
    public List<Message> getMessages(Integer accountID) {
        List<Message> messageByReceiverID = messageDao.findMessageByReceiverID(accountID);
        return messageByReceiverID;
    }

}
