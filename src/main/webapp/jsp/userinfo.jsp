<%--
  Created by IntelliJ IDEA.
  User: 12436
  Date: 2018/8/9
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户信息</title>
</head>
<body>

用户头像:<img src="${userinfo.headimgurl}" alt=""> <br>
昵称:${userinfo.nickname} <br>
openID:${userinfo.openid} <br>
国家:${userinfo.county} <br>
省份:${userinfo.province} <br>
城市:${userinfo.city} <br>

</body>
</html>
