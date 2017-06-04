<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<style type="text/css">
.div_form{
        	margin: 10px 0px 10px 20px;
        }
</style>

		 <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyUI/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/locale/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript">
$(function(){
	
	
	var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey:"id",
                    pIdKey:"pid"
                },
                key:{
                    name:"text",
                    url:"href"
                }
            },callback: {
                onClick: zTreeOnClick
            }
        };
        function zTreeOnClick(event, treeId, treeNode) {
            if(treeNode.url!=""&&treeNode.url!=undefined){
                var flag=$("#tt").tabs("exists",treeNode.text);
                if(!flag){
                    $("#tt").tabs("add",{
                        title:treeNode.text,
                        href:"${pageContext.request.contextPath}/"+treeNode.url,
                        closable:true
                    });
                }else{
                    $("#tt").tabs("select",treeNode.text);
                }
            }
        };

        var zNodes =${json};
        $(document).ready(function(){
            $.fn.zTree.init($("#tree"), setting, zNodes);
        });

	
	
})

</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:120px;background:#B3DFDA;padding:10px">
	<h2>微信公众号后台管理系统</h2>
	<p style="float: right;">欢迎${sessionScope.existUser.username}登录<input type="button" value="注销" onclick="exit()"/></p></div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
		<div id="tree" class="ztree" ></div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div id="center" data-options="region:'center' ">
		<div id="tt" class="easyui-tabs" data-options="fit:true,border:0">
		<div title="首页" style="padding:10px">
			<center>
				<div style="height: 200px"></div>
				<span style="color: red; font-size: 50px;font-weight: bold;font-family: '楷体'">微&nbsp;信&nbsp;公&nbsp;众&nbsp;号&nbsp;后&nbsp;台</span>
			</center>
		</div>
		</div>
			
			
			
			
	</div>
</body>
</html>