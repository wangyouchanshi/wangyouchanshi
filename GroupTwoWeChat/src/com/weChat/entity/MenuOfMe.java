package com.weChat.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuOfMe {

	private Integer id ;
	private Integer pid ;
	private String key ;
	private String name ;
	private String url ;
	private Integer type_id ;
	private String type;
	private String msg ;
	private List<MenuOfMe> sub_button = new ArrayList<MenuOfMe>();
	
	public List<MenuOfMe> getSub_button() {
		return sub_button;
	}
	
	public void setSub_button(List<MenuOfMe> sub_button) {
		this.sub_button = sub_button;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
