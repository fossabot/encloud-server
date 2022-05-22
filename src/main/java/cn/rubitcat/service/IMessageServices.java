package cn.rubitcat.service;

import cn.rubitcat.domain.database.Message;

import java.util.List;

public interface IMessageServices {
    public List<Message> getMessages(Integer accountID);
}
