package com.weChat.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weChat.entity.TreeGrid;
import com.weChat.entitytwo.InviteBind;
import com.weChat.entitytwo.KefuDetail;
import com.weChat.entitytwo.ServiceMan;
import com.weChat.entitytwo.UserCus;
import com.weChat.entitytwo.Weixin;
import com.weChat.service.CuService;
import com.weChat.utils.EveryUtils;
import com.weChat.utils.getToken4ZY;

import sun.misc.BASE64Encoder;

/**
 * 
 * @author THINK
 *	TO-DO :
 */
@Controller
@RequestMapping("cuService")
public class CuserviceCtroller {

	@Autowired
	private CuService cuService;
	
	@RequestMapping("manage")
	public String manage(){
		
		return "manage";
	}
	
	@RequestMapping("status")
	public String status(){
		
		return "status";
	}
	


	/**
	 * 作者姓名：杨冠宇
	 * 2017年5月15日
	 * @return
	 * 功能：获取客服的信息
	 * @throws Exception 
	 */
	@RequestMapping("information")
	@ResponseBody
	public List<KefuDetail> information() throws Exception{
		//第一步：获取token，以后删掉
		String access_token = getToken4ZY.getToken();
		
		//第二步：用get请求方式获取所有客服的信息
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token="+access_token+"");
		CloseableHttpResponse response = client.execute(get);
		
		//第三部，处理回调数据
		if (response.getStatusLine().getStatusCode() == 200) {

			HttpEntity entity2 = response.getEntity();
			String string = EntityUtils.toString(entity2,"utf-8");
			string=string.substring(string.indexOf("["), string.lastIndexOf("]")+1);
			//转成list返回前台
			List<KefuDetail> list = JSONArray.parseArray(string, KefuDetail.class);
			return list;
		}else{
			System.out.println("处理失败");
		}
		
		return null;
	}
	/**
	 * 
	 * 作者姓名：杨冠宇
	 * 2017年5月16日
	 * @return
	 * 功能：添加客服人员
	 * @throws Exception 
	 */
	@RequestMapping("addCuService")
	@ResponseBody
	public boolean addCuService(KefuDetail kefu) throws Exception{
		//第一步：获取token，以后删掉
		//Weixin weixin = EveryUtils.getToken();
		//String access_token = weixin.getAccess_token();
		//用新的方法获取token
		String access_token = getToken4ZY.getToken();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+access_token+"");
		//写到电商项目的时候一定要存进数据库里，本次没有存
		ServiceMan man = new ServiceMan();
		man.setKf_account("test@"+kefu.getKf_account());
		man.setNickname(kefu.getKf_nick());
		//待加密字符串
		String pass=kefu.getPassword();
		//md5工具
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base = new BASE64Encoder();
		//编码
		String newstr = base.encode(md5.digest(pass.getBytes("utf-8")));
		man.setPassword(newstr);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(man);
		System.out.println(json);
		//Httpclient需要传输数据方法必须是实体类，所以要用StringEntity
		StringEntity entity = new StringEntity(json, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		if (response.getStatusLine().getStatusCode() == 200) {

			HttpEntity entity2 = response.getEntity();
			String string = EntityUtils.toString(entity2,"utf-8");
			System.out.println(string);
			

		}else{
			System.out.println("请求失败");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * 作者姓名：杨冠宇
	 * 2017年5月22日
	 * @return
	 * 功能：绑定客服的微信号
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("bindCuService")
	@ResponseBody
	public boolean bindCuService(InviteBind bind) throws ClientProtocolException, IOException{
		String access_token = getToken4ZY.getToken();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token="+access_token+"");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bind);
		StringEntity entity = new StringEntity(json, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		//执行请求
		CloseableHttpResponse response = client.execute(post);
		
		if (response.getStatusLine().getStatusCode() == 200) {

			HttpEntity entity2 = response.getEntity();
			String string = EntityUtils.toString(entity2,"utf-8");
			System.out.println(string);
			

		}else{
			System.out.println("请求失败");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * 作者姓名：杨冠宇
	 * 2017年5月20日
	 * @param kefu
	 * @param res
	 * @return
	 * @throws Exception
	 * 功能：删除客服
	 */
	@RequestMapping("delCuService")
	@ResponseBody
	public boolean delCuService(KefuDetail kefu,HttpServletResponse res) throws Exception{
		//第一步：获取token，以后删掉
		//Weixin weixin = EveryUtils.getToken();
		//String access_token = weixin.getAccess_token();
		String access_token = getToken4ZY.getToken();
		CloseableHttpClient client = HttpClients.createDefault();
	
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base = new BASE64Encoder();
		String pass="taozi250";
		String newstr = base.encode(md5.digest(pass.getBytes("utf-8")));
		HttpGet get = new HttpGet("https://api.weixin.qq.com/customservice/kfaccount/del?access_token="+access_token+"&kf_account="+
				kefu.getKf_account()+"&nickname="+kefu.getKf_nick()+"&password="+newstr+"");
		CloseableHttpResponse execute = client.execute(get);
		if(execute.getStatusLine().getStatusCode()==200){
			
			HttpEntity entity2 = execute.getEntity();
			String string = EntityUtils.toString(entity2,"utf-8");
			System.out.println(string);
			return true;
		}
		
		
		return false;
	}
	
	@RequestMapping("getReception")
	public String getReception(String userno) throws ClientProtocolException, IOException{
		String access_token = getToken4ZY.getToken();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get=new HttpGet("https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token="+access_token+"&kf_account="+userno+"");
		CloseableHttpResponse response = client.execute(get);
		if(response.getStatusLine().getStatusCode()==200){
			
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			string=string.substring(string.indexOf("["), string.lastIndexOf("]")+1);
			List<UserCus> list= JSONArray.parseArray(string,UserCus.class);
			
		}
		return null;
	}
	/**
	 * 
	 * 作者姓名：wan ren jing yang の 杨冠宇
	 * 2017年5月19日
	 * @return
	 * 功能： 把自定义菜单放到管理页面
	 */
	@RequestMapping("getSelfDefinationalMenu")
	@ResponseBody
	public List<TreeGrid> getSelfDefinationalMenu(){
		//查询列表
		List<TreeGrid> list=this.cuService.getSelfDefinationalMenu();
		//创建子列表
		List<TreeGrid> newlist=new ArrayList<TreeGrid>();
		//第一次循环，把一级菜单放进去
		for (TreeGrid t : list) {
			if(t.getPid()==0){
				newlist.add(t);
			}
		}
		for (TreeGrid treeGrid : newlist) {
			for (TreeGrid t : list) {
				if(t.getPid()==treeGrid.getId()&&treeGrid!=null){
					treeGrid.getChildren().add(t);
				}
			}
		}
		
		return newlist;
	}
	
	
	
}
