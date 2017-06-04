package com.weChat.entity;


public class BaseInfo {

	private Integer base_info_id ;
	private  String logo_url;
	private static final String code_type  = "CODE_TYPE_TEXT" ;
	private String brand_name;
	private String title;
	private String sub_title;
	private static final String color = "Color010" ;
	private String notice ;
	private String service_phone;
	private String description;
	private DateInfo date_info;
	private Sku sku;
	private static final Integer get_limit = 3 ;
	private static final boolean use_custom_code = false;
	private static final boolean bind_openid = false;
	private static final boolean can_share = true;
	private static final boolean can_give_friend = true;
	private static final int[] location_id_list  = new int[]{123, 12321, 345345};
	private static final String custom_url_name = "前往领取";
	private String custom_url;
	
	private String custom_url_sub_title;
	private static final String promotion_url_name  = "更多优惠";
	private  String promotion_url;
	
	public Integer getBase_info_id() {
		return base_info_id;
	}
	public void setBase_info_id(Integer base_info_id) {
		this.base_info_id = base_info_id;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public String getCode_type() {
		return code_type;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getColor() {
		return color;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getService_phone() {
		return service_phone;
	}
	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DateInfo getDate_info() {
		return date_info;
	}
	public void setDate_info(DateInfo date_info) {
		this.date_info = date_info;
	}
	public Sku getSku() {
		return sku;
	}
	public void setSku(Sku sku) {
		this.sku = sku;
	}
	public Integer getGet_limit() {
		return get_limit;
	}
	
	public boolean isUse_custom_code() {
		return use_custom_code;
	}
	
	public boolean isBind_openid() {
		return bind_openid;
	}
	public boolean isCan_share() {
		return can_share;
	}
	
	public boolean isCan_give_friend() {
		return can_give_friend;
	}
	/*public void setUse_custom_code(int use_custom_code) {
		this.use_custom_code = use_custom_code==1?true:false;
	}
	public void setBind_openid(int bind_openid) {
		this.bind_openid = bind_openid==1?true:false;
	}
	public void setCan_share(int can_share) {
		this.can_share = can_share==1?true:false;
	}
	
	public void setCan_give_friend(int can_give_friend) {
		this.can_give_friend = can_give_friend== 1 ?true:false;
	}*/
	public int[] getLocation_id_list() {
		return location_id_list;
	}
	public String getCustom_url_name() {
		return custom_url_name;
	}
	public String getCustom_url() {
		return custom_url;
	}
	public void setCustom_url(String custom_url) {
		this.custom_url = custom_url;
	}
	public String getCustom_url_sub_title() {
		return custom_url_sub_title;
	}
	public void setCustom_url_sub_title(String custom_url_sub_title) {
		this.custom_url_sub_title = custom_url_sub_title;
	}
	public String getPromotion_url_name() {
		return promotion_url_name;
	}
	public String getPromotion_url() {
		return promotion_url;
	}
	public void setPromotion_url(String promotion_url) {
		this.promotion_url = promotion_url;
	}
	
	
	
}
