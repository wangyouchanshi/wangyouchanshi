package com.weChat.utils;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class Access_TokenUtils {

	//static String appid = "wx3f94f550cb0b04f4";
//	static String secret = "1a02a6e5ca2128b979e063153cdadc17";
	
	private static String appid = "wxa19f9db81fc4b3e8";
	private static String secret = "6593cdc8fbd992443d1e866f5ab6d9f2";
	
	public static String getToken(){
		String access_token = "";
		//创建一个HttpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		//创建一个get请求
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret);
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8     ");
		get.addHeader("Accept-Encoding", "gzip, deflate, br");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Host", "openapi.baidu.com");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		CloseableHttpResponse response;
           try {
                response = client.execute(get);
                HttpEntity entity = response.getEntity(); 
                String content = EntityUtils.toString(entity,"UTF-8");
                JSONObject obj = JSONObject.parseObject(content);
                access_token = (String) obj.get("access_token");
                System.out.println(new Date().toLocaleString()+"您获取的 access_token是"+ access_token);
           }catch(Exception e){
        	   
        	   e.printStackTrace();
           }
		
		return access_token;
	}
	
}
