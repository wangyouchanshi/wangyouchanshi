<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<script type="text/javascript">
$(function(){
$('#cus').treegrid({    
    url:'${pageContext.request.contextPath}/cuService/getSelfDefinationalMenu.controller',    
    method: 'get',
	rownumbers: true,
	idField: 'id',
	treeField: 'name',    
    columns:[[  
              {field:'userId',title:'User', width:50,
      			formatter: function(value,row,index){
      				if(row.pid!=0)
      				return "<input type='checkbox' class='cs' value='"+row.id+"'>";
      				else
      					return "请选择";
      			}
      		},
        {field:'name', title:'菜单',width:180},    
        {field:'type_id', title:'类型',width:60},    
        {field:'key',title:'key值',width:80},    
        {field:'url',title:'url值',width:180}  ,  


    	
    
    ]]    
});  
	
$("#cus_plus").click(function(){
	var ids=",1,2,3";
	$(".cs:checked").map(function(){
		ids+=","+$(this).val()
	})
	ids=ids.substr(1);
	//alert(ids)
	if(ids=="1,2,3"){
		
		alert("请您选择要同步到微信上的")
	}else{
		location.href="${pageContext.request.contextPath}/weChat/menuOfMySelf.controller?ids="+ids;
	}
		
		
});

})

</script>


	<h2>自定义菜单</h2>
	<div style="margin:20px 0;"></div>
	<table id="cus" title="Folder Browser" class="easyui-treegrid" style="width:700px;height:450px"
			>

		
	</table>
	<button id="cus_plus">同步到微信</button>
</body>
</html>