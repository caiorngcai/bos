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
<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>小明</td>
				<td>90</td>
			</tr>
			<tr>
				<td>002</td>
				<td>老王</td>
				<td>3</td>
			</tr>
		</tbody>
	</table>
	
	<hr>
	<!-- 方式二：发送ajax请求获取json数据创建datagrid -->
	<table data-options="url:'${pageContext.request.contextPath }/json/datagrid_data.json'" 
			class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
	</table>
	
	
	<hr>
	<table id="myTable"></table>
	
	<script type="text/javascript">
		$(function()
		{
			$("#myTable").datagrid({
				columns:[[
				          {title:'编号',field:'id',checkbox:true},
				          {title:'姓名',field:'name'},
				          {title:'年龄',field:'age'}
				          ]],
			url:'${pageContext.request.contextPath}/json/datagrid_data.json',
			rownumbers:true,
			singleSelect:true,
			toolBar:[
			         {text:'添加',iconCls:'icon-add',
			        	handler:function()
			        	{
			        		alert("add...");
			        	}
			         },
			         {text:'删除',iconCls:'icon-remove'},
			         {text:'修改',iconCls:'icon-edit'},
			         {text:'查询',iconCls:'icon-search'} 
			         ],
			  pagination:true,
			  pageList:[1,3,5,7,9,11]
			})	;
		});
	</script>
	
</body>
</html>