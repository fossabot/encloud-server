package cn.rubitcat.domain.message;
public class LoginRequest {
    String accountName;
    String accountPassword;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                '}';
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }
}
