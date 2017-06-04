package com.weChat.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author THINK
 * 获取郑祎老师的token
 */
public class getToken4ZY {

	public static String getToken() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get=new HttpGet("http://www.zhengyisky.com/Token/getToken.action");
		CloseableHttpResponse response = client.execute(get);
		
		if(response.getStatusLine().getStatusCode()==200){
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			return string;
		}
		
		return "";
	}
	
}
