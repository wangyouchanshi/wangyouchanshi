package com.weChat.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weChat.entity.BaseInfo;
import com.weChat.entity.Card;
import com.weChat.entity.Menu;
import com.weChat.entity.MenuOfMe;
import com.weChat.entity.PicStore;
import com.weChat.entity.TextMessage;
import com.weChat.service.WeChatService;
import com.weChat.utils.CardUtils;
import com.weChat.utils.MenuUtils;
import com.weChat.utils.SignUtil;
import com.weChat.utils.UploadMedia2Util;

@Controller
@RequestMapping("/weChat")
public class WeChatController {

	@Autowired
	private WeChatService service;
	private final String ACCESS_TOKEN = "27uNZZEwMXmGzN5ee9tyig-NB9NyEfUECg_T4bXdsLI4sKPt8b3VNI8PizhHLKjRnSMLUGAlgbqfKsI29Km3eb-VJETzYvQ99Tu7xyMaUENpIlLm2xCmv_NReJ79V9EwVHGdAIAMYX";
	@RequestMapping("/reqMessage")
	public String reqMessage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getMethod();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter writer = response.getWriter();
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(method);
		if(method.equals("GET")){
			boolean b = SignUtil.checkSignature(signature, timestamp, nonce);
			if(b){
				writer.println(echostr);
			}
			
			
		}else if(method.equals("POST")){
			if(SignUtil.checkSignature(signature, timestamp, nonce)){
				
				String respXml = service.doRequest(request);
				writer.print(respXml);
			}
		}
		
		return null;
	}
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		
		List<Menu> list = service.findMenu();
		for (Menu menu : list) {
			
			System.out.println(menu.getText());
			
		}
		
		String jsonString = JSONObject.toJSONString(list);
		
		request.getSession().setAttribute("json", jsonString);
		return "index";
	}
	
	/**
	 * 
	 * 作者姓名：L   Y
	 * 2017年5月20日
	 * @param request
	 * @return
	 * 自定义菜单
	 */
	@RequestMapping("/menuOfMySelf")
	public String menuOfMySelf(HttpServletRequest request,String ids){
		
		//查询所有的menu_button菜单
		List<MenuOfMe> list = service.findMenuButton(ids);
		//用于存储pid为0的MenuOfMe对象
		List<MenuOfMe> button = new ArrayList<MenuOfMe>();
		// 存放每个 MenuOfMe 
		Map<Integer, MenuOfMe> map = new HashMap<Integer, MenuOfMe>();
		//遍历list 取出pid为0的对象 
		for (MenuOfMe menuOfMe : list) {
			if(menuOfMe.getPid()==0){
				button.add(menuOfMe);
			}
			//把每个对象放入map里面
			map.put(menuOfMe.getId(), menuOfMe);
		}
		// 遍历list
		for (MenuOfMe menu : list) {
			if(menu.getPid()!=0){
				MenuOfMe menuOfMe = map.get(menu.getPid());
				
				menuOfMe.getSub_button().add(menu);
				
			}
		}
		
		Map<String, List<MenuOfMe>> map1 = new HashMap<String, List<MenuOfMe>>();
		
		//String jsonString = JSONObject.toJSONString(button);
		
		map1.put("button", button);
		String butt = JSONObject.toJSONString(map1);
		//System.out.println(jsonString);
		//System.out.println("--------------------");
		//测试json数据格式正确
		System.out.println(butt);
		
		//或取client 客户端  
		boolean b = MenuUtils.setMenu("KLlwhP27QFmA74t6Nqz8nYSp-T-N_IP_9xhCOPf-gDWhwu2pPwiJMXxOod_KbLXlBouxlP9mTnZIO84tYsDIw-hvChFaWortk3xSvC1KGdgi_WgUuuUDj09GA48Ki28oHQRjABAUDE", butt);

		System.out.println(b);
		return null;
	}
	
	@RequestMapping("/getCardId")
	public String getCardId(){
		
		Card card = service.getCardById();
		
		Map<String, Object> map = new HashMap<String, Object>();
		Integer id = card.getId();
		
		//String jsonString = JSONObject.toJSONString(button);
		card.setId(null);
		card.setBase_info_id(null);
		card.setGroupon_id(null);
		card.setCard_id(null);
		map.put("card", card);
		String butt = JSONObject.toJSONString(map);
		JSONObject card2 = CardUtils.createCard(ACCESS_TOKEN, butt);
		String card_id = (String) card2.get("card_id");
		card.setCard_id(card_id);
		card.setId(id);
		int i = service.updateCardId(card);
		System.out.println(card2.toJSONString()+"++++++"+id);
		
		return null;
	}
	
	@RequestMapping("/createCardPage")
	public String createCardPage(){
		System.out.println("_________");
		
		return "add_card";
	}
	
	@RequestMapping("/createCard")
	@ResponseBody
	public boolean createCard(BaseInfo baseInfo,String deal_detail){
		
		//获取baseInfo信息  插入数据库    返回baseInfo id
		//再插入groupon 表返回 id 最后插入card表 返回card表的id
		int card_id = service.addCard(baseInfo,deal_detail);
		
		return true;
	}
	
	@RequestMapping("/uploadLogo")
	@ResponseBody()
	public String uploadLogo(MultipartFile logo,String brand_name) throws IOException{
		
		/* CommonsMultipartFile cf= (CommonsMultipartFile)logo; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File f = fi.getStoreLocation();
		*/
		
		if(logo==null)
			return "error";
		
		String originalFilename = logo.getOriginalFilename();
		
		//精确到毫秒  如果有高并发可以再加几组随机数
		String string = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		/*Random r = new Random();
		for (int i = 0; i < 3; i++) {
			fileName = fileName+r.nextInt(10);
		}*/
		String newFileName = string + originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String filePath =  "E:\\upload\\";
		
		File file = new File(filePath+newFileName);
		
		logo.transferTo(file);
		
		JSONObject jsonObject = UploadMedia2Util.uploadPermanentMedia2(ACCESS_TOKEN, file, "test", "test");
		
		PicStore store = new PicStore();
		store.setType("logo");
		store.setMedia_id(jsonObject.getString("media_id"));
		store.setUrl(jsonObject.getString("url"));
		store.setPic_name(newFileName);
		service.addPicStore(store);
		return newFileName;
	}
	
	@RequestMapping("menu")
	public String menu(){
		
		return "manageselfmenu";
	}
	
	
}
