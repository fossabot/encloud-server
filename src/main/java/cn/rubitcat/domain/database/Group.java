package cn.rubitcat.domain.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"handler"})
public class Group implements Serializable {
    //字段
    Integer         groupID;
    String          groupName;
    Date            createDate;

    //关系
    List<Account>   accounts;

    @Override
    public String toString() {
        return "Group{" +
                "groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                ", createDate=" + createDate +
                ", accounts=" + accounts +
                '}';
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
