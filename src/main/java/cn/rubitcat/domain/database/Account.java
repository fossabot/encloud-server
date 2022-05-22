package cn.rubitcat.domain.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"handler"})
public class Account implements Serializable {
    //字段
    Integer accountID;
    String  password;
    String  accountName;
    String  imgPath;
    Integer userID;
    Date    createDate;
    String  rootDirID;
    String  mainKey;
    String  publicKey;
    String  privateKey;


    //关系
    List<Message>   messages;
    List<Account>   accounts;
    List<Group>     groups;
    List<Share>     shares;
    List<File>      files;
    User    user;

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", password='" + password + '\'' +
                ", accountName='" + accountName + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", userID=" + userID +
                ", createDate=" + createDate +
                ", rootDirID='" + rootDirID + '\'' +
                ", mainKey='" + mainKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", messages=" + messages +
                ", accounts=" + accounts +
                ", groups=" + groups +
                ", shares=" + shares +
                ", files=" + files +
                ", user=" + user +
                '}';
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setRootDirID(String rootDirID) {
        this.rootDirID = rootDirID;
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

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Integer getUserID() {
        return userID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getRootDirID() {
        return rootDirID;
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

    public List<Message> getMessages() {
        return messages;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Share> getShares() {
        return shares;
    }

    public List<File> getFiles() {
        return files;
    }

    public User getUser() {
        return user;
    }
}
