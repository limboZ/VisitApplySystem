<%--
  Created by IntelliJ IDEA.
  User: limboZ
  Date: 2017/9/29
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/content/taglib.jsp" %>
<html>
<head>
    <title>出访申请详情</title>
</head>
<body>
<div class="x_panel">
    <div class="x_title">
        <h2>出访团组信息</h2>
        <div class="pull-right">
            <a href="${ctx}/user/edit" class="btn btn-info">编辑</a>
            <button type="button" class="btn btn-success">提交审批</button>
            <button type="button" class="btn btn-primary">打印表单</button>
            <button type="button" class="btn btn-warning" onclick="history.back()">返回</button>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="bg-danger">
            1.审批流程：用户填写申请-总经办管理员在系统中审核-部门领导及公司领导审批流程只显示领导审批，不显示总经办审核<br/>
            2.出访申请审批环节 ,用户在总工部外事办提交审批前可以对出访申请中的内容进行编辑,总工部外事办提交审批后则不能修改<br/>
                出访记录审批环节,用户在总工部外事办提交审批前对出访记录的环节进行编辑总工部外事办提交审批后则不能修改<br/>
            3.用户提交审批后将没有保存并提交审批按钮,在申请审批完成后出现打印表单按钮进行打印当前页面<br/>
        </div>
        <div class="bg-info">
            1.编辑时可编辑那些字段？
            2.审批流程中：部门领导及公司领导审批流程只显示领导审批，不显示总经办审核，什么意思？
        </div>
        <table class="table table-th table-bordered" style="width: 80%">
            <tbody>
                <tr>
                    <th>团组名称</th><td colspan="3">${apply.teamName}</td>
                </tr>
                <tr>
                    <th>团组申请人</th><td>${apply.applyUserId}</td>
                </tr>
                <tr>
                    <th>任务类型</th><td>${apply.commissionType}</td>
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
                <table class="table table-th table-bordered" style="width: 60%">
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
                        <th>职位</th>
                        <td>软件研发经理</td>
                    </tr>
                    <tr>
                        <th>工号</th>
                        <td>015074</td>
                        <th>姓名</th>
                        <td>邹江华</td>
                        <th>部门</th>
                        <td>信息服务部</td>
                        <th>职位</th>
                        <td>软件研发员</td>
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
                <h3 class="red">审批中</h3>
                <table class="table table-th ">
                    <thead>
                    <tr>
                        <th>审批意见</th>
                        <th>审批结果</th>
                        <th>审批人</th>
                        <th>审批状态</th>
                        <th>审批日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>统一</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
                    </tr>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>统一</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
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
                <h2 class="green">行程</h2>
                <table class="table table-th table-bordered" style="width: 80%">
                    <thead>
                    <tr>
                        <th>日期</th>
                        <th>时间</th>
                        <th>行程描述</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>2017-10-01</td>
                        <td>上午</td>
                        <td>今天去XXX，做了什么事</td>
                    </tr>
                    <tr>
                        <td>2017-10-02</td>
                        <td>下午</td>
                        <td>今天去XXX，做了什么事</td>
                    </tr>
                    </tbody>
                </table>
                <h2 class="green">总结</h2>
                <textarea class="form-control" rows="7" readonly>ss</textarea>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="summaryCheck"  href="#summaryCheckC" aria-expanded="true" aria-controls="summaryCheckC">
                <h4 class="panel-title">出访总结审批</h4>
            </div>
            <div class="panel-body" id="summaryCheckC" role="tabpanel" aria-labelledby="summaryCheck">
                <h3 class="red">审批中</h3>
                <table class="table table-th ">
                    <thead>
                    <tr>
                        <th>审批意见</th>
                        <th>审批结果</th>
                        <th>审批人</th>
                        <th>审批状态</th>
                        <th>审批日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>统一</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
                    </tr>
                    <tr>
                        <td>建议xxxxxxxxxx</td><td>统一</td><td>张三</td><td>审批通过</td><td>2017-10-02</td>
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
</div>
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<script>
    $('.normal').addClass('active');
    $('.normal ul').show();
    $('.normal ul .home').addClass('current-page');
</script>
</body>
</html>
