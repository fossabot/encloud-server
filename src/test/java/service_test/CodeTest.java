package service_test;

import cn.rubitcat.domain.BeanTest;
import cn.rubitcat.domain.message.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 本测试类测试SpringFramework
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springcontext.xml")
public class CodeTest {

    @Test
    void test(){
        String s1 = UUID.randomUUID().toString();
        System.out.println(s1);
        String[] split = s1.split("-");
        for( String s : split){
            System.out.println(s);
        }
    }

    @Test
    void test1() throws JsonProcessingException {
        BeanTest beanTest = new BeanTest();
        beanTest.setName("陈宏峰");
        beanTest.setAge(24);

        BeanTest beanTest2 = new BeanTest();
        beanTest2.setName("张三");
        beanTest2.setAge(22);

        ArrayList<BeanTest> beanTests = new ArrayList<>();
        beanTests.add(beanTest);
        beanTests.add(beanTest2);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(beanTests);

        System.out.println(s);
    }

    @Test
    void jsonTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(StatusCode.CHECK_CODE_ERROR);
        System.out.println(s);
    }

    @Test
    void testUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
    }

    @Test
    void testDate(){
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    void testPattern(){
        String  str = "BIDUPSID=B964C134CC2E0AE9551561EB432B042B; PSTM=1646011449; ISSW=1; ISSW=1; BD_UPN=123353; __yjs_duid=1_554d29478ae99af678d86550f74038771646013454954; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDUSS=WNxeTZ6dDdCVHFad1QzR2YzRkduWW5UVGs1dn5lbWR-NXFjYWFpeVc4bDlsM1JpSVFBQUFBJCQAAAAAAAAAAAEAAAAXI5w-vtu74bj5vt0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH0KTWJ9Ck1iS; BDUSS_BFESS=WNxeTZ6dDdCVHFad1QzR2YzRkduWW5UVGs1dn5lbWR-NXFjYWFpeVc4bDlsM1JpSVFBQUFBJCQAAAAAAAAAAAEAAAAXI5w-vtu74bj5vt0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH0KTWJ9Ck1iS; BAIDUID=B964C134CC2E0AE954E58150BB7BA2F2:SL=0:NR=10:FG=1; BDSFRCVID_BFESS=_kkOJexroG0RCvjDSD5rcatYifCfZvRTDYLEOwXPsp3LGJLVgiUREG0PtqGW55CbCHVaogKKWgOTH6KF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF_BFESS=tbuH_I-2JKL3H48k-4QEbbQH-UnLqh3g0mOZ04n-ah02VlOCMxOry-kQb-jCBJcO0KczLfom3UTdsq76Wh35K5tTQP6rLtb0QGr4KKJxbPjnSIKGLP5TyhK_hUJiBhbMBan72JvIXKohJh7FM4tW3J0ZyxomtfQxtNRJ0DnjtpChbC84D68Mj5bBeU5eetjK2CntsJOOaCvf8qOOy4oWK441Db5m-fTRaK7MaROqbpQHhlvoD-Jc3M04XhO9-hvT-54e2p3FBUQJjf5-Qft20b0kWb3xWn3a02bp3R7jWhvRep72y5jvQlRX5q79atTMfNTJ-qcH0KQpsIJM5-DWbT8IjHCeJjDJJnPf_Dvt-5rDHJTg5DTjhPrM5JjlWMT-MTryKKJ40-Qksb7nbUTj-J0z-tcmKqTqLHnRhlR2B-3iV-OxDUvnyxAZbUrPQfQxtNRJQl36JC5THlOS34QobUPU3UJ9LUkqW2cdot5yBbc8eIna5hjkbfJBQttjQn3hfIkj2CKLtK_KMK-mjTD3-RJH-xQ0KnLXKKOLVh7o3q7ketn4hUt20xtBy4Og0TQeKK6JhRTHWhk2ep72Qhrd5M4WWb3ebTJr32Qr-J3VQxopsIJM5Moi0M-T5M5mBp5KaKviaM3jBMb1MlvDBT5h2M4qMxtOLR3pWDTm_q5TtUJMeCnTDMFhe6JXDG8JtT-qfKresJoq2RbhKROvhjRrMPPgyxoObtRxtNrmLJcgLh-hqROp5hrEbxtEWljNLU3kBgT9LMnx--t58h3_XhjJMqLwQttjQn3D3akt_lOt0hn4HJ7TyURRbU47yaji0q4Hb6b9BJcjfU5MSlcNLTjpQT8r5MDOK5OuJRQ2QJ8BtKIKbDbP; H_PS_PSSID=36309_36166_34584_36122_36257_26350_36311_36061; BD_HOME=1; delPer=1; BAIDUID_BFESS=0C0B235D1100CF8A462AAAB49085065E:FG=1; PSINO=6; BD_CK_SAM=1; sug=3; sugstore=0; ORIGIN=2; bdime=0; H_PS_645EC=73b4dNkBihJdOmlI07BDDYWGE6xrWU7+XCDYgPo42AZebQFUeWUvfXA7FDI; BA_HECTOR=ahag2k2k8l200480201h5uv040r; BDSVRTM=0";
        String  baidusid = null;
        Matcher matcher = Pattern.compile(".*BIDUPSID=([a-zA-Z0-9]+).*").matcher(str);
        if(matcher.find()){
            baidusid = matcher.group(1);
        }
        System.out.println(baidusid);
    }

    @Test
    void testBean(){
        BeanTest beanTest = new BeanTest();
        beanTest.setAge(11);
        beanTest.setName("张三");

    }
    @Test
    void test6(){
        System.out.println("/home/vick/uploadtest/" +1 + "/" +2);
        File file = new File("/home/vick/uploadtest/" + 1 + "/" + 2);
        System.out.println(file.mkdirs());
    }

    @Test
    void test7(){

    }
}
