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
<style type="text/css" media=print>
    .noprint{display : none }
</style>
<div class="x_panel">
    <div class="x_title">
        <h2>出访团组信息</h2>
        <div class="pull-right">
            <c:if test="${applyDetailDTO.totalStatus eq 'DRAFT'}">
                <a href="${ctx}/user/edit/${applyDetailDTO.id}" class="btn btn-info noprint">编辑</a>
            </c:if>
            <button type="button" class="btn btn-success noprint" id="submitApply">提交审批</button>
            <c:if test="${applyDetailDTO.totalStatus eq 'COMPLETE'}">
                <button type="button" class="btn btn-primary noprint" id="print" >打印表单</button>
            </c:if>
            <%--<button type="button" class="btn btn-primary noprint" id="printArea" >打印表单</button>--%>
            <button type="button" class="btn btn-warning noprint" onclick="history.back()">返回</button>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="x_content" id="content">
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
                    <th width="20%">团组名称</th><td colspan="3">${applyDetailDTO.teamName}</td>
                </tr>
                <tr>
                    <th width="20%">团组申请人</th><td>${applyDetailDTO.applyUserName}</td>
                </tr>
                <tr>
                    <th width="20%">任务类型</th><td>${applyDetailDTO.commissionType}</td>
                </tr>
                <tr>
                    <th width="20%">预计出访时间</th><td colspan="3"> <b id="startTime"><fmt:formatDate value="${applyDetailDTO.startTime}" pattern="yyyy-MM-dd"></fmt:formatDate></b> 至 <b id="endTime"><fmt:formatDate value="${applyDetailDTO.endTime}" pattern="yyyy-MM-dd"></fmt:formatDate></b> , 共计 <b id="days"></b> 天</td>
                </tr>
                <tr>
                    <th width="20%">出访事由</th><td colspan="3">${applyDetailDTO.reason}</td>
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
                    <c:forEach items="${applyDetailDTO.destinations}" var="item">
                        <tr>
                            <th>国家</th><td>${item.nation}</td><th>城市</th><td>${item.destination}</td>
                        </tr>
                    </c:forEach>
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
                    <c:forEach var="item" items="${applyDetailDTO.teamMates}">
                        <tr>
                            <th>工号</th><td>${item.employeeId}</td>
                            <th>姓名</th><td>${item.employeeName}</td>
                            <th>部门</th><td>${item.employeeDept}</td>
                            <th>职位</th><td>${item.employeePost}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${applyDetailDTO.totalStatus != 'DRAFT' && applyDetailDTO.totalStatus != 'UN_CONFIG' && applyDetailDTO.totalStatus != 'CONFIGURED_NOT_SUBMIT'}">
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
                    <c:forEach items="${applyDetailDTO.applyExamineProgresses}" var="item">
                        <tr>
                            <td>${item.advise}</td>
                            <td>${item.ret}</td>
                            <td>${item.examinePeopleName}</td>
                            <td>${item.result}</td>
                            <td>${item.passTime}</td>
                        </tr>
                    </c:forEach>
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
                    <c:forEach var="item" items="${applyDetailDTO.reports}">
                        <c:if test="${item.reportType eq 'TRIP'}">
                            <tr>
                                <td>${item.reportDate}</td>
                                <td>${item.reportSlot}</td>
                                <td>${item.content}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
                <h2 class="green">总结</h2>
                <textarea class="form-control" rows="7" readonly>
                    <c:forEach var="item" items="${applyDetailDTO.reports}">
                        <c:if test="${item.reportType eq 'FINAL'}">
                            ${item.content}
                        </c:if>
                    </c:forEach>
                </textarea>
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
                    <c:forEach items="${applyDetailDTO.reportExamineProgresses}" var="item">
                        <tr>
                            <td>${item.advise}</td>
                            <td>${item.ret}</td>
                            <td>${item.examinePeopleName}</td>
                            <td>${item.result}</td>
                            <td>${item.passTime}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </c:if>
    </div>
</div>
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<script src="${ctx}/assets/vendors/jquery.print/jquery.PrintArea.js"></script>
<script src="${ctx}/assets/build/js/mine.js"></script>
<script>
    $('.normal').addClass('active');
    $('.normal ul').show();
    $('.normal ul .home').addClass('current-page');
    $('#days').text(DateDiff($('#startTime').text(),$('#endTime').text()) + 1);
    $('#print').click(function () {
       window.print();
    });
    //提交审批
    $('#submitApply').click(function () {
       $.ajax({
           url:'${ctx}/user/submitApply/${applyDetailDTO.id}',
           method:'post',
           success:function (data) {
               alert(data.tip);
               if(data.code == 0){
                   location.reload();
               }
           },
           error:function () {
               alert('提交审核错误！');
           }
       })
    });
</script>
</body>
</html>
