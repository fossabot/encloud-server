package cn.rubitcat.domain.message;

public class RegRequest {
    String userName;
    String phone;
    String email;
    String accountName;
    String accountPassword;
    String checkCode;
    String mainKey;
    String publicKey;
    String privateKey;

    @Override
    public String toString() {
        return "RegMessage{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", mainKey='" + mainKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMainKey(String mainKey) {
        this.mainKey = mainKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getMainKey() {
        return mainKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }


}
