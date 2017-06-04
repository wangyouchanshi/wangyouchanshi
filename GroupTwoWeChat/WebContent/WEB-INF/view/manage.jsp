<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>


<body>
<script type="text/javascript">
$(function(){
	
	$("#cus_table").datagrid({    
	    url:"${pageContext.request.contextPath}/cuService/information.controller",    
	    columns:[[    
	        {field:'kf_account',title:'客服编号',width:200},    
	        {field:'kf_nick',title:'客服昵称',width:200},    
	        {field:'kf_id',title:'客服工号',width:200}, 
	        {field:'kf_wx',title:'绑定的微信号',width:400,
	        	
	        	

	        }, 
	    ]],toolbar: [{
			iconCls: 'icon-man',
			handler: function(){
				var row = $("#cus_table").datagrid("getSelected");
				alert(row.kf_account);
				$.ajax({
					url:"${pageContext.request.contextPath}/cuService/getReception.controller",
					type:"post",
					data:{userno:row.kf_account},
					dataType:"json",
					success:function(obj){
												
					}
					
				});
				
			}
		},'-',{
			iconCls: 'icon-add',
			handler: function(){
				$("#cus_add").form("clear");
				$('#cus_dialog').dialog("open")
				
				
			}
		},'-',{
			iconCls: 'icon-cancel',
			handler: function(){
				var row = $("#cus_table").datagrid("getSelected");
				alert(row)
				$.ajax({
					url:"${pageContext.request.contextPath}/cuService/delCuService.controller",
					type:"post",
					data:{kf_account:row.kf_account,kf_nick:row.nickname,password:row.password},
					dataType:"json",
					success:function(obj){
						if(obj){
							$("#cus_table").datagrid("reload");
						}
					}
					
				})
				alert('删除客服')
				
			}
		},'-',{
			iconCls: 'icon-lock',
			handler: function(){
				var row = $("#cus_table").datagrid("getSelected");
				$("#cus_bind_form").form("clear");
				$("#cus_bind_form").form("load",row);
				$('#cus_dialogbind').dialog("open")
				
				
			}
		}]

	}); 
	
	//datagrid到此
	$('#cus_dialog').dialog({    
	    title: '添加新客服',    
	    width: 300,    
	    height: 200,    
	    closed: true,    
	   
	});
	
	$("#cus_sub").linkbutton({  
		
	}); 
	
	$("#cus_acc").textbox({
		label:"客服账号",
	})
	$("#cus_nick").textbox({
		label:"客服昵称",
		
	})
	$("#cus_pwd").textbox({
		label:"客服密码",
	})

	$("#cus_sub").click(function(){
		
		$.ajax({
			
			url:"${pageContext.request.contextPath}/cuService/addCuService.controller",
			type:"post",
			data:$("#cus_add").serialize(),
			dataType:"json",
			success:function(obj){
				if(obj){
					
				$("#cus_table").datagrid("reload");
				}
			}
			
		})
		
	});
	
    //绑定微信号
	$("#cus_bind_acc").textbox({
		label:"客服账号",
	})
	$("#cus_bind_wx").textbox({
		label:"微信号",
	})
	
	$('#cus_dialogbind').dialog({    
	    title: '绑定微信号',    
	    width: 300,    
	    height: 200,    
	    closed: true,    
	   
	});
	
	$("#cus_bind").linkbutton({  
		
	}); 
	
	$("#cus_bind").click(function(){
		
		$.ajax({
			
			url:"${pageContext.request.contextPath}/cuService/bindCuService.controller",
			type:"post",
			data:$("#cus_bind_form").serialize(),
			dataType:"json",
			success:function(obj){
				if(obj){
					$("#cus_table").datagrid("reload");
				}
			}
			
		})
	})//ajax
	
})
</script>
<table id="cus_table">

</table>
<div id="cus_dialog">
	<form id="cus_add">

		  
		<input id="cus_acc" name="kf_account" style="width:200px;"><br/><br/>
		
		<input id="cus_nick" name="kf_nick" style="width:200px;"><br/><br/>
		
		<input id="cus_pwd" name="password" style="width:200px;"><br/><br/>
		
		<a id="cus_sub">提交</a>
	</form>
</div>
<div id="cus_dialogbind">
	<form id="cus_bind_form">

		  
		<input id="cus_bind_acc" name="kf_account" style="width:200px;"><br/><br/>
		
		<input id="cus_bind_wx" name="invite_wx" style="width:200px;"><br/><br/>
		
		
		<a id="cus_bind">提交</a>
	</form>
</div>
</body>

</html>