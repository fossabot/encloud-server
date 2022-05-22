package cn.rubitcat.service.impl;

import cn.rubitcat.dao.database.IDaoTest;
import cn.rubitcat.domain.BeanTest;
import cn.rubitcat.service.IServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceTest")
public class ServiceTest implements IServiceTest {
    @Autowired
    IDaoTest IDaoTest;

    public void testPrintPerson(){
        List<BeanTest> allObject = IDaoTest.findAllPerson();
        System.out.println(allObject);
    }

    public void testAddPerson(){
        IDaoTest.addPerson();
        List<BeanTest> allObject = IDaoTest.findAllPerson();
        System.out.println(allObject);
    }
    public void testTx(){
        IDaoTest.addPerson();
        int  i = 1/0;
        List<BeanTest> allObject = IDaoTest.findAllPerson();
        System.out.println(allObject);
    }
}
