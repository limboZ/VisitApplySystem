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
    <title>填写出访申请</title>
</head>
<body>
    <div class="x_panel">
        <div class="x_title">
            <h2>填写出访申请</h2>
            <div class="pull-right">
                <button type="button" class="btn btn-warning" onclick="history.back()">返回</button>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="bg-danger">
                1.保存自动生成团组名称:
                首个团员的部门名称+首个团员名字+等x人赴+所有目的地国家(最多三个国家名称)+任务类型+出访申请
                例如:信息服务部冯涛等2人赴美国、加拿大国际会议出访申请<br/>
                每次编辑时名称重新生成<br>
                2.时间用户自己填写(共计天数)<br/>
                3.任务类型与外事系统的一致,取外事系统的数据（json文件存储）：飞机接机、商务洽谈、航站审计、境外培训、航站代表、飞行会计、国际会议、境外招聘、飞机监造、设备考察、境外授训、首航筹备、航线实习、验证飞行、机组团组<br/>
                4.出访事由1000字内<br/>
                5.所有都是必填项<br/>
                6.新增出访人员时通过OA工号查询相关数据并自动填入姓名,部门,职位
            </div>
            <form class="form-horizontal form-label-left">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="form-group">
                                <label class="control-label col-sm-2 col-xs-12">任务类型<span class="required">*</span>
                                </label>
                                <div class="col-sm-8 col-xs-12">
                                    <select class="form-control" id="type" name="type" required>
                                        <option value="">请选择</option>
                                        <c:forEach var="item" items="">
                                            <option value=""></option>
                                        </c:forEach>
                                        <option>飞机接机</option>
                                        <option>商务洽谈</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2 col-xs-12">预计出访时间<span class="required">*</span>
                                </label>
                                <div class="col-sm-8 col-xs-12">
                                    <div class="col-xs-8">
                                        <input type="text" class="Wdate datePicker" id="startTime" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'2099-12-31\'}'})" required>  至
                                        <input type="text" class="Wdate datePicker" id="endTime" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2099-12-31'})" required>
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
                                    <textarea rows="10" id="reason" class="form-control">最多1000字</textarea>
                                </div>
                            </div>
                        <div class="ln_solid"></div>
                            <div class="form-group">
                                <label class="control-label col-sm-2 col-xs-12">目的地<span class="required">*</span>
                                </label>
                                <div class="col-sm-8 col-xs-12">
                                    <table class="table table-th" id="destinationList">
                                        <tbody>
                                            <tr id="nationForm">
                                                <th>国家</th>
                                                <td>
                                                    <input type="text" id="nation">
                                                </td>
                                                <th>城市</th>
                                                <td>
                                                    <input type="text" id="city">
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-xs btn-primary " id="addDestination"><i class="fa fa-plus"></i> 添加</button>
                                                </td>
                                            </tr>
                                        </tbody>
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
                                        <tr id="visitorForm">
                                            <th>工号</th>
                                            <td>
                                                <%--<input type="text" style="width:60px" id="empNo">--%>
                                                <input type="text" id="empNo">
                                            </td>
                                            <th>姓名</th>
                                            <td>
                                                <%--<input type="text" style="width:80px" id="userName">--%>
                                                <input type="text" id="userName">
                                            </td>
                                            <th>部门</th>
                                            <td>
                                                <%--<input type="text" style="width:120px" id="department">--%>
                                                <input type="text" id="department">
                                            </td>
                                            </td>
                                            <th>职位</th>
                                            <td>
                                                <input type="text" id="job">
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-xs btn-primary" id="addVisitor"><i class="fa fa-plus"></i> 添加</button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <button type="button" class="btn btn-info" id="toAddVisitor"><i class="fa fa-plus"></i> 新增人员</button>
                                </div>
                            </div>
                        </div>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="ln_solid"></div>
                <button type="button" class="btn btn-success pull-right" id="createButton"><i class="fa fa-plus"></i>添加</button>
            </form>
        </div>
    </div><%--遮罩背景层--%>
    <div id="warning" class="loading-div">
        <div class="inner">
            <img src="${ctx}/assets/images/loader.gif">
        </div>
    </div>
    <script src="${ctx}/assets/build/js/jquery.min.js"></script>
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
                innerHtml += '<td>' + nation + '</td>';
                innerHtml += '<th>城市</th>';
                innerHtml += '<td>' + city + '</td>';
                innerHtml += '<td><button type="button" class="btn btn-xs btn-danger remove"><i class="fa fa-remove"></i></button></td>';
                innerHtml += '</tr>';
                $('#destinationList tr:last').before(innerHtml);
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
                message += '请输入工号！';
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
                var option = '<tr><th>工号</th>';
                option += '<td>' + empNo + '</td>';
                option += '<th>姓名</th>';
                option += '<td>' + userName + '</td>';
                option += '<th>部门</th>';
                option += '<td>' + department + '</td>';
                option += '<th>职位</th>';
                option += '<td>' + job + '</td>';
                option += '<td><button type="button" class="btn btn-xs btn-danger remove"><i class="fa fa-remove"></i></button></td>';
                option += '</tr>';
                $('#visitorForm').before(option);
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
            $('#warning').show();
        });
    </script>
</body>
</html>
