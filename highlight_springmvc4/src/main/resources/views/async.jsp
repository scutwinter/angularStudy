<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>servlet async support</title>
</head>
<body>
<div id="msgid"></div>
<script type="text/javascript" src="assets/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    var s='';
    deferred(); //1 页面打开就向后台发送请求
    function deferred() {
        $.get('defer',function (data) {
            s+=data+"<br/>";
            console.log(data);  //2 在控制台输出服务端推送的数据
            $("#msgid").html(s)
            deferred(); // 一次请求完成后再向后台发送请求
        })
    }
</script>
</body>
</html>