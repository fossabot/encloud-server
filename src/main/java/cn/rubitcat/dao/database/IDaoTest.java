package cn.rubitcat.dao.database;

import cn.rubitcat.domain.BeanTest;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface IDaoTest {
    @Select("SELECT * FROM TEST.PERSON")
    List<BeanTest> findAllPerson();

    @Update("INSERT INTO TEST.PERSON VALUES('www', 50)")
    void addPerson();
}
