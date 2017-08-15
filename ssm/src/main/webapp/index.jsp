<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>测试接收JSON格式的数据</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/json2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btn").click(testRequestBody);
        });
        function testRequestBody() {
            $.ajax("${pageContext.request.contextPath}/json/testRequestBody",// 发送请求的URL字符串。
                {
                    dataType: "json",
                    type: "post",
                    contentType: "application/json",
                    data: JSON.stringify({"id": 1, "name": "Spring MVC学习"}),
                    async: true,
                    success: function (data) {
                        console.log(data);
                        $("#id").html(data.id);
                        $("#name").html(data.name);
                        $("#author").html(data.author);
                    },
                    error: function () {
                        alert("数据发送失败");
                    }
                });
        }
    </script>
</head>
<body>
<button id="btn">点击</button>
<br>
编号：<span id="id"></span><br>
书名：<span id="name"></span><br>
作者：<span id="author"></span><br>
</body>
</html>