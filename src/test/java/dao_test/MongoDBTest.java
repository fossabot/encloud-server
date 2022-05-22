package dao_test;

import cn.rubitcat.dao.mongodb.IDirectoryDao;
import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springcontext.xml")
public class MongoDBTest {

    public String conStr = "mongodb://192.168.3.240:27017/?maxPoolSize=20&w=majority";

    @Autowired
    MongoClient mongoClient;

    @Autowired
    IDirectoryDao directoryDao;

    @Autowired
    MongoTemplate mongoTemplate;

    //测试MongoDB单独运行
    @Test
    void test1(){
        try(MongoClient mongoClient = MongoClients.create(conStr)){
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> student = database.getCollection("student");
            Bson filter = Filters.eq("_id", "myid");
            FindIterable<Document> documents = student.find(filter);
            documents.forEach(doc -> System.out.println(doc.toJson()));
            System.out.println(documents.cursor().available());

//            student.find(Filters.eq("")).projection(Projections.include("")).limit(4).forEach();

//            student.insertOne(new Document())
//            student.insertMany()

//            student.updateOne(Filters.eq(""), Updates.inc("",20))
//            student.updateMany()

//            student.deleteOne(Filters.eq(""))
//            student.deleteMany()
        }
    }

    //测试Spring集成MongoDB
    @Test
    void testMongo(){
        MongoDatabase test = mongoClient.getDatabase("test");
        MongoCollection<Document> student = test.getCollection("student");
        FindIterable<Document> documents = student.find();
        documents.forEach(doc -> System.out.println(doc));
    }

    //测试spring mongoTemplate
    @Test
    void testMongoTeplate(){
        Directory directory = new Directory();
        directory.setModifyDate(new Date());
        directory.setCreateDate(new Date());
        directory.setModifyDate(new Date());
        directory.setItemCount(10);
        directory.setItems(null);
        DirectoryItem directoryItem1 = new DirectoryItem();
        DirectoryItem directoryItem2 = new DirectoryItem();
        directoryItem1.setCreateDate(new Date());
        directoryItem1.setItemKey("fileKey1");
        directoryItem1.setItemHash("hash1");
        directoryItem1.setItemSize(10);
        directoryItem2.setCreateDate(new Date());
        directoryItem2.setItemKey("fileKey1");
        directoryItem2.setItemHash("hash1");
        directoryItem2.setItemSize(10);
        ArrayList<DirectoryItem> directoryItems = new ArrayList<>();
        directoryItems.add(directoryItem1);
        directoryItems.add(directoryItem2);
        directory.setItems(directoryItems);
        directory.setDirectoryName("java");
        directory.setDirectorySize(100);
        mongoTemplate.insert(directory,"vick");
        System.out.println(directory);
        //流式风格api
//        mongoTemplate.insert(Directory.class).one(directory)
//        mongoTemplate.update(Directory.class).apply()
//        mongoTemplate.remove(Directory.class).matching(new Query()).all()

        //mongodb传统风格api
//        mongoTemplate.find(new Query(),Directory.class,"directory")
//        mongoTemplate.findAll()
//        mongoTemplate.findById()
//        mongoTemplate.findAndModify()
//        mongoTemplate.findAndRemove()

//        mongoTemplate.insert(directory, "directory")
//        mongoTemplate.insertAll()
//        mongoTemplate.updateFirst(new Query(),new Update(),Directory.class)
//        mongoTemplate.updateMulti()
//        mongoTemplate.upsert()

//        mongoTemplate.remove(new Query(),Directory.class)
    }


    @Test
    void test3(){
        Directory byId = mongoTemplate.findById("626544aeba6a00348cd5ce72", Directory.class, "vick");
        System.out.println(byId);
        System.out.println(byId.getItems());
    }

    @Test
    void test4(){
        Directory directory = new Directory();
        directory.setDirectoryName("你好");
        directory.setItemCount(10);
        directory.setItems(null);
        directoryDao.addDir("daotest",directory);
    }

    @Test
    void test5(){
        DirectoryItem directoryItem1 = new DirectoryItem();
        directoryItem1.setItemID("8888");
        directoryItem1.setItemName("世界");
        directoryItem1.setItemKey("fileKey1");
        directoryItem1.setItemHash("hash1");
        directoryItem1.setItemSize(10);
        directoryDao.addDirItem("daotest","62662e2d632b4c7f79b1035f",directoryItem1);
    }

    @Test
    void test6(){
        directoryDao.renameDirItem("daotest","62662e2d632b4c7f79b1035f","8888","我的世界");

    }

    @Test
    void tes7(){
        directoryDao.deleteDirItem("daotest","62662e2d632b4c7f79b1035f", "8888");

    }

}
