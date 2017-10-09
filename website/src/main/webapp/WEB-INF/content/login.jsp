<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/content/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>欢迎使用出访申请系统 </title>

    <!-- Bootstrap -->
    <link href="${ctx}/assets/build/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${ctx}/assets/build/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${ctx}/assets/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
<div>

    <div class="login_wrapper">
        <div class="animate form login_form ">
            <section class="login_content">
                <form action="${ctx}/login" method="post">
                    <h1>登录</h1>
                    <div>
                        <input type="text" name="empNo" class="form-control" placeholder="工号" required />
                    </div>
                    <div>
                        <input type="password" name="password" class="form-control" placeholder="密码" required />
                    </div>
                    <div>
                        <%--<a class="btn btn-default submit" href="${ctx}/home?user=normal">登录</a>--%>
                        <button type="submit" class="btn btn-default submit">登录</button>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <br />
                        <div>
                            <h1><img src="${ctx}/assets/images/Logo_scal.png" /> 出访申请系统</h1>
                            <p>版本号：V1.0.0</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<c:if test="${not empty message}">
    <script>
        alert('${message}');
    </script>
</c:if>
</body>
</html>
