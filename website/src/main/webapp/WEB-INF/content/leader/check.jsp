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
</head>
<body>
    <div>
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
                        <%--<th>职位</th>--%>
                        <%--<td>软件研发经理</td>--%>
                    </tr>
                    <tr>
                        <th>工号</th>
                        <td>015074</td>
                        <th>姓名</th>
                        <td>邹江华</td>
                        <th>部门</th>
                        <td>信息服务部</td>
                        <%--<th>职位</th>--%>
                        <%--<td>软件研发员</td>--%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="applyProcess"  href="#applyProcessC" aria-expanded="true" aria-controls="applyProcessC">
                <h4 class="panel-title">出访申请审批流程</h4>
            </div>
            <div class="panel-body" id="applyProcessC" role="tabpanel" aria-labelledby="applyProcess">
                <table class="table table-th " id="applyCheckTable">
                    <thead>
                    <tr>
                        <th>OA号</th>
                        <th>姓名</th>
                        <th>职位</th>
                        <%--<th>操作</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>007955</td>
                        <td>张三</td>
                        <td>经理</td>
                        <%--<td>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i>下移</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs remove">删除</button>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td>007955</td>
                        <td>李四</td>
                        <td>部门领导</td>
                        <%--<td>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i>下移</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs remove">删除</button>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td>007955</td>
                        <td>王五</td>
                        <td>部门领导</td>
                        <%--<td>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i>下移</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs remove">删除</button>--%>
                        <%--</td>--%>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr id="checkForm">
                        <td>
                            <input type="text" class="form-control empNo" >
                        </td>
                        <td>
                            <input type="text" class="form-control userName" >
                        </td>
                        <td>
                            <input type="text" class="form-control job" >
                        </td>
                        <td>
                            <button type="button" class="btn btn-success" id="addCheck">添加</button>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                <%--<button type="button" class="btn btn-info" id="toAddChecker">新增审批人员</button>--%>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="applyCheck"  href="#applyCheckC" aria-expanded="true" aria-controls="applyCheckC">
                <h4 class="panel-title">出访申请审批</h4>
            </div>
            <div class="panel-body" id="applyCheckC" role="tabpanel" aria-labelledby="applyCheck">
                <h3 class="green">审批完成</h3>
                <table class="table table-th ">
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
                        <%--<th width="20%">操作</th>--%>
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
                        <%--<td>--%>
                        <%--<button type="button"  class="btn btn-info btn-xs">编辑</button>--%>
                        <%--<button type="button"  class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button>--%>
                        <%--</td>--%>
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
                        <%--<td>--%>
                        <%--<button type="button"  class="btn btn-info btn-xs">编辑</button>--%>
                        <%--<button type="button"  class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button>--%>
                        <%--</td>--%>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr id="summaryForm">
                        <td>
                            <input type="text" class="Wdate form-control" readonly id="date" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'\'2099-12-31\''})" style="margin:0">
                        </td>
                        <td>
                            <select class="form-control" id="time">
                                <option value="">请选择</option>
                                <option value="上午">上午</option>
                                <option value="下午">下午</option>
                            </select>
                        </td>
                        <td>
                            <textarea rows="5" class="form-control" id="detail"></textarea>
                        </td>
                        <td>
                            <button type="button" class="btn btn-success" id="addSummary">添加</button>
                            <button type="button" class="btn btn-warning">取消</button>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                <%--<button type="button" id="toAddSummary" class="btn btn-info">新增行程</button>--%>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="summaryProcess"  href="#summaryProcessC" aria-expanded="true" aria-controls="summaryProcessC">
                <h4 class="panel-title">出访总结审批流程</h4>
            </div>
            <div class="panel-body" id="summaryProcessC" role="tabpanel" aria-labelledby="summaryProcess">
                <table class="table table-th" id="summaryCheckTable">
                    <thead>
                    <tr>
                        <th>OA号</th>
                        <th>姓名</th>
                        <th>职位</th>
                        <%--<th>操作</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>007955</td>
                        <td>张三</td>
                        <td>经理</td>
                        <%--<td>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i> 下移</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs remove">删除</button>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td>007955</td>
                        <td>李四</td>
                        <td>部门领导</td>
                        <%--<td>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i> 下移</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs remove">删除</button>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td>007955</td>
                        <td>王五</td>
                        <td>部门领导</td>
                        <%--<td>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>--%>
                        <%--<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i> 下移</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs remove">删除</button>--%>
                        <%--</td>--%>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr id="checkerForm">
                        <td>
                            <input type="text" class="form-control empNo" >
                        </td>
                        <td>
                            <input type="text" class="form-control userName" >
                        </td>
                        <td>
                            <input type="text" class="form-control job" >
                        </td>
                        <td>
                            <button type="button" class="btn btn-success" id="addChecker">添加</button>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                <%--<button type="button" class="btn btn-info" id="toAddSummaryChecker">新增审批人员</button>--%>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse" id="summaryCheck"  href="#summaryCheckC" aria-expanded="true" aria-controls="summaryCheckC">
                <h4 class="panel-title">出访总结审批</h4>
            </div>
            <div class="panel-body" id="summaryCheckC" role="tabpanel" aria-labelledby="summaryCheck">
                <h3 class="red">审批中</h3>
                <table class="table table-th ">
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
<script>
    $().ready(function () {
        //导航栏样式
        $('.manage').addClass('active');
        $('.manage ul').show();
        $('.manage ul .home').addClass('current-page');
        //初始化显示
        $('#checkForm').hide();
        $('#checkerForm').hide();
        $('#summaryForm').hide();
        //新增出访总结审批人员表单
        $('#toAddSummaryChecker').click(function () {
            $('#checkerForm').show();
            $('#checkerForm .empNo').focus();
        });
        //新增出访申请审批人员表单
        $('#toAddChecker').click(function () {
            $('#checkForm').show();
            $('#checkForm .empNo').focus();
        });
        //新增出访总结表单
        $('#toAddSummary').click(function () {
            $('#summaryForm').show();
            $('#summaryForm #date').focus();
        });
        //添加出访总结审批人
        $('#addChecker').click(function () {
            var empNo = $('#checkerForm .empNo').val();
            var userName = $('#checkerForm .userName').val();
            var job = $('#checkerForm .job').val();
            var message = '';
            if(empNo == ''){
                message += 'OA号不能为空！';
            }
            if(userName == ''){
                message += '姓名不能为空！';
            }
            if(job == ''){
                message += '职位不能为空！';
            }
            if(message == ''){
                var op = '<tr><td>' + empNo + '</td>';
                op += '<td>' + userName + '</td>';
                op += '<td>' + job + '</td>';
                op += '<td><button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>';
                op += '<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i> 下移</button>';
                op += '<button type="button" class="btn btn-danger btn-xs remove">删除</button>';
                op += '</td></tr>';
                $('#summaryCheckTable tbody').append(op);
                $('#checkerForm').hide();
                $('#checkerForm .empNo').val('');
                userName = $('#checkerForm .userName').val('');
                job = $('#checkerForm .job').val('');
            }else {
                alert(message);
            }
        });
        //添加出访申请审批人
        $('#addCheck').click(function () {
            var empNo = $('#checkForm .empNo').val();
            var userName = $('#checkForm .userName').val();
            var job = $('#checkForm .job').val();
            var message = '';
            if(empNo == ''){
                message += 'OA号不能为空！';
            }
            if(userName == ''){
                message += '姓名不能为空！';
            }
            if(job == ''){
                message += '职位不能为空！';
            }
            if(message == ''){
                var op = '<tr><td>' + empNo + '</td>';
                op += '<td>' + userName + '</td>';
                op += '<td>' + job + '</td>';
                op += '<td><button type="button" class="btn btn-default btn-xs moveBefore"><i class="fa fa-chevron-up"></i>上移</button>';
                op += '<button type="button" class="btn btn-default btn-xs moveAfter"><i class="fa fa-chevron-down"></i> 下移</button>';
                op += '<button type="button" class="btn btn-danger btn-xs remove">删除</button>';
                op += '</td></tr>';
                $('#applyCheckTable tbody').append(op);
                $('#checkForm').hide();
                $('#checkForm .empNo').val('');
                userName = $('#checkForm .userName').val('');
                job = $('#checkForm .job').val('');
            }else {
                alert(message);
            }
        });
        //添加出访总结
        $('#addSummary').click(function () {
            var date = $('#summaryForm #date').val();
            var time = $('#summaryForm #time').val();
            var detail = $('#summaryForm #detail').val();
            var flag = true;
            var message = '';
            if(isEmpty(date)){
                message += '请填写日期!';
            }
            if(isEmpty(time)){
                message += '请填写时间！';
            }
            if(isEmpty(detail)){
                message += '请填写出访总结！';
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
                                    alert('该时间的总结已填写！');
                                    flag = false;
                                    return false;
                                }
                            })
                        }
                    })
                    if(flag){
                        var op = '<tr><td class="tDate">' + date + '</td>';
                        op += '<td class="tTime">' + time + '</td>';
                        op += '<td>' + detail + '</td>';
                        op += '<td><button class="btn btn-info btn-xs">编辑</button><button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button></td>';
                        $('.sortSummary tbody').append(op);
                        $('#summaryForm #date').val('');
                        $('#summaryForm #time').val('');
                        $('#summaryForm #detail').val('');
                        $('#summaryForm').hide();
                    }
                }
            }else {
                alert(message);
            }
        });
        //实现根据oa号拉取人信息
        $('.empNo').focusout(function () {
            var empNo = $(this).val();
            if(empNo == ''){
                $('#warning').show();
                //读取数据
                setTimeout('$("#warning").hide();',2000);
            }
        });
        //移除一条数据
        $('body').on('click','.remove',function () {
            $(this).parents('tr:first').remove();
        });
    })
</script>
</body>
</html>
