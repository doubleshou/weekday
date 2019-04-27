package com.wx.weekday.controller;

import com.wx.weekday.util.CodeUtil;
import com.wx.weekday.util.HttpClientUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    //获取课程表页面html的字符串
    @PostMapping("/html")
    public String getHtml(@RequestParam("xn") String xn, @RequestParam("xq") String xq,
                          @RequestParam("xh") String xh, @RequestParam("cookie") String cookie){
        String url="http://222.195.158.225/student/wsxk.xskcb.jsp?params=";
        url=url+CodeUtil.encodeByBase64("xn="+xn+"&"+"xq="+xq+"&"+"xh="+xh);
        String resultString=HttpClientUtil.doGet(url,cookie);
        return resultString;
    }

    //检查表格数据，以便去除无用数据
    public boolean checkLesson(String text){
        if(text.contains("星") || text.contains("午") || text.equals("一")
                || text.equals("二") || text.equals("三") || text.equals("四") || text.equals("")){
            return false;
        }
        else return true;
    }

    //使用Jsoup提取html中的表格数据
    @PostMapping("/lesson")
    public void getLesson(@RequestParam("html") String html){
        try {
            Document document=Jsoup.parse(html);
            //获取表格
            Elements elements = document.select("table").select("tr");
            //System.out.println(elements);
            //处理每一行
            for(int i=0;i<elements.size()-1;i++){
                //获取表格的列
                Elements tds=elements.get(i).select("td");
                //System.out.println(tds);
                //处理每一列
                for(int j=0;j<tds.size();j++){
                    //获取第i行第j列的文本
                    String text=tds.get(j).text();
                    if (checkLesson(text)){
                        System.out.println(i);//行数
                        System.out.println(j-(i%2));//课程在星期几
                        System.out.println(text);//课程信息字符串，需要分割处理
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
