package cn.rubitcat.service.impl;

import cn.rubitcat.dao.database.IAccountDao;
import cn.rubitcat.dao.database.IMessageDao;
import cn.rubitcat.dao.database.IUserDao;
import cn.rubitcat.dao.mongodb.IDirectoryDao;
import cn.rubitcat.domain.database.Account;
import cn.rubitcat.domain.database.Message;
import cn.rubitcat.domain.database.User;
import cn.rubitcat.service.IFileServices;
import cn.rubitcat.service.ISessionServices;
import cn.rubitcat.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServices implements IUserServices {
    @Autowired
    IUserDao    userDao;

    @Autowired
    IAccountDao accountDao;

    @Override
    public Boolean registration(User user, Account account) {
        userDao.addUser(user);
        account.setUserID(user.getUserID());
        accountDao.addAccount(account);
        Account checkout = accountDao.findAccountByID(account.getAccountID());
        return checkout  !=  null ? true : false ;
    }

    @Override
    public Account login(String accountName, String password) {
        Account account = accountDao.findByAccountName(accountName);
        if(account != null && account.getPassword().equals(password)){
            return account;
        }
        return null;
    }


    @Override
    public User getUser(Integer accountID) {
        User result = userDao.findByAccountID(accountID);
        return result;
    }

    @Override
    public Account getAccount(Integer accountID) {
        Account result = accountDao.findAccountByID(accountID);
        return result;
    }

    @Override
    public Boolean setAccountRootDirID(Integer accountID, String rootDirID) {
        accountDao.setRootDirID(accountID,rootDirID);
        return true;
    }


}
