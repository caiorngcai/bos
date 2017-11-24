<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>datagrid</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
		
	<table id="myTable"></table>
	
	<script type="text/javascript">
		$(function()
		{
			var myIndex = -1;
			$("#myTable").datagrid({
				columns:[[
				          {title:'编号',field:'id',checkbox:true},
				          {width:150,title:'姓名',field:'name',editor:{
				        	  								type:'validatebox',
				        	  								options:{}
				          								   }},
				          {width:150,title:'年龄',field:'age',editor:{
								type:'numberbox',
  								options:{}
								   }},
				          {width:150,title:'日期',field:'address',editor:{
								type:'datebox',
  								options:{}
								   }}
				          ]],
			url:'${pageContext.request.contextPath}/json/datagrid_data.json',
			rownumbers:true,
			singleSelect:true,
			toolbar:[
			         {text:'添加',iconCls:'icon-add',
			        	handler:function()
			        	{
			        		$("#myTable").datagrid("insertRow",{
			        			index:0,
			        			row:{}
			        		});
			        		$("#myTable").datagrid("beginEdit",0);
			        	}
			         },
			         {text:'删除',iconCls:'icon-remove',
			        	 handler:function()
			        	 {
			        		var rows=$("#myTable").datagrid("getSelections");
			        		
			        		if(rows.length==1)
			        			{
			        				var row=rows[0];
			        				 myIndex = $("#myTable").datagrid("getRowIndex",row);
			        				 alert(myIndex);
			        			}
			        		 $("#myTable").datagrid("deleteRow",myIndex);
			        	 }
			         },
			         {text:'修改',iconCls:'icon-edit' ,handler:function(){
				        	 //获得选中的行对象
				        	 var rows = $("#myTable").datagrid("getSelections");
				        	 if(rows.length == 1){
				        		 var row = rows[0];
				        		 //获得指定行对象的索引
				        		 myIndex = $("#myTable").datagrid("getRowIndex",row);
				        	 }
				        	 $("#myTable").datagrid("beginEdit",myIndex);
				         }},
			         {text:'查询',iconCls:'icon-search'} 
			         ],
			  pagination:true,
			  pageList:[1,3,5,7,9,11],
			onAfterEdit:function(index,data,changes){
				console.info(data);
				$.post();
			}
			})	;
		});
	</script>
	
</body>
</html>