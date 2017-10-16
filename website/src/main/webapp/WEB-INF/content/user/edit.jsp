<%--
  Created by IntelliJ IDEA.
  User: limboZ
  Date: 2017/9/28
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/content/taglib.jsp" %>
<html>
<head>
    <title>编辑出访申请</title>
</head>
<body>
<div class="x_panel">
    <div class="x_title">
        <h2>编辑出访申请</h2>
        <div class="pull-right">
            <button type="button" class="btn btn-warning" onclick="history.back()">返回</button>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal form-label-left">
            <input type="hidden" id="applyId" value="${applyDetailDTO.id}" >
            <div class="form-group">
                <label class="control-label col-sm-2 col-xs-12">团组名称<span class="required">*</span>
                </label>
                <div class="col-sm-8 col-xs-12">
                    <input type="text" id="name" value="${applyDetailDTO.teamName}" class="form-control" readonly >
                </div>
            </div>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="form-group">
                    <label class="control-label col-sm-2 col-xs-12">任务类型<span class="required">*</span>
                    </label>
                    <div class="col-sm-8 col-xs-12">
                        <select class="form-control" id="type" name="type" required>
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2 col-xs-12">预计出访时间<span class="required">*</span>
                    </label>
                    <div class="col-sm-8 col-xs-12">
                        <div class="col-xs-8">
                            <input type="text" class="Wdate datePicker" value="<fmt:formatDate value="${applyDetailDTO.startTime}" pattern="yyyy-MM-dd"></fmt:formatDate>" id="startTime" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'2099-12-31\'}'})" required>  至
                            <input type="text" class="Wdate datePicker" value="<fmt:formatDate value="${applyDetailDTO.endTime}" pattern="yyyy-MM-dd"></fmt:formatDate>" id="endTime" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2099-12-31'})" required>
                        </div>
                        <div class="col-xs-4">
                            共计<input type="number" id="countdays" class="textInput" readonly style="width:60px;background-color: " >天
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2 col-xs-12">出访事由<span class="required">*</span>
                    </label>
                    <div class="col-sm-8 col-xs-12">
                        <textarea rows="10" id="reason" class="form-control" maxlength="1000">${applyDetailDTO.reason}</textarea>
                    </div>
                </div>
                <div class="ln_solid"></div>
                <div class="form-group">
                    <label class="control-label col-sm-2 col-xs-12">目的地<span class="required">*</span>
                    </label>
                    <div class="col-sm-8 col-xs-12">
                        <table class="table table-th" id="destinationList">
                            <tbody>
                                <c:forEach var="item" items="${applyDetailDTO.destinations}">
                                    <tr>
                                        <th>国家</th><td class="nation">${item.nation}</td>
                                        <th>城市</th><td class="city">${item.destination}</td>
                                        <td><button type="button" class="btn btn-xs btn-danger remove"><i class="fa fa-remove"></i></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr id="nationForm">
                                <th>国家</th>
                                <td>
                                    <input type="text" id="nation">
                                </td>
                                <th>城市</th>
                                <td>
                                    <input type="text" id="city">
                                </td>
                                <td style="width:120px;">
                                    <button type="button" class="btn btn-xs btn-primary " id="addDestination"><i class="fa fa-plus"></i> 添加</button>
                                    <button type="button" class="btn btn-warning btn-xs cancel">取消</button>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                        <button type="button" id="toAddDestination" class="btn btn-info"><i class="fa fa-plus"></i> 新增目的地</button>
                    </div>
                </div>
                <div class="ln_solid"></div>
                <div class="form-group">
                    <label class="control-label col-sm-2 col-xs-12">出访人员<span class="required">*</span>
                    </label>
                    <div class="col-sm-10 col-xs-12">
                        <table class="table table-th" id="visitorList">
                            <tbody>
                            <c:forEach items="${applyDetailDTO.teamMates}" var="item">
                                <tr>
                                    <th>OA号</th><td class="empNo">${item.employeeId}</td>
                                    <th>姓名</th><td class="userName">${item.employeeName}</td>
                                    <th>部门</th><td class="department">${item.employeeDept}</td>
                                    <th>职位</th><td class="job">${item.employeePost}</td>
                                    <td><button type="button" class="btn btn-xs btn-danger remove"><i class="fa fa-remove"></i></button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr id="visitorForm">
                                <th>OA号</th>
                                <td>
                                    <input type="text" id="empNo">
                                </td>
                                <th>姓名</th>
                                <td>
                                    <input type="text" id="userName">
                                </td>
                                <th>部门</th>
                                <td>
                                    <input type="text" id="department">
                                </td>
                                </td>
                                <th>职位</th>
                                <td>
                                    <input type="text" id="job">
                                </td>
                                <td width="120px;">
                                    <button type="button" class="btn btn-xs btn-primary" id="addVisitor"> 添加</button>
                                    <button type="button" class="btn btn-warning btn-xs cancel">取消</button>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                        <button type="button" class="btn btn-info" id="toAddVisitor"><i class="fa fa-plus"></i> 新增人员</button>
                    </div>
                </div>
            </div>
            <%--</div>--%>
            <%--</div>--%>
            <div class="ln_solid"></div>
            <button type="button" class="btn btn-success pull-right" id="createButton"><i class="fa fa-plus"></i>保存</button>
        </form>
    </div>
