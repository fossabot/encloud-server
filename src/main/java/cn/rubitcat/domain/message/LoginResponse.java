package cn.rubitcat.domain.message;

import cn.rubitcat.domain.database.Account;

import java.util.Date;

public class LoginResponse extends ResponseBase{
    String      token;
    Account     account;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", account=" + account +
                ", statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public Account getAccount() {
        return account;
    }
}
