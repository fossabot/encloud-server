package cn.rubitcat.service.impl;

import cn.rubitcat.dao.database.IFileDao;
import cn.rubitcat.dao.mongodb.IDirectoryDao;
import cn.rubitcat.domain.database.File;
import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;
import cn.rubitcat.domain.mongodb.ItemType;
import cn.rubitcat.service.IFileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FileServices implements IFileServices {

    @Autowired
    IDirectoryDao directoryDao;

    @Autowired
    IFileDao fileDao;


    @Override
    public String initRootDir(Integer accountID) {
        Directory rootDirectory = new Directory();
        rootDirectory.setAccessDate(new Date());
        rootDirectory.setModifyDate(new Date());
        rootDirectory.setCreateDate(new Date());
        rootDirectory.setItemCount(0);
        rootDirectory.setDirectoryName("/");
        rootDirectory.setDirectorySize(0);
        Boolean result = directoryDao.addDir(accountID.toString(), rootDirectory);
        if(result == true){
            return rootDirectory.getDirectoryID();
        }
        return null;
    }

    @Override
    public Boolean addDir(Integer accountID, String pDirID, String dirName) {
        //添加目录
        Directory newDir = new Directory();
        newDir.setAccessDate(new Date());
        newDir.setModifyDate(new Date());
        newDir.setCreateDate(new Date());
        newDir.setItemCount(0);
        newDir.setDirectoryName(dirName);
        newDir.setDirectorySize(0);
        Boolean result = directoryDao.addDir(accountID.toString(),newDir);

        //更新目录项
        DirectoryItem dirItem = new DirectoryItem();
        dirItem.setItemType(ItemType.DIR);
        dirItem.setItemID(newDir.getDirectoryID());
        dirItem.setItemName(dirName);
        dirItem.setCreateDate(new Date());
        Boolean result2 = directoryDao.addDirItem(accountID.toString(),pDirID,dirItem);
        return true;
    }

    @Override
    public Boolean deleteDir(Integer accountID, String pDirID, String dirID) {
        Boolean result = directoryDao.deleteDirItem(accountID.toString(), pDirID, dirID);
        Boolean result2 = directoryDao.deleteDir(accountID.toString(), dirID);
        return true;
    }

    @Override
    public Boolean renameDir(Integer accountID, String pDirID, String dirID, String newName) {
        Boolean result = directoryDao.renameDirItem(accountID.toString(), pDirID, dirID, newName);
        Boolean result2 = directoryDao.renameDir(accountID.toString(), dirID, newName);
        return true;
    }


    @Override
    public Directory getDir(Integer accountID, String dirID) {
        Directory dir = directoryDao.getDir(accountID.toString(), dirID);
        return dir;
    }

    @Override
    public Boolean addDirFile(Integer accountID, String pDirID, DirectoryItem item) {
        Boolean result = directoryDao.addDirItem(accountID.toString(), pDirID, item);
        return true;
    }

    @Override
    public Boolean deleteDirFile(Integer accountID, String pDirID, Integer itemID) {
        Boolean result = directoryDao.deleteDirItem(accountID.toString(), pDirID, itemID.toString());
        return true;
    }

    @Override
    public Boolean renameDirFile(Integer accountID, String pDirID, Integer fileID, String newName) {
        Boolean result = directoryDao.renameDirItem(accountID.toString(), pDirID, fileID.toString(), newName);
        return true;
    }

    @Override
    public Boolean addFileRecord(File file) {
        fileDao.addFile(file);
        return true;
    }

    @Override
    public Boolean setFileRecordName(Integer fileID, String fileName) {
        fileDao.setFileNameByID(fileID, fileName);
        return true;
    }

    @Override
    public Boolean deleteFileRecord(Integer fileID) {
        fileDao.deleteFileByID(fileID);
        return true;
    }


}
