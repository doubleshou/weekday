package com.wx.weekday.controller;

import com.wx.weekday.util.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/jwcLogin")
public class JwcLoginController {

    @PostMapping("/encodeByBase64")
    public String encodeByBase64(@RequestParam("text") String text){
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] textByte = text.getBytes("UTF-8");
            String encodedText = encoder.encodeToString(textByte);
            return encodedText;
        } catch (UnsupportedEncodingException e) {
            return ("Error :" + e.getMessage());
        }
    }

    @PostMapping("/decodeByBase64")
    public String decodeByBase64(@RequestParam("text") String text){
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            String decodedText=new String(decoder.decode(text), "UTF-8");
            return decodedText;
        }catch (UnsupportedEncodingException e){
            return ("Error :" + e.getMessage());
        }
    }

    @PostMapping("/encodeByMd5")
    public String encodeByMd5(@RequestParam("text") String text){
        try {
            String encodedText= DigestUtils.md5DigestAsHex(text.getBytes());
            return encodedText;
        }catch (UnknownError e){
            return ("Error :" + e.getMessage());
        }
    }

    //获取Cookie
    @PostMapping("/cookie")
    public String cookie(){
        //String cookie="";
        CookieStore cookieStore=null;
        String JSESSIONID=null;
        String url="http://222.195.158.225:80/cas/login.action";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get=new HttpGet(url);
        HttpClientContext context = HttpClientContext.create();
        try {
            CloseableHttpResponse response = httpClient.execute(get, context);
            try {
                //cookie=Arrays.stream(response.getAllHeaders()).toString();
                cookieStore = context.getCookieStore();
                List<Cookie> cookies = cookieStore.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    if (cookies.get(i).getName().equals("JSESSIONID")) {
                        JSESSIONID = cookies.get(i).getValue();
                    }
                    //cookie=cookie+context.getCookieStore().getCookies();
                }
            }
            finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "JSESSIONID="+JSESSIONID;
    }

    //模拟登录
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,
                        @RequestParam("randnumber") String randnumber, @RequestParam("cookie") String cookie){
        String url="http://222.195.158.225:80/cas/logon.action";
        String _sessionid=cookie.substring(11);
        Map<String, String> param = new HashMap<>();
        param.put("_u"+randnumber,encodeByBase64(username+";;"+_sessionid));
        param.put("_p"+randnumber,encodeByMd5(encodeByMd5(password)+encodeByMd5(randnumber.toLowerCase())));
        param.put("randnumber",randnumber);
        param.put("isPasswordPolicy","1");
        System.out.println(param);
        String resultString= HttpClientUtil.doPost(url,param,cookie());
        //String resultString=param.toString();
        return resultString;
    }

    //获取验证码图片，以UUID命名，并存入src\main\resources\captcha
    @RequestMapping("/randnumber")
    public String randnumber(){
        String url="http://222.195.158.225/cas/genValidateCode?dateTime=";
        String uuid=UUID.randomUUID().toString();
        String cookie=cookie();
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Cookie",cookie);
            // 请求http
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + response.getStatusLine());
            }
            // save img
            String picName = "src\\main\\resources\\captcha";
            File f = new File(picName);
            f.mkdirs();
            picName += "/" + uuid + ".jpg";
            InputStream inputStream = response.getEntity().getContent();
            OutputStream outStream = new FileOutputStream(picName);
            IOUtils.copy(inputStream, outStream);
            outStream.close();
            System.out.println(picName + " OK!");
            httpGet.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;
    }
}
