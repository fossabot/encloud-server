package cn.rubitcat.service;

import cn.rubitcat.domain.database.Account;
import cn.rubitcat.domain.database.Message;
import cn.rubitcat.domain.database.User;

import java.util.List;

public interface IUserServices {
    public Boolean  registration(User user, Account account);
    public Account  login(String  accountName, String password);
    public User     getUser(Integer accountID);
    public Account  getAccount(Integer accountID);
    public Boolean  setAccountRootDirID(Integer accountID, String rootDirID);

}
