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

public class CardUtils {

	public static JSONObject createCard(String token ,String data){
		
		JSONObject obj = null;
		//或取client 客户端  
		CloseableHttpClient client = HttpClients.createDefault();
				
		HttpPost post = new HttpPost("https://api.weixin.qq.com/card/create?access_token="+token);
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
		        obj = JSONObject.parseObject(content);
		            
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
		
		
		return obj;
	}
	
}
