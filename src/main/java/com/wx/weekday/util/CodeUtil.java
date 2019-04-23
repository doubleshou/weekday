package com.wx.weekday.util;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class CodeUtil {
    @PostMapping("/encodeByBase64")
    public static String encodeByBase64(@RequestParam("text") String text){
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
    public static String decodeByBase64(@RequestParam("text") String text){
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            String decodedText=new String(decoder.decode(text), "UTF-8");
            return decodedText;
        }catch (UnsupportedEncodingException e){
            return ("Error :" + e.getMessage());
        }
    }

    @PostMapping("/encodeByMd5")
    public static String encodeByMd5(@RequestParam("text") String text){
        try {
            String encodedText= DigestUtils.md5DigestAsHex(text.getBytes());
            return encodedText;
        }catch (UnknownError e){
            return ("Error :" + e.getMessage());
        }
    }
}
