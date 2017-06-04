<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
    <style type="text/css">
        .policy_editor {
            width:100%;
        }
        .div_form{
        	margin: 10px 0px 10px 20px;
        }
    </style>
   	<script type="text/javascript">
   		$("#add_card_title").textbox({
   			width:"250px",
			label:"标题:"
   		})
   		
   		$("#add_card_sub_title").textbox({
   			width:"250px",
			label:"子标题:"
   		})
   		$("#add_card_brand_name").textbox({
   			width:"250px",
			label:"店铺名称:"
   		})
   		
   		/* $("#pol_imgs").filebox({
   			width:"250px",
			label:"上传图片:"
   		}) */
   		
   		
   		$("#add_card_notice").textbox({
   			width:"250px",
			label:"提示信息:"
   		})
   		
   		$("#add_card_service_phone").textbox({
   			width:"250px",
			label:"服务号码:"
   		})
   		
   		
   		$("#pol_file").filebox({ 
   			width:'250px',
   		    buttonText: '选择文件', 
   		    buttonAlign: 'left',
   		    label:"店铺Logo",
   		 	onChange:function(newValue, oldValue){
   		 	var opt = {
					//重新指定form的action的值
					url:"${pageContext.request.contextPath}/weChat/uploadLogo.controller",
					type:"post",
					dateType:"text",
					data:{
						fileName:oldValue
						
					},
					success:function(obj){
						/* var jsonObj = $.parseJSON($(obj).text()); */
						$("#imgsImgSrc").attr("src","http://PC-20160927GUWP:8080/pic/"+obj);
						$("#add_card_logo_url").val(obj);
						
					},
					error:function(){
						alert("系统错误");
					}
					
				};
				$("#add_card_form").ajaxSubmit(opt);
   		 		
   		 	}
   		})


   		
   		$('#policy_add_submit').linkbutton({
				width:"80px"
			})
			
		$('#policy_add_reset').linkbutton({
				width:"80px"
		})
   		
		$("#add_card_submit").click(function(){
	        
	        $.ajax({
	        	url:"${pageContext.request.contextPath}/weChat/createCard.controller",
	        	data:$("#add_card_form").serialize(),
	        	dataType:"json",
	        	type:"post",
	        	success:function(o){
	        		
	        		if(o){
	        			$.messager.alert("恭喜","添加成功");
	        			$("#tt").tabs("close","创建卡券");
	        			//$("#policy_datagrid").datagrid("reload");
	        		}else{
	        			$.messager.alert("警告","提交失败");
	        		}
	        		
	        	}
	        })
		})
		
		$("#add_card_description").textbox({
   			width:"250px",
			label:"描述信息:"
   		})
   		
   		$("#add_card_custom_url").textbox({
   			width:"250px",
			label:"领取连接:"
   		})
   		
   		$("#add_card_promotion_url").textbox({
   			width:"250px",
			label:"更多的连接:"
   		})
   		
   		$("#add_card_deal_detail").textbox({
   			width:"250px",
			label:"优惠描述:"
   		})
   		
		
   	</script>
<div id="btns">

	<form id="add_card_form" enctype="multipart/form-date" method="post" >
		<div class="div_form" >
		<input type="text" name="brand_name" id= "add_card_brand_name" ><br>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img alt="Logo 点我在素材库中选取" width="80px" height="80px" src="" id="imgsImgSrc" ><br>
		<div class="div_form" >
		<input type="hidden" 
		id="add_card_logo_url" name="logo_url"  >
		<input  name="logo" id="pol_file" ><br>
		<!-- <input  class="easyui-filebox" name="pol_imgs" id="pol_imgs"  data-options="onChange:fileUpload() "  ><br> -->
		</div>
		
		<div class="div_form" >
		<input type="text" name="title" id="add_card_title"  ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="sub_title"  id="add_card_sub_title" ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="notice" id= "add_card_notice" ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="service_phone" id= "add_card_service_phone" ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="description" id= "add_card_description" ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="custom_url" id= "add_card_custom_url" ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="promotion_url" id= "add_card_promotion_url" ><br>
		</div>
		<div class="div_form" >
		<input type="text" name="deal_detail" id= "add_card_deal_detail" ><br>
		</div>
     	<input type="hidden" name="pol_content" id="pol_content" >
     	<a id="add_card_submit" >提交</a>	
     	<a id="add_card_reset" >重置</a>	
     
	</form>

    

    

</div>		
</body>
</html>