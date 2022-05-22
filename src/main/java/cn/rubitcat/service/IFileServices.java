package cn.rubitcat.service;

import cn.rubitcat.domain.database.File;
import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;

public interface IFileServices {

    public String initRootDir(Integer accountID);
    public Boolean addDir(Integer accountID, String pDirID, String dirName);
    public Boolean deleteDir(Integer accountID, String pDirID, String dirID);
    public Boolean renameDir(Integer accountID, String pDirID, String dirID, String newName);
    public Directory getDir(Integer accountID, String dirID);

    public Boolean addDirFile(Integer accountID, String pDirID, DirectoryItem item);
    public Boolean deleteDirFile(Integer accountID, String pDirID, Integer itemID);
    public Boolean renameDirFile(Integer accountID, String pDirID, Integer fileID, String newName);

    public Boolean addFileRecord(File file);
    public Boolean setFileRecordName(Integer fileID, String fileName);
    public Boolean deleteFileRecord(Integer fileID);

}
