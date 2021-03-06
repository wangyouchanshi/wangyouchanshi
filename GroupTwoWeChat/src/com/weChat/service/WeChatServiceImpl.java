package com.weChat.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weChat.entity.BaseInfo;
import com.weChat.entity.Card;
import com.weChat.entity.CusEvent;
import com.weChat.entity.DateInfo;
import com.weChat.entity.Groupon;
import com.weChat.entity.Menu;
import com.weChat.entity.MenuOfMe;
import com.weChat.entity.PicStore;
import com.weChat.entity.Sku;
import com.weChat.entity.TextMessage;
import com.weChat.mapper.WeChatMapper;
import com.weChat.utils.MessageUtil;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Autowired
	private WeChatMapper mapper;

	@Override
	public List<Menu> findMenu() {
		return mapper.findMenu();
	}


	@Override
	public String doRequest(HttpServletRequest request) {
		// xml格式的消息数据
				String respXml = null;
				// 默认返回的文本消息内容
				String respContent = "未知的消息类型！";
				try {
					// 调用parseXml方法解析请求消息
					Map<String, String> requestMap = MessageUtil.parseXml(request);
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					// 开发者微信号
					String toUserName = requestMap.get("ToUserName");
					// 消息类型
					String msgType = requestMap.get("MsgType");
					//消息内容
					
					// 回复文本消息
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					// 文本消息
					if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
						
						
						respContent = "您发送的是文本消息！";
					}
					// 图片消息
					else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
						respContent = "您发送的是图片消息！";
					}
					// 语音消息
					else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
						respContent = "您发送的是语音消息！";
					}
					// 视频消息
					else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
						respContent = "您发送的是视频消息！";
					}
					// 地理位置消息
					else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
						respContent = "您发送的是地理位置消息！";
					}
					// 链接消息
					else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
						respContent = "您发送的是链接消息！";
					}
					// 事件推送
					else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
						// 事件类型
						String eventType = requestMap.get("Event");
						// 关注
						if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
							respContent = "谢谢您的关注！";
						}
						// 取消关注
						else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
							// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
						}
						// 扫描带参数二维码
						else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
							// TODO 处理扫描带参数二维码事件
						}
						// 上报地理位置
						else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
							// TODO 处理上报地理位置事件
						}
						// 自定义菜单
						else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
							// TODO 处理菜单点击事件--杨冠宇：此处是点击与我联系之后，转发给客服（任意）
							String EventKey = request.getParameter("EventKey");
							if("ywlx_001".equals(EventKey))
							{
								CusEvent eve=new CusEvent();
								eve.setToUserName(fromUserName);
								eve.setFromUserName(toUserName);
								eve.setCreateTime(new Date().getTime());
								eve.setMsgType("transfer_customer_service");
								//记录调用客服的用户信息
								this.mapper.saveSeekCustom(eve);
								
								String xml = MessageUtil.messageToXml(eve);
								return xml;
							}
							
						}
					}
					// 设置文本消息的内容
					textMessage.setContent(respContent);
					// 将文本消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
					
					int i = mapper.insertMessage(requestMap);
					System.out.println(i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return respXml;
		
		
		
	}


	@Override
	public List<MenuOfMe> findMenuButton(String ids) {
		
		
		
		return mapper.findMenuButton(ids);
	}


	@Override
	public Card getCardById() {

		Card card = mapper.getCardById();
		Integer base_info_id = card.getBase_info_id();
		Integer groupon_id = card.getGroupon_id();
		Groupon groupon = mapper.getGrouponById(groupon_id);
		
		BaseInfo base_info = mapper.getBaseInfoById(base_info_id);
		base_info.setSku(new Sku());
		base_info.setDate_info(new DateInfo());
		groupon.setBase_info(base_info);
		card.setGroupon(groupon);
		return card;
	}


	@Override
	public int updateCardId(Card card) {
		return mapper.updateCardId(card);
	}


	@Override
	public int addPicStore(PicStore store) {
		return mapper.addPicStore(store);
	}


	@Override
	public int addCard(BaseInfo baseInfo, String deal_detail) {
		int i = 0 ;
		//添加 baseInfo 
		i+= mapper.addBaseInfo(baseInfo);
		Groupon group = new Groupon();
		group.setDeal_detail(deal_detail);
		//添加 groupon
		i+= mapper.addGroupon(group);
		
		Card card = new Card();
		card.setBase_info_id(baseInfo.getBase_info_id());
		
		
		return 0;
	}
	
}
