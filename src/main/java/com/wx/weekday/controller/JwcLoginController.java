package com.wx.weekday.controller;

import com.wx.weekday.util.HttpClientUtil;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        String randnumber="9NSh";
        String url="http://222.195.158.225:80/cas/logon.action";
        String _sessionid="8E278202740A4A0A4CBA9C22BD87D9B3.kingo";
        Map<String, String> param = new HashMap<>();
        param.put("_u"+randnumber,encodeByBase64(username+";;"+_sessionid));
        param.put("_p"+randnumber,encodeByMd5(encodeByMd5(password)+encodeByMd5(randnumber.toLowerCase())));
        param.put("randnumber",randnumber);
        param.put("isPasswordPolicy","1");
        String resultString= HttpClientUtil.doPost(url,param);
        return resultString;
    }
}
