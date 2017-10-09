<%--
  Created by IntelliJ IDEA.
  User: limboZ
  Date: 2017/6/19
  Time: 下午4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/content/taglib.jsp" %>
<%@ page import="cn.com.scal.components.utils.Consts" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <sitemesh:write property='title' ></sitemesh:write>
    </title>

    <!--注意css文件引用的顺序，如果线引入custom.min.css,再引入bootstrap.min.css，则浏览器解析不一样，因为这两个css文件会有部分的样式重叠，请自己选择相关样式-->

    <!-- Bootstrap -->
    <link href="${ctx}/assets/build/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${ctx}/assets/build/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${ctx}/assets/build/css/custom.min.css" rel="stylesheet">
    <!-- 自定义css样式，满足某些特定需求 -->
    <link href="${ctx}/assets/build/css/mine.css" rel="stylesheet">
    <style>
        .error{
            color:red;
            padding-left: 14px;
            /*padding-bottom: 2px;
            font-weight: bold;*/
        }
        label.valid {
            color:#31b0d5;
        }
    </style>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="${ctx}/user/list" class="site_title">
                        <!-- <i class="fa fa-paw"></i>  -->
                        <img src="${ctx}/assets/images/Logo_scal.png">
                        <span>出访申请系统</span>
                    </a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="${ctx}/assets/images/user.png" alt="人物图片" class="img-circle profile_img" style="height:57px;">
                    </div>
                    <div class="profile_info">
                        <span>欢迎您,</span>
                        <%--<h2>${sessionScope.current_user.userName}</h2>--%>
                        <h2>邹江华</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->

                <br />

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <ul class="nav side-menu">
                            <%--<c:if test="${sessionScope.current_user.role == 'USER'}">--%>
                                <li class="normal">
                                    <a><i class="fa fa-home"></i> 出访申请 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li class="home"><a href="${ctx}/user/list">我的申请</a></li>
                                    </ul>
                                </li>
                            <%--</c:if>--%>
                            <%--<c:if test="${sessionScope.current_user.role == 'MANAGER'}">--%>
                                <li class="manager">
                                    <a><i class="fa fa-gears"></i> 出访管理 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li class="home"><a href="${ctx}/manager/list">申请审核</a></li>
                                    </ul>
                                </li>
                            <%--</c:if>--%>
                        </ul>
                    </div>
                </div>
                <!-- /sidebar menu -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="${ctx}/assets/images/boy.jpeg" alt="">${sessionScope.current_user.userName}
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <%--<li><a href="javascript:;"> 个人信息</a></li>--%>
                                <%--<li>--%>
                                    <%--<a href="javascript:;">--%>
                                        <%--<span>修改密码</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <li><a href="${ctx}/logout"><i class="fa fa-sign-out pull-right"></i>注销</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <!-- top tiles -->
            <div class="clearfix"></div>
            <div class="row">
                <!--在此编写页面代码-->
                <sitemesh:write property='body'/>
                <!--在此编写页面代码-->
            </div>
            <!-- /top tiles -->
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                四川航空股份有限公司---出访申请系统 v1.0
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>
<%--//存储提示信息--%>
<input type="hidden" value="${message}" id="message">
<%--&lt;%&ndash;遮罩背景层&ndash;%&gt;--%>
<%--<div id="warning" class="loading-div">--%>
    <%--<div class="inner">--%>
        <%--<img src="${ctx}/assets/images/loader.gif">--%>
    <%--</div>--%>
<%--</div>--%>
<!-- jQuery -->
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${ctx}/assets/build/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/build/js/mine.js"></script>
<!-- Custom Theme Scripts -->
<%--<script src="${ctx}/assets/build/js/custom.min.js"></script>--%>
<script src="${ctx}/assets/build/js/custom.js"></script>
<!--日历控件,myDatePicker97-->
<script src="${ctx}/assets/vendors/calender/My97DatePicker/WdatePicker.js"></script>
    <script>
        var message = $('#message').val();
        if(message != ''){
            alert('${message}');
            $('#message').val('');
        }
        $('#warning').hide();
    </script>
</body>
</html>

