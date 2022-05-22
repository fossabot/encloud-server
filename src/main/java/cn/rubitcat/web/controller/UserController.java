package cn.rubitcat.web.controller;

import cn.rubitcat.dao.database.IMessageDao;
import cn.rubitcat.domain.database.Account;
import cn.rubitcat.domain.database.Message;
import cn.rubitcat.domain.database.User;
import cn.rubitcat.domain.message.*;
import cn.rubitcat.service.*;
import cn.rubitcat.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static cn.rubitcat.utils.CommonUtils.getSessionID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserServices       userServices;
    @Autowired
    IAccountServices    accountServices;
    @Autowired
    ISessionServices    sessionServices;

    @Autowired
    IFileServices       fileServices;

    @Autowired
    IMessageServices    messageServices;

    //注册码接口
    @RequestMapping("/checkCode")
    public void checkCode(
            @CookieValue(name = "SESSIONID",required = false) String sessionID,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {
        //设置session
        if(sessionID == null){
            sessionID = sessionServices.initSession();
            Cookie sessionidCookie = new Cookie("SESSIONID", sessionID);
            sessionidCookie.setPath("/");
            response.addCookie(sessionidCookie);
        }

        //生成验证码
        Random r = new Random();
        String checkCodeSeed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder checkCodeBuilder =new StringBuilder();
        for (int i =0; i< 4;++i){
            char ch = checkCodeSeed.charAt(r.nextInt(checkCodeSeed.length()));
            checkCodeBuilder.append(ch);
        }
        String checkCode = checkCodeBuilder.toString();
        sessionServices.setSessionAttribute(sessionID,"checkCode", checkCode);

        //生成图片
        final int width = 100 ;
        final int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics pen = image.getGraphics();
        pen.setFont(new Font("Source Code Pro",30,18));
        pen.setColor(Color.PINK);
        pen.fillRoundRect(0,0,width,height,14,14);
        pen.setColor(Color.BLUE);
        pen.drawRect(0,0,width-1,height-1);
        for(int i =0; i< 4; ++i){
            pen.drawString(checkCode.charAt(i)+"", 10+i*25, 20);
        }
        pen.setColor(Color.gray);
        for (int i = 0 ;i < 10 ; ++i){
            pen.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
        ImageIO.write(image, "jpg",response.getOutputStream());
    }


    //注册接口
    @RequestMapping("/registration")
    @ResponseBody
    public RegResponse  registration(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody RegRequest regRequest,
            HttpServletRequest request)
    {
        //初始化
        RegResponse regResponse = new RegResponse();

        //验证验证码
        String checkCode = sessionServices.getSessionAttribute(sessionID, "checkCode");
        if(!checkCode.equalsIgnoreCase(regRequest.getCheckCode())){
            regResponse.setStatusCode(StatusCode.CHECK_CODE_ERROR);
            regResponse.setDate(new Date());
            regResponse.setServerMessage("CHECK CODE VERIFY ERROR");
            return regResponse;
        }

        //注册用户
        User user = new User();
        Account account = new Account();
        user.setName(regRequest.getUserName());
        user.setEmail(regRequest.getEmail());
        user.setPhone(regRequest.getPhone());
        user.setRegistrationDate(new Date());
        account.setPassword(regRequest.getAccountPassword());
        account.setAccountName(regRequest.getAccountName());
        account.setImgPath("");
        account.setRootDirID("1");
        account.setCreateDate(new Date());
        account.setMainKey(regRequest.getMainKey());
        account.setPublicKey(regRequest.getPublicKey());
        account.setPrivateKey(regRequest.getPrivateKey());
        Boolean result = userServices.registration(user, account);
        if(result == false){
            regResponse.setStatusCode(StatusCode.REGISTRATION_ERROR);
            regResponse.setDate(new Date());
            regResponse.setServerMessage("REGISTRATION ERROR");
            return regResponse;
        }
        String rootDirID = fileServices.initRootDir(account.getAccountID());
        userServices.setAccountRootDirID(account.getAccountID(), rootDirID);

        //封装返回信息
        regResponse.setStatusCode(StatusCode.REGISTRATION_SUCCESS);
        regResponse.setDate(new Date());
        regResponse.setServerMessage("REGISTRATION_SUCCESS");
        return regResponse;
    }

    //登录接口
    @RequestMapping("/login")
    public @ResponseBody LoginResponse login(
            @CookieValue(name = "SESSIONID",required = false) String sessionID,
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response)
    {
        //初始化
        String token = null;
        LoginResponse loginResponse = new LoginResponse();

        //设置session
        if(sessionID == null){
            sessionID = sessionServices.initSession();
            Cookie sessionidCookie = new Cookie("SESSIONID", sessionID);
            sessionidCookie.setPath("/");
            response.addCookie(sessionidCookie);
        }

        //登录认证
        Account account = userServices.login(loginRequest.getAccountName(), loginRequest.getAccountPassword());
        if(account == null){
            loginResponse.setStatusCode(StatusCode.LOGIN_FAILED);
            loginResponse.setDate(new Date());
            return loginResponse;
        }
        token = sessionServices.initToken(sessionID, account.getAccountID());
        loginResponse.setStatusCode(StatusCode.LOGIN_SUCCESS);
        loginResponse.setDate(new Date());
        loginResponse.setServerMessage("LOGIN_SUCCESS");
        loginResponse.setAccount(account);
        loginResponse.setToken(token);
        return loginResponse;
    }

    //消息同步接口
    @RequestMapping("/messageSync")
    @ResponseBody
    public MessageSycnResponse messageSync(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody RequestBase syncRequest,
            HttpServletRequest request)
    {
        //初始化
        MessageSycnResponse syncResponse = new MessageSycnResponse();

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, syncRequest.getToken());
        if(result == false){
            syncResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            syncResponse.setDate(new Date());
            syncResponse.setServerMessage("TOKEN VERIFY ERROR");
            return syncResponse;
        }

        //获取Messages
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        List<Message> messages = messageServices.getMessages(accountID);
        syncResponse.setStatusCode(StatusCode.MESSAGE_SYNC_SUCCESS);
        syncResponse.setDate(new Date());
        syncResponse.setServerMessage("MESSAGE SYNC SUCCESS");
        syncResponse.setMessages(messages);
        return syncResponse;
    }


    //用户信息同步接口
    @RequestMapping("/userSync")
    @ResponseBody
    public UserSyncResponse userSync(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody RequestBase syncRequest,
            HttpServletRequest request
        )
    {
        //初始化
        UserSyncResponse syncResponse = new UserSyncResponse();

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, syncRequest.getToken());
        if(result == false){
            syncResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            syncResponse.setDate(new Date());
            syncResponse.setServerMessage("TOKEN VERIFY ERROR");
            return syncResponse;
        }

        //获取User
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        User user = userServices.getUser(accountID);
        syncResponse.setStatusCode(StatusCode.USER_SYNC_SUCCESS);
        syncResponse.setDate(new Date());
        syncResponse.setServerMessage("USER SYNC SUCCESS");
        syncResponse.setUser(user);
        return syncResponse;
    }


    @RequestMapping("/accountSync")
    @ResponseBody
    public AccountSyncResponse accountSync(
            @CookieValue(name = "SESSIONID") String sessionID,
            @RequestBody  RequestBase syncRequest,
            HttpServletRequest request)
    {
        //初始化
        AccountSyncResponse syncResponse = new AccountSyncResponse();

        //验证token
        Boolean result = sessionServices.verifiyToken(sessionID, syncRequest.getToken());
        if(result == false){
            syncResponse.setStatusCode(StatusCode.TOKEN_VERIFY_ERROR);
            syncResponse.setDate(new Date());
            syncResponse.setServerMessage("TOKEN VERIFY ERROR");
            return syncResponse;
        }

        //获取account
        Integer accountID = Integer.valueOf(sessionServices.getSessionAttribute(sessionID,"accountID"));
        Account account = userServices.getAccount(accountID);
        syncResponse.setStatusCode(StatusCode.ACCOUNT_SYNC_SUCCESS);
        syncResponse.setDate(new Date());
        syncResponse.setServerMessage("ACCOUNT SYNC SUCCESS");
        syncResponse.setAccount(account);
        return syncResponse;
    }

}
