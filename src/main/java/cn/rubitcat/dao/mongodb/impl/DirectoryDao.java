package cn.rubitcat.dao.mongodb.impl;

import cn.rubitcat.dao.mongodb.IDirectoryDao;
import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;
import cn.rubitcat.domain.mongodb.ItemType;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

@Repository
public class DirectoryDao implements IDirectoryDao {

    @Resource(name = "directoryMongoTemplate")
    MongoTemplate dirMongoTemplate;

    @Override
    public Boolean addDir(String collectionName, Directory dir){
        Directory insert = dirMongoTemplate.insert(dir, collectionName);
        return true;
    }

    @Override
    public Boolean renameDir(String collectionName, String dirID, String newName) {
        Query query = new Query(Criteria.where("_id").is(dirID));
        Update update = new Update().set("directoryName", newName);
        dirMongoTemplate.updateFirst(query, update, Directory.class, collectionName);
        return true;
    }

    @Override
    public Boolean deleteDir(String collectionName, String dirId) {
        Query query = new Query(Criteria.where("_id").is(dirId));
        dirMongoTemplate.remove(query,collectionName);
        return true;
    }

    @Override
    public Directory getDir(String collectionName, String dirID) {
        Directory dir = dirMongoTemplate.findById(dirID, Directory.class,collectionName);
        return dir;
    }

    @Override
    public Boolean addDirItem(String collectionName, String pDirID, DirectoryItem item) {
        Query query = new Query(Criteria.where("_id").is(pDirID));
        Update update = new Update().push("items",item);
        UpdateResult result = dirMongoTemplate.updateFirst(query, update, Directory.class, collectionName);
        return true;
    }

    @Override
    public Boolean renameDirItem(String collectionName, String pDirID, String itemID, String newName) {
        Query query = new Query(
                Criteria.where("_id").is(pDirID)
                .and("items").elemMatch(Criteria.where("itemID").is(itemID)));
        Update update = new Update().set("items.$.itemName", newName);
        UpdateResult result = dirMongoTemplate.updateFirst(query, update, Directory.class, collectionName);
        return true;
    }

    @Override
    public Boolean deleteDirItem(String collectionName, String pDirID, String itemID) {
        Query query = new Query(Criteria.where("_id").is(pDirID));
        Update update = new Update().pull("items", new BasicDBObject("itemID",itemID));
        UpdateResult result = dirMongoTemplate.updateFirst(query, update, Directory.class, collectionName);
        return null;
    }

}
