package cn.rubitcat.dao.mongodb;

import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;
import cn.rubitcat.domain.mongodb.ItemType;

public interface IDirectoryDao {

    public Boolean addDir(String collectionName, Directory dir);
    public Boolean renameDir(String collectionName, String dirID, String newName);
    public Boolean deleteDir(String collectionName, String dirId);
    public Directory getDir(String collectionName, String dirID);

    public Boolean addDirItem(String collectionName, String pDirID, DirectoryItem item);
    public Boolean renameDirItem(String collectionName, String pDirID, String itemID, String newName);
    public Boolean deleteDirItem(String collectionName, String pDirID, String itemID);

}
