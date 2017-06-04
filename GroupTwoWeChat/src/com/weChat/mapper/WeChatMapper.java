package com.weChat.mapper;

import java.util.List;
import java.util.Map;

import com.weChat.entity.BaseInfo;
import com.weChat.entity.Card;
import com.weChat.entity.CusEvent;
import com.weChat.entity.Groupon;
import com.weChat.entity.Menu;
import com.weChat.entity.MenuOfMe;
import com.weChat.entity.PicStore;

public interface WeChatMapper {

	List<Menu> findMenu();

	int insertMessage(Map<String, String> requestMap);

	List<MenuOfMe> findMenuButton(String ids);

	Card getCardById();

	BaseInfo getBaseInfoById(Integer groupon_id);

	Groupon getGrouponById(Integer groupon_id);

	int updateCardId(Card card);

	int addPicStore(PicStore store);

	int addBaseInfo(BaseInfo baseInfo);

	int addGroupon(Groupon group);

	void saveSeekCustom(CusEvent eve);

}