</div><%--遮罩背景层--%>
<div id="warning" class="loading-div">
    <div class="inner">
        <img src="${ctx}/assets/images/loader.gif">
    </div>
</div>
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<script src="${ctx}/assets/build/js/mine.js"></script>
<script>
    $('.normal').addClass('active');
    $('.normal ul').show();
    $('.normal ul .home').addClass('current-page');
    //初始化视图
    $('#nationForm').hide();
    $('#toAddDestination').click(function () {
        $('#nationForm').show();
        $('#nation').focus();
    })
    $('#visitorForm').hide();
    $('#toAddVisitor').click(function () {
        $('#visitorForm').show();
        $('#empNo').focus();
    })
    $('.cancel').click(function () {
        $(this).parents('tr:first').hide();
    })
    //添加目的地
    $('#addDestination').click(function () {
        var city = $('#city').val();
        var nation = $('#nation').val();
        if(nation == ''){
            alert('请输入国家！');
            $('#nation').focus();
        }else if(city == ''){
            alert('请输入城市！');
            $('#city').focus();
        }else {
            var innerHtml = '';
            innerHtml += '<tr>';
            innerHtml += '<th>国家</th>';
            innerHtml += '<td class="nation">' + nation + '</td>';
            innerHtml += '<th>城市</th>';
            innerHtml += '<td class="city">' + city + '</td>';
            innerHtml += '<td><button type="button" class="btn btn-xs btn-danger remove"><i class="fa fa-remove"></i></button></td>';
            innerHtml += '</tr>';
            $('#destinationList tbody').append(innerHtml);
            $('#city').val('');
            $('#nation').val('');
            $('#nationForm').hide();
        }
    })
    //添加访问人
    $('#addVisitor').click(function () {
        var empNo = $('#empNo').val();
        var userName = $('#userName').val();
        var department = $('#department').val();
        var job = $('#job').val();
        var message = '';
        if(empNo == ''){
            message += '请输入OA号！';
        }
        if(userName == ''){
            message += '请输入姓名！';
        }
        if(department == ''){
            message += '请输入部门！';
        }
        if(job == ''){
            message += '请输入职位！';
        }
        if(message == ''){
            $('#visitorForm').hide();
            var option = '<tr><th>OA号</th>';
            option += '<td class="empNo">' + empNo + '</td>';
            option += '<th>姓名</th>';
            option += '<td class="userName">' + userName + '</td>';
            option += '<th>部门</th>';
            option += '<td class="department">' + department + '</td>';
            option += '<th>职位</th>';
            option += '<td class="job">' + job + '</td>';
            option += '<td><button type="button" class="btn btn-xs btn-danger remove"><i class="fa fa-remove"></i></button></td>';
            option += '</tr>';
            $('#visitorList tbody').append(option);
            $('#empNo').val('');
            $('#userName').val('');
            $('#department').val('');
            $('#job').val('');

        }else {
            alert(message);
        }
    })
    $('body').on('click','.remove',function () {
        $(this).parents('tr:first').remove();
    });
    //自动从oa拉取出访人员数据
    $('#empNo').focusout(function () {
        if(!isEmpty($(this).val())){
            $('#warning').show();
            //读取数据
            setTimeout('$("#warning").hide();',2000);
            $('#userName').val('黄龙翔');
            $('#department').val('信息服务部');
            $('#job').val('项目经理');
        }
    });
    //当开始日期和结束日期选择完成之后，自动计算天数
    $('.Wdate').focusout(function () {
        var start = $('#startTime').val();
        var end = $('#endTime').val();
        if(!isEmpty(start) && !isEmpty(end)){
            var days = DateDiff(start,end) + 1;
            $('#countdays').val(days);
        }
    })
    //点击提交，组装数据到后台
    $('#createButton').click(function () {
        //验证数据的有效性
        var applyId = $('#applyId').val();
        var type = $.trim($('#type').val());
        var startDate = $.trim($('#startTime').val());
        var endTime = $.trim($('#endTime').val());
        var reason = $.trim($('#reason').val());
        var destinations = $('#destinationList tbody tr');
        var visitors = $('#visitorList tbody tr');
        //存储提示信息
        var message = '';
        if(isEmpty(type)){
            message += '请选择出访类型！';
        }
        if(isEmpty(startDate) || isEmpty(endTime)){
            message += '请选择出访时间！';
        }
        if(isEmpty(reason)){
            message += '请填写出访事由！';
        }else {
            if(reason.length > 1000){
                message += '出访事由最多为1000字！';
            }
        }
        if(destinations.length == 0){
            message += '请选择出访目的地！';
        }
        if(visitors.length == 0){
            message += '请选择出访团组成员！';
        }
        if(isEmpty(message)){
            //拼接出访信息传至后台
            var applyDto = {};
            applyDto.id = applyId;
            applyDto.commissionType = type;
            applyDto.startTime = startDate;
            applyDto.endTime = endTime;
            applyDto.reason = reason;
            //拼接目的地
            var destination = [];
            $(destinations).each(function () {
                var item = {};
                item.nation = $(this).find('.nation').text();
                item.destination = $(this).find('.city').text();
                destination.push(item);
            });
            applyDto.destinations = destination;
            //拼接出访人员
            var visitor = [];
            $(visitors).each(function () {
                var item = {};
                item.employeeId = $(this).find('.empNo').text();
                item.employeeName = $(this).find('.userName').text();
                item.employeeDept = $(this).find('.department').text();
                item.employeePost = $(this).find('.job').text();
                visitor.push(item);
            });
            applyDto.teamMates = visitor;
            console.log(applyDto);
            $('#warning').show();
            //ajax提交数据
            $.ajax({
                url:'${ctx}/user/submitEdit',
                method:'post',
//                    data:{applyDTO:JSON.stringify(applyDto)},
                data:JSON.stringify(applyDto),
                dataype:'json',
                contentType:"application/json",
                success:function (data) {
                    alert(data.tip);
                    if(data.code == 0){
                        location.href = '${ctx}/user/show/${applyDetailDTO.id}';
                    }
                    $('#warning').hide();
                },
                error:function () {
                    alert('连接服务器失败！');
                    $('#warning').hide();
                }
            });
        }else {
            alert(message);
        }
    });
    //ajax获取所有任务类型
    $.ajax({
        url:'${ctx}/commissionTypes',
        success:function (data) {
            if(data.code == 0){
                if(data.data.length > 0){
                    for(var i=0;i<data.data.length;i++){
                        var option = '<option value="' + data.data[i].commissionType + '">' + data.data[i].commissionType + '</option>';
                        $('#type').append(option);
                    }
                    //选中目前的任务类型
                    var type = '${applyDetailDTO.commissionType}';
                    $('#type').val(type);
                }
            }else {
                alert("任务类型拉取失败：" + data.tip);
            }
        }
    });
    //计算天数
    var days = DateDiff($('#startTime').val(),$('#endTime').val()) + 1;
    $('#countdays').val(days);
</script>
</body>
</html>
