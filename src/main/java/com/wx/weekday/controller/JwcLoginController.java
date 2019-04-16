package com.wx.weekday.controller;

import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
@Controller
public class JwcLoginController {

    public void jwcLogin(String text){
        try {
            final Base64.Decoder decoder = Base64.getDecoder();
            final Base64.Encoder encoder = Base64.getEncoder();
            final byte[] textByte = text.getBytes("UTF-8");
            //编码
            final String encodedText = encoder.encodeToString(textByte);
            System.out.println(encodedText);
            //解码
            System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
}
