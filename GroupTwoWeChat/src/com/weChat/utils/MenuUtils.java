package com.weChat.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 修改自定义菜单的工具类
 * @author J&L
 *
 */
public class MenuUtils {

	
	public static boolean setMenu(String token,String data){
		
		String object = "";
		//或取client 客户端  
		CloseableHttpClient client = HttpClients.createDefault();
				
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token);
		
		post.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8     ");
		post.addHeader("Accept-Encoding", "gzip, deflate, br");
		post.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Host", "openapi.baidu.com");
		post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		try {
			StringEntity entity = new StringEntity(data, Charset.forName("utf-8"));
			entity.setContentType("application/json; charset=UTF-8");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CloseableHttpResponse response = null;
			try {
				response = client.execute(post);
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity,"UTF-8");
		        System.out.println(content);
		        JSONObject obj = JSONObject.parseObject(content);
		        object = (String) obj.get("errmsg");
		            
		         System.out.println(obj.toJSONString());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						response.close();
						client.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				
		if(object.equals("ok"))
			return true ;
		else
			return false;
		
	}
	
}
