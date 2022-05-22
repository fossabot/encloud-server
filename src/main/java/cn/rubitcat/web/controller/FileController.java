package cn.rubitcat.web.controller;

import cn.rubitcat.domain.BeanTest;
import cn.rubitcat.domain.message.*;
import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;
import cn.rubitcat.domain.mongodb.ItemType;
import cn.rubitcat.service.IFileServices;
import cn.rubitcat.service.ISessionServices;
import cn.rubitcat.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    IFileServices fileServices;

    @Autowired
    ISessionServices sessionServices;

    @RequestMapping("/directory")
    public @ResponseBody DirectoryResponse directorySync(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody DirectoryRequest  directoryRequest)
    {
        DirectoryResponse directoryResponse = new DirectoryResponse();
        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, directoryRequest.getToken());
        if (result == false) {
            directoryResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            directoryResponse.setDate(new Date());
            directoryResponse.setServerMessage("TOKEN VERIFFY ERROR");
            return directoryResponse;
        }

        //获取目录
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        Directory dir = fileServices.getDir(accountID, directoryRequest.getDirID());
        if(dir == null){
            directoryResponse.setStatusCode(StatusCode.DIRECTORY_SYNC_SUCCESS);
            directoryResponse.setDate(new Date());
            directoryResponse.setServerMessage("DIRECTORY_SYNC_SUCCESS");
            directoryResponse.setDirectory(dir);
        }
        directoryResponse.setStatusCode(StatusCode.DIRECTORY_SYNC_SUCCESS);
        directoryResponse.setDate(new Date());
        directoryResponse.setServerMessage("DIRECTORY_SYNC_SUCCESS");
        directoryResponse.setDirectory(dir);
        return directoryResponse;
    }


    @RequestMapping("/uploadFile")
    @ResponseBody
    UploadResponse uploadFileProcess(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestPart("uploadRequest") UploadRequest uploadRequest,
            @RequestPart("uploadFile") MultipartFile uploadFile) throws IOException
    {
        System.out.println(uploadRequest);
        //初始化
        UploadResponse uploadResponse = new UploadResponse();
        System.out.println(sessionID);

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, uploadRequest.getToken());
        if(result == false){
            uploadResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            uploadResponse.setDate(new Date());
            uploadResponse.setServerMessage("TOKEN_VERIFY_ERROR");
        }

        //添加文件记录
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        cn.rubitcat.domain.database.File file = new cn.rubitcat.domain.database.File();
        file.setAccountID(accountID);
        file.setFileName(uploadRequest.getFileName());
        file.setFileKey(uploadRequest.getFileKey());
        file.setHash(uploadRequest.getHash());
        file.setFileSize(uploadRequest.getSize());
        file.setUploadDate(new Date());
        file.setFromShare(false);
        System.out.println(file);
        fileServices.addFileRecord(file);

        //添加目录记录
        DirectoryItem directoryItem = new DirectoryItem();
        directoryItem.setItemSize(uploadRequest.getSize());
        directoryItem.setItemType(ItemType.FILE);
        directoryItem.setItemID(String.valueOf(file.getFileID()));
        directoryItem.setItemName(uploadRequest.getFileName());
        directoryItem.setCreateDate(new Date());
        directoryItem.setItemHash(uploadRequest.getHash());
        directoryItem.setItemKey(uploadRequest.getFileKey());
        System.out.println(directoryItem);
        fileServices.addDirFile(accountID,uploadRequest.getDirectoryID(),directoryItem);

        //存储文件
        System.out.println(file);
        int L1Number = file.getFileID()/(25*25);
        int L2Number = file.getFileID()%(25*25)/25;
        int L3Number = file.getFileID()%(25*25)%25;
        File pDir = new File("/mnt/cephfs/encloud/data/" + L1Number + "/" + L2Number);
        if(!pDir.exists()) pDir.mkdirs();
        uploadFile.transferTo(new File("/mnt/cephfs/encloud/data/" + L1Number + "/" + L2Number+"/"+L3Number));

        uploadResponse.setStatusCode(StatusCode.UPLOAD_FILE_SUCCESS);
        uploadResponse.setDate(new Date());
        uploadResponse.setServerMessage("UPLOAD_FILE_SUCCESS");
        return uploadResponse;
    }


    @RequestMapping("/downloadFile")
    @ResponseBody
    void downloadFileProcess(
            HttpServletResponse response,
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody DownloadRequest downloadRequest) throws IOException
    {
        System.out.println(downloadRequest);
        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, downloadRequest.getToken());
        if (result == false) {
            DownloadResponse downloadResponse = new DownloadResponse();
            downloadResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            downloadResponse.setDate(new Date());
            downloadResponse.setServerMessage("TOKEN_VERIFY_ERROR");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter() ,downloadResponse);
            response.setHeader("Content-Type","application/json");
        }
        response.setHeader("Content-Type","application/octet-stream");
        //获取文件
        Integer fileID = Integer.valueOf(downloadRequest.getItemID());
        int L1Number = fileID/(25*25);
        int L2Number = fileID%(25*25)/25;
        int L3Number = fileID%(25*25)%25;
        FileInputStream fis = new FileInputStream("/mnt/cephfs/encloud/data/" + L1Number + "/" + L2Number + "/" + L3Number);
        ServletOutputStream sos = response.getOutputStream();
        int len = 0;
        byte[] buff = new byte[1024*8];
        while ((len = fis.read(buff)) != -1){
            sos.write(buff, 0, len);
        }
    }


    @RequestMapping("/rename")
    @ResponseBody
    RenameResponse renameProcess(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody RenameRequest renameRequest)
    {
        System.out.println(renameRequest);
        //初始化
        RenameResponse renameResponse = new RenameResponse();

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, renameRequest.getToken());
        if(result == false){
            renameResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            renameResponse.setDate(new Date());
            renameResponse.setServerMessage("TOKEN_VERIFY_ERROR");
            return renameResponse;
        }

        //修改名称
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        Boolean renameResult = false;
        if(renameRequest.getType() == ItemType.DIR){
            renameResult = fileServices.renameDir(accountID,renameRequest.getDirectoryID(),
                    renameRequest.getItemID(),renameRequest.getNewName());
        }

        if(renameRequest.getType() == ItemType.FILE){
            renameResult = fileServices.renameDirFile(accountID,renameRequest.getDirectoryID(),
                    Integer.valueOf(renameRequest.getItemID()),renameRequest.getNewName());
            fileServices.setFileRecordName(Integer.valueOf(renameRequest.getItemID()),renameRequest.getNewName());
        }
        if(renameResult == false){
            renameResponse.setStatusCode(StatusCode.RENAME_FALSE);
            renameResponse.setDate(new Date());
            renameResponse.setServerMessage("RENAME_FALSE");
            return renameResponse;
        }

        renameResponse.setStatusCode(StatusCode.RENAME_SUCCESS);
        renameResponse.setDate(new Date());
        renameResponse.setServerMessage("RENAME_SUCCESS");
        return renameResponse;
    }

    @RequestMapping("/mkdir")
    @ResponseBody
    MkdirResponse mkdirProcess(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody MkdirRequest mkdirRequest)
    {
        System.out.println(mkdirRequest);

        //初始化
        MkdirResponse response = new MkdirResponse();

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, mkdirRequest.getToken());
        if(result == false){
            response.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            response.setDate(new Date());
            response.setServerMessage("TOKEN_VERIFY_ERROR");
            return response;
        }

        //创建目录
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        Boolean result2 = fileServices.addDir(accountID, mkdirRequest.getDirectoryID(), mkdirRequest.getDirectoryName());
        if(result2 == false){
            response.setStatusCode(StatusCode.MKDIR_FALSE);
            response.setDate(new Date());
            response.setServerMessage("MKDIR_FALSE");
            return response;
        }

        response.setStatusCode(StatusCode.MKDIR_SUCCESS);
        response.setDate(new Date());
        response.setServerMessage("MKDIR_SUCCESS");
        return response;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DeleteResponse deleteProcess(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody DeleteRequest deleteRequest)
    {
        System.out.println(deleteRequest);
        //初始化
        DeleteResponse deleteResponse  = new DeleteResponse();

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, deleteRequest.getToken());
        if(result == false){
            deleteResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            deleteResponse.setDate(new Date());
            deleteResponse.setServerMessage("TOKEN_VERIFY_ERROR");
            return deleteResponse;
        }

        //删除文件
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        if(deleteRequest.getType() == ItemType.DIR){
            Directory directory = fileServices.getDir(accountID, deleteRequest.getItemID());
            deleteDirectory(accountID,directory);
            fileServices.deleteDir(accountID,deleteRequest.getDirectoryID(),
                    deleteRequest.getItemID());
        }
        if(deleteRequest.getType() == ItemType.FILE){
            fileServices.deleteFileRecord(Integer.valueOf(deleteRequest.getItemID()));
            fileServices.deleteDirFile(accountID,deleteRequest.getDirectoryID(),
                    Integer.valueOf(deleteRequest.getItemID()));
        }

        deleteResponse.setStatusCode(StatusCode.DELETE_SUCCESS);
        deleteResponse.setDate(new Date());
        deleteResponse.setServerMessage("DELETE_SUCCESS");
        return deleteResponse;
    }


    private void deleteDirectory(Integer accountID, Directory directory){
        List<DirectoryItem> items = directory.getItems();
        if(items != null){
            for (DirectoryItem item : items) {
                if(item.getItemType() == ItemType.DIR){
                    Directory subDir = fileServices.getDir(accountID, item.getItemID());
                    deleteDirectory(accountID, subDir);
                    fileServices.deleteDir(accountID,directory.getDirectoryID(),item.getItemID());
                }
                if(item.getItemType() == ItemType.FILE){
                    fileServices.deleteFileRecord(Integer.valueOf(item.getItemID()));
                    fileServices.deleteDirFile(accountID,directory.getDirectoryID(),
                            Integer.valueOf(item.getItemID()));
                }
            }
        }

    }

}
