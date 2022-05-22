package cn.rubitcat.domain.mongodb;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Directory {
    @Id
    String                  directoryID;
    String                  directoryName;
    Integer                 directorySize;
    Integer                 itemCount;
    Date                    createDate;
    Date                    accessDate;
    Date                    modifyDate;
    List<DirectoryItem>     items;

    @Override
    public String toString() {
        return "Directory{" +
                "directoryID='" + directoryID + '\'' +
                ", directoryName='" + directoryName + '\'' +
                ", directorySize=" + directorySize +
                ", itemCount=" + itemCount +
                ", createDate=" + createDate +
                ", accessDate=" + accessDate +
                ", modifyDate=" + modifyDate +
                ", items=" + items +
                '}';
    }

    public void setDirectoryID(String directoryID) {
        this.directoryID = directoryID;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public void setDirectorySize(Integer directorySize) {
        this.directorySize = directorySize;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setItems(List<DirectoryItem> items) {
        this.items = items;
    }

    public String getDirectoryID() {
        return directoryID;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public Integer getDirectorySize() {
        return directorySize;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public List<DirectoryItem> getItems() {
        return items;
    }
}
