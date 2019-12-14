<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFrompPush"></div>

<%--<c:url>标签将URL格式化为一个字符串，然后存储在一个变量中。--%>
<%--这个标签在需要的时候会自动重写URL。--%>
<%--var属性用于存储格式化后的URL。--%>
<%--<c:url>标签只是用于调用response.encodeURL()方法的一种可选的方法。它真正的优势在于提供了合适的URL编码，包括<c:param>中指定的参数。--%>

<%--<script type="text/javascript" src="<c:url value="assets/js/jquery-3.4.1.min.js"/>"></script>--%>
<script type="text/javascript" src="assets/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    if(!!window.EventSource){ //1 EventSource对象只有新式的浏览器才有（Chrome，Firefox）等，EvnetSource是SSE的客户端
        var source = new EventSource('push');
        var s='';
        //2 添加SSE客户端监听，在此获得服务器端推送的消息
        source.addEventListener('message',function (e) {
            s+=e.data+"<br/>";
            $("#msgFrompPush").html(s)
        });

        source.addEventListener('open',function (e) {
            console.log('连接打开.');
        },false);

        source.addEventListener('error',function (e) {
            if (e.readyState==EventSource.CLOSED){
                console.log('连接关闭');
            }else{
                console.log(e.readyState);
            }
        },false);
    }else{
        console.log('您的浏览器不支持SSE');
    }
</script>
</body>
</html>