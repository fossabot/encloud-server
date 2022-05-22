package service_test;

import cn.rubitcat.domain.mongodb.Directory;
import cn.rubitcat.domain.mongodb.DirectoryItem;
import cn.rubitcat.domain.mongodb.ItemType;
import cn.rubitcat.service.IFileServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springcontext.xml")
public class FileServicesTest {
    @Autowired
    IFileServices fileServices;
    @Test
    void testRootDirInit(){
        String s = fileServices.initRootDir(100);
        System.out.println(s);
    }

    @Test
    void test2(){
        fileServices.addDir(144,"6268b9963d5d20097ff9c059","唯我独尊");
    }

    @Test
    void test3(){
        fileServices.deleteDir(100,"626633ecbfb21b37032b4284","6266361c8d8cbd1063e57032");
    }

    @Test
    void test4(){
        DirectoryItem item = new DirectoryItem();
        item.setItemID(Integer.valueOf(11).toString());
        item.setItemName("期末论文要求");
        item.setItemSize(1000);
        item.setItemKey("filekey");
        item.setCreateDate(new Date());
        item.setItemType(ItemType.FILE);
        fileServices.addDirFile(144,"6268bfbf9039d13b6bea5f43",item);

    }

    @Test
    void test5(){
        fileServices.deleteDirFile(100,"626633ecbfb21b37032b4284",11);
    }

    @Test
    void test6(){
        fileServices.renameDirFile(100,"626633ecbfb21b37032b4284",11,"我的大学");
    }

    @Test
    void test7(){
        Directory dir = fileServices.getDir(130, "6266a725ae1194238429a503" );
        System.out.println(dir);
    }

    @Test
    void test8(){
        Boolean java = fileServices.setFileRecordName(1, "java");

    }

    @Test
    void test9(){
        fileServices.deleteFileRecord(1);


    }

}
