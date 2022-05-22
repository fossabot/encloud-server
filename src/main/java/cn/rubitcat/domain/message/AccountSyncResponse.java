package cn.rubitcat.domain.message;

import cn.rubitcat.domain.database.Account;

import java.util.Date;

public class AccountSyncResponse extends  ResponseBase{

    Account     account;

    @Override
    public String toString() {
        return "AccountSyncResponse{" +
                "account=" + account +
                ", statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
