<%--
  Created by IntelliJ IDEA.
  User: limboZ
  Date: 2017/9/28
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/content/taglib.jsp" %>
<html>
<head>
    <title>我的申请</title>
</head>
<body>
    <div class="x_panel">
        <div class="x_title">
            <h2>我的申请</h2>
            <ul class="nav navbar-right panel_toolbox">
                <li>
                    <%--<a href="${ctx}/user/add" class="btn btn-info"><i class="fa fa-plus"></i> 创建出访申请</a>--%>
                    <button id="addApply" class="btn btn-info"><i class="fa fa-plus"></i> 创建出访申请</button>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="bg-danger">
                1.只有申请审批完成后才能填写出访行程，当外事办把出访总结提交审批后，则不能再修改行程
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="30%">出访团名称</th>
                        <th width="10%">申请创建时间</th>
                        <th width="10%">申请审批状态</th>
                        <th width="7%">出访总结</th>
                        <th width="10%">总结审批状态</th>
                        <th width="8%">填写总结</th>
                        <th width="20%">操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${applyPreviewListDTO}" var="item" varStatus="no">
                    <tr>
                        <td>${no.count}</td>
                        <td>${item.teamName}</td>
                        <td><fmt:formatDate value="${item.applyCreateTime}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                        <td>${item.applyExamineStatus}</td>
                        <td>${item.isFilledReport}</td>
                        <td>${item.reportExamineStatus}</td>
                        <td>
                            <%--<c:if test="${item.applyExamineStatus eq 'COMPLETE'}">--%>
                                <button class="btn btn-xs btn-info" data-toggle="modal" data-target="#summary" onclick="pullSummary(${item.id})">填写</button>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.applyExamineStatus != 'COMPLETE'}">等待中</c:if>--%>
                        </td>
                        <td>
                            <a href="${ctx}/user/show/${item.id}" class="btn btn-xs btn-primary">详情</a>
                            <c:if test="${item.totalStatus eq 'COMPLETE'}">
                                <a href="#" class="btn btn-xs btn-success">提交审批</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8">
                        <tags:pagination pagination="${pageCommand}" currentPage="${currentPage}" count="${count}" />
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 科室详情模态框（Modal） -->
    <div class="modal fade" id="summary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog" style="width:60%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        填写出访总结
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="bg-danger">
                        1.出访要做时间排序<br>
                        2.出访总结1000字以内
                    </div>
                    <div class="x_panel">
                        <div class="x_content">
                            <h2>出访总结列表</h2>
                            <div class="ln_solid"></div>
                            <table class="table table-striped sortSummary" id="summaryList">
                                <thead>
                                <tr>
                                    <th width="12%">日期</th>
                                    <th width="12%">时间</th>
                                    <th>行程描述</th>
                                    <th width="12%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="" var="item">
                                        <tr>
                                            <td class="tDate"></td>
                                            <td class="tTime"></td>
                                            <td class="tDetail"></td>
                                            <td>
                                                <button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <button class="btn btn-info" id="addButton">新增行程</button>
                            <%--<button type="button" class="btn btn-primary ">新增行程</button>--%>
                            <div id="add">
                                <h2>新增行程</h2>
                                <div class="ln_solid"></div>
                                <form class="form-horizontal form-label-left">
                                    <input type="hidden" id="id" >
                                    <div class="form-group">
                                        <label class="control-label col-sm-2 col-xs-12">日期<span class="required">*</span>
                                        </label>
                                        <div class="col-sm-8 col-xs-12">
                                            <input type="text" class="Wdate form-control" readonly id="date" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'\'2099-12-31\''})">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2 col-xs-12">时间<span class="required">*</span>
                                        </label>
                                        <div class="col-sm-8 col-xs-12">
                                            <select class="form-control" id="time">
                                                <option value="">请选择</option>
                                                <option value="上午">上午</option>
                                                <option value="下午">下午</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2 col-xs-12">行程描述<span class="required">*</span>
                                        </label>
                                        <div class="col-sm-8 col-xs-12">
                                           <textarea rows="4" class="form-control" id="detail"></textarea>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <button type="button" id="addVisit" class="btn btn-success pull-right">新增</button>
                                </form>
                            </div>
                            <div class="ln_solid"></div>
                            <form class="form-horizontal form-label-left">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 col-xs-12">出访总结<span class="required">*</span>
                                    </label>
                                    <div class="col-sm-8 col-xs-12">
                                        <textarea rows="10" class="form-control" id="summaryC" maxlength="1000"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" id="submitSummary">保存</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <script src="${ctx}/assets/build/js/jquery.min.js"></script>
    <script src="${ctx}/assets/build/js/sort.summary.js"></script>
    <script>
        $('#add').hide();
        //拉取该条申请的出访总结
        function pullSummary(id) {
            $('#id').val(id);
            if(!isEmpty(id)){
                $.ajax({
                    url:'${ctx}/user/report/' + id,
                    success:function (data) {
                        if(data.code == 0){
                            for(var i=0;i<data.data.reports.length;i++){
                                var op = '<tr><td class="tDate">' + data.data.reports[i].reportDate + '</td>';
                                op += '<td class="tTime">' + data.data.reports[i].reportSlot + '</td>';
                                op += '<td class="tDetail">' + data.data.reports[i].content + '</td>';
                                op += '<td><button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button></td>';
                                $('#summaryList').append(op);
                            }
                        }else {
                            alert(data.tip);
                        }
                    }
                });
            }
        }
        $('#addApply').click(function () {
            window.location.href = '${ctx}/user/add';
        });
        //新增行程
        $('#addVisit').click(function () {
            var date = $('#date').val();
            var time = $('#time').val();
            var detail = $('#detail').val();
            var message = '';
            var flag = true;
            if(isEmpty(date)){
                message += '请填写日期!';
            }
            if(isEmpty(time)){
                message += '请填写时间！';
            }
            if(isEmpty(detail)){
                message += '请填写行程描述！';
            }
            if(isEmpty(message)){
                //判断出访总结是否超过1000字
                if(detail.length > 1000){
                    alert('出访总结最多1000字！');
                }else {
                    //判断该时间是否已存在相应的总结
                    $('.sortSummary').find('.tDate').each(function () {
                        var obj = $(this);
                        if(obj.text() == date){
                            obj.parent().find('.tTime').each(function () {
                                if($(this).text() == time){
                                    alert('该时间的描述已填写！');
                                    flag = false;
                                    return false;
                                }
                            })
                        }
                    })
                    if(flag){
                        var op = '<tr><td class="tDate">' + date + '</td>';
                        op += '<td class="tTime">' + time + '</td>';
                        op += '<td class="tDetail">' + detail + '</td>';
                        op += '<td><button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button></td>';
                        $('#summaryList').append(op);
                        $('#date').val('');
                        $('#time').val('');
                        $('#detail').val('');
                        $('#add').hide();
                    }
                }
            }else {
                alert(message);
            }
        });
        //移除一条总结
        $('body').on('click','.remove',function () {
            $(this).parents('tr:first').remove();
        });
        //提交总结
        $('#submitSummary').click(function () {
           //遍历所有总结
            var summary = $('#summaryC').val();
            //存储行程
            var tripList = [];
            if(isEmpty(summary)){
                alert('请填写出访总结！');
            }else {
                if(summary.length > 1000){
                    alert('出访总结不能超过1000字！');
                }else {
                    var id = $('#id').val();
                    var content = {};
                    content.content = summary;
                    content.reportType = 'FINAL';
                    tripList.push(content);
                    //遍历行程
                    var list = $('#summaryList tbody tr');
                    if(list.length > 0){
                        $(list).each(function () {
                            var one = {};
                            one.reportDate = $(this).find('.tDate').text();
                            one.reportSlot = $(this).find('.tTime').text();
                            one.content = $(this).find('.tDetail').text();
                            one.reportType = 'TRIP';
                            tripList.push(one);
                        });
//                        console.log(tripList);
                        var report = {};
                        report.applyId = id;
                        report.reports = tripList;
                        //ajax提交数据
                        $.ajax({
                            url:'${ctx}/user/submitReport',
                            method:'post',
                            data:JSON.stringify(report),
                            dataype:'json',
                            contentType:"application/json",
                            success:function (data) {
                                alert(data.tip);
                                if(data.code == 0){
                                    location.href = '${ctx}/user/show/' + id;
                                }
                            },
                            error:function (data) {
                                alert('连接服务器错误，请刷新页面后重试！');
                            }
                        });
                    }else {
                        alert('请填写出访行程！');
                    }
                }
            }
        });
        $('#addButton').click(function () {
           $('#add').show();
        });
    </script>
</body>
</html>
