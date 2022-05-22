package cn.rubitcat.domain.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"handler"})
public class Share implements Serializable {
    //字段
    Integer     shareID;
    Integer     accountID;
    Integer     destAccountID;
    String      shareKey;
    String      fileKeys;
    Date        shareDate;
    Date        closeDate;

    //关系
    Account     account;
    List<File>  files;

    @Override
    public String toString() {
        return "Share{" +
                "shareID=" + shareID +
                ", accountID=" + accountID +
                ", destAccountID=" + destAccountID +
                ", shareKey='" + shareKey + '\'' +
                ", fileKeys='" + fileKeys + '\'' +
                ", shareDate=" + shareDate +
                ", closeDate=" + closeDate +
                ", account=" + account +
                ", files=" + files +
                '}';
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setShareKey(String shareKey) {
        this.shareKey = shareKey;
    }

    public void setFileKeys(String fileKeys) {
        this.fileKeys = fileKeys;
    }

    public String getShareKey() {
        return shareKey;
    }

    public String getFileKeys() {
        return fileKeys;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getShareID() {
        return shareID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Integer getDestAccountID() {
        return destAccountID;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setShareID(Integer shareID) {
        this.shareID = shareID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public void setDestAccountID(Integer destAccountID) {
        this.destAccountID = destAccountID;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
