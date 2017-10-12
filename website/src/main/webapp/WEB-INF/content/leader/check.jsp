<%--
  Created by IntelliJ IDEA.
  User: limboZ
  Date: 2017/10/11
  Time: 08:44
  To change this template use File | Settings | File Templates.
  领导通过OA中的url跳转至本页面，对出访申请和出访总结进行审批
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/content/taglib.jsp" %>
<html>
<head>
    <title>领导审批</title>
    <!-- Bootstrap -->
    <link href="${ctx}/assets/build/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <%--<link href="${ctx}/assets/build/css/custom.min.css" rel="stylesheet">--%>
    <!-- 自定义css样式，满足某些特定需求 -->
    <link href="${ctx}/assets/build/css/mine.css" rel="stylesheet">
    <%--//解决ie8兼容问题--%>
    <!--[if lt IE 9]>
        <script src="https://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.js"></script>
    <![endif]-->
</head>
<body style="background: #EDEDED;">
    <div style="margin:30px auto;width:60%;background: white;padding: 20px;">
        <table class="table table-th table-bordered">
            <tbody>
            <tr>
                <th>团组名称</th><td colspan="3">关于黄龙翔等5人去新加坡访问的申请</td>
            </tr>
            <tr>
                <th>团组申请人</th><td>黄龙翔</td>
            </tr>
            <tr>
                <th>任务类型</th><td>境外培训</td>
            </tr>
            <tr>
                <th>预计出访时间</th><td colspan="3"> 2017-09-12 至 2017-10-09 , 共计 20 天</td>
            </tr>
            <tr>
                <th>出访事由</th><td colspan="3">啊啊哈哈哈哈哈哈哈哈哈</td>
            </tr>
            </tbody>
        </table>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="destination"  href="#destinationC" aria-expanded="true" aria-controls="destinationC">
                <h4 class="panel-title">目的地信息</h4>
            </div>
            <div class="panel-body" id="destinationC" role="tabpanel" aria-labelledby="destination">
                <table class="table table-th table-bordered" style="width: 80%">
                    <tbody>
                    <tr>
                        <th>国家</th><td>美国</td><th>城市</th><td>华盛顿</td>
                    </tr>
                    <tr>
                        <th>国家</th><td>日本</td><th>城市</th><td>东京</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="visitors"  href="#visitorsC" aria-expanded="true" aria-controls="visitorsC">
                <h4 class="panel-title">出访团组人员信息</h4>
            </div>
            <div class="panel-body" id="visitorsC" role="tabpanel" aria-labelledby="visitors">
                <table class="table table-th table-bordered" style="width: 80%">
                    <tbody>
                    <tr>
                        <th>工号</th>
                        <td>007955</td>
                        <th>姓名</th>
                        <td>猴小翔</td>
                        <th>部门</th>
                        <td>信息服务部</td>
                    </tr>
                    <tr>
                        <th>工号</th>
                        <td>015074</td>
                        <th>姓名</th>
                        <td>邹江华</td>
                        <th>部门</th>
                        <td>信息服务部</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="applyCheck"  href="#applyCheckC" aria-expanded="true" aria-controls="applyCheckC">
                <h4 class="panel-title">出访申请审批</h4>
            </div>
            <div class="panel-body" id="applyCheckC" role="tabpanel" aria-labelledby="applyCheck">
                <h3 style="color:green;">审批完成</h3>
                <table class="table table-th ">
                    <thead>
                    <tr>
                        <th>审批建议</th>
                        <th>审批结果</th>
                        <th>审批人</th>
                        <th>审批状态</th>
                        <th>审批日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>同意</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
                    </tr>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>同意</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
                    </tr>
                    <tr>
                        <td></td><td></td><td>张三</td><td>待审批</td><td></td>
                    </tr>
                    <tr>
                        <td></td><td></td><td>张三</td><td>待审批</td><td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="summary"  href="#summaryC" aria-expanded="true" aria-controls="summaryC">
                <h4 class="panel-title">出访总结</h4>
            </div>
            <div class="panel-body" id="summaryC" role="tabpanel" aria-labelledby="summary">
                <table class="table table-striped sortSummary table-th" id="summaryList">
                    <thead>
                    <tr>
                        <th width="12%">日期</th>
                        <th width="12%">时间</th>
                        <th>出访总结</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="tDate">
                            2017-09-23
                        </td>
                        <td class="tTime">
                            上午
                        </td>
                        <td>
                            今天去了日本东京，看了富士山脚下的樱花...
                        </td>
                    </tr>
                    <tr>
                        <td class="tDate">
                            2017-09-23
                        </td>
                        <td class="tTime">
                            下午
                        </td>
                        <td>
                            1234567890 * 1024
                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    </tfoot>
                </table>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="summaryCheck"  href="#summaryCheckC" aria-expanded="true" aria-controls="summaryCheckC">
                <h4 class="panel-title">出访总结审批</h4>
            </div>
            <div class="panel-body" id="summaryCheckC" role="tabpanel" aria-labelledby="summaryCheck">
                <h3 style="color:red;">审批中</h3>
                <table class="table table-th ">
                    <thead>
                    <tr>
                        <th>审批建议</th>
                        <th>审批结果</th>
                        <th>审批人</th>
                        <th>审批状态</th>
                        <th>审批日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>同意</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
                    </tr>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>同意</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
                    </tr>
                    <tr>
                        <td></td><td></td><td>张三</td><td>待审批</td><td></td>
                    </tr>
                    <tr>
                        <td></td><td></td><td>张三</td><td>待审批</td><td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<%--遮罩背景层--%>
<div id="warning" class="loading-div">
    <div class="inner">
        <img src="${ctx}/assets/images/loader.gif">
    </div>
</div>
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<script src="${ctx}/assets/build/js/sort.summary.js"></script>
</body>
</html>
