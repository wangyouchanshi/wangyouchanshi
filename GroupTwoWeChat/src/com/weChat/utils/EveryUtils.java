package com.weChat.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weChat.entitytwo.Weixin;

public class EveryUtils {

	public static boolean vertifyFist(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
	
			String signature = req.getParameter("signature");
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String echostr = req.getParameter("echostr");
			List<String> list=new ArrayList<String>();
			list.add("yang1991");
			list.add(timestamp);
			list.add(nonce);
			
			Collections.sort(list);
			String s="";
			for (String string : list) {
				s+=string;
			}
			
			String signature2=DigestUtils.shaHex(s);
			
			if(signature2.equals(signature)){
				PrintWriter out = res.getWriter();
				out.println(echostr);
				out.close();
				return true;
			}

		
	
		return false;
		
	}
	
	
	public static Weixin getToken() throws Exception{
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa19f9db81fc4b3e8&secret=6593cdc8fbd992443d1e866f5ab6d9f2");

		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8	");
		get.addHeader("Accept-Encoding", "gzip, deflate, br");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Host", "mp.weixin.qq.com");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		
		CloseableHttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity,"UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		Weixin weixin = mapper.readValue(content, Weixin.class);


		
		return weixin;
	}
	
}
