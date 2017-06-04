package com.weChat.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weChat.entity.BaseInfo;
import com.weChat.entity.Card;
import com.weChat.entity.Menu;
import com.weChat.entity.MenuOfMe;
import com.weChat.entity.PicStore;
import com.weChat.entity.TextMessage;

public interface WeChatService {

	List<Menu> findMenu();


	String doRequest(HttpServletRequest request);


	List<MenuOfMe> findMenuButton(String ids);


	Card getCardById();


	int updateCardId(Card card);


	int addPicStore(PicStore store);


	int addCard(BaseInfo baseInfo, String deal_detail);

}
