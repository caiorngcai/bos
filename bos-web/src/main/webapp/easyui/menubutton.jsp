<%--Created by crc  Date: 2017/10/30    Time: 14:32--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>menubutton</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<!-- 制作菜单 -->
<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>

<!-- 使用div元素制作下拉菜单 -->
<div id="mm">
    <div onclick="alert(1111)" data-options="iconCls:'icon-edit'">修改密码</div>
    <div>联系管理员</div>
    <div class="menu-sep"></div>
    <div>退出系统</div>
</div>
</body>
</html>
