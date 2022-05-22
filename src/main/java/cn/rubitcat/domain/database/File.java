package cn.rubitcat.domain.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"handler"})
public class File implements Serializable {
    //属性
    Integer     fileID;
    Integer     accountID;
    String      fileName;
    String      hash;
    String      fileKey;
    Integer     fileSize;
    Date        uploadDate;
    Boolean     isFromShare;

    //关系
    Account     account;
    List<Share> shares;

    @Override
    public String toString() {
        return "File{" +
                "fileID=" + fileID +
                ", accountID=" + accountID +
                ", fileName='" + fileName + '\'' +
                ", hash='" + hash + '\'' +
                ", fileKey='" + fileKey + '\'' +
                ", fileSize=" + fileSize +
                ", uploadDate=" + uploadDate +
                ", isFromShare=" + isFromShare +
                ", account=" + account +
                ", shares=" + shares +
                '}';
    }

    public void setFromShare(Boolean fromShare) {
        isFromShare = fromShare;
    }

    public Boolean getFromShare() {
        return isFromShare;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public Account getAccount() {
        return account;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }


    public Integer getFileID() {
        return fileID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }

    public String getFileKey() {
        return fileKey;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

}
