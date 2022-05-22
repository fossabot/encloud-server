package cn.rubitcat.web.controller;

import cn.rubitcat.domain.BeanTest;
import cn.rubitcat.service.IServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 本模块用于测试SpringMVC框架的相关功能是否正常.
 */
@Controller
@RequestMapping("/test")
public class ControllerTest {
    @Autowired
    IServiceTest serviceTest = null;

    @RequestMapping("/headertest")
    void headertest(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode("你好.html","utf8"));

        PrintWriter writer = response.getWriter();
        writer.write("你好");
    }

    //绑定基础数据类型或字符串，自动根据表单数据的键为字符串注入数据. 当无法进行转换时需要定义转换器并在springmvc.xml中配置.
    void void_string(String test1, Integer test2){
        System.out.println("绑定基础数据类型或字符串");
        System.out.println(test1);
        System.out.println(test2);
    }

    //绑定Servlet原始API, 当参数包含HttpServletRequest、HttpServletResponse这两个原始API时会框架会自动注入.
    @RequestMapping("/")
    void servletApiTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "原生Servlet API测试 !!!!!";
        System.out.println(message);
        response.setCharacterEncoding("utf8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    //绑定MultipartFile，若已经配置文件解析器，当上传文件时会自动将文件封装成MultipartFile对象
    @RequestMapping("/uploadTest")
    void upload(MultipartFile[] file) throws IOException {
        String message = "文件上传测试 !!!!!!";
        System.out.println(message);
        System.out.println(file);
        for (MultipartFile file1 : file) {
            String filename = file1.getOriginalFilename();
            System.out.println("文件名是"+filename);
            file1.transferTo(new File("/home/vick/uploadtest/"+filename));
        }
        return ;
    }

    //返回值是字符串“forward:URL”，自动进行服务器资源转发
    @RequestMapping("/forwardTest")
    String forward(HttpServletRequest request){
        System.out.println("转发测试 !!!!!!!");
        request.setAttribute("java","c++");
        return  "forward:/test/redirectTest";
    }

    //返回值是字符串"redirect:URL"，自动对客户端进行重定向响应
    @RequestMapping("/redirectTest")
    String redirect(HttpServletRequest request){
        System.out.println("redirect execute");
        System.out.println(request.getAttribute("java"));
        return "redirect:https://blog.rubitcat.cn";
    }

    //返回值是普通字符串, 自动通过视图解析器解析JSP进行响应
    @RequestMapping("/aaa")
    String  viewPhraser(){
        return "index";
    }

    //返回值是JavaBean且已经配置Jackson依赖时会自动将Bean转换为json响应给客户端
    @RequestMapping("jsonEchoTest")
    public @ResponseBody BeanTest jsonEcho(@RequestBody BeanTest beanTest){
        System.out.println(beanTest);
        return beanTest;
    }

    @RequestMapping("/commStringTest")
    @ResponseBody String commString(){

        return "hello world, just test common string!!!";
    }


    /**
     * 测试SSM整合
     */
    @RequestMapping("/ssmtest")
    void void_void_test(){
        serviceTest.testPrintPerson();
        System.out.println("我运行了");
    }

}
