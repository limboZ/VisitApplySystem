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
                    <button type="button" id="addApply" class="btn btn-info"><i class="fa fa-plus"></i> 创建出访申请</button>
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
                    <tr>
                        <td>1</td>
                        <td>关于邹江华等2人去美国、加拿大的出国申请</td>
                        <td>2017-09-20</td>
                        <td>未审批</td>
                        <td>未填写</td>
                        <td>未审批</td>
                        <td>
                            <button class="btn btn-xs btn-info" data-toggle="modal" data-target="#summary">填写</button>
                        </td>
                        <td>
                            <a href="${ctx}/user/show" class="btn btn-xs btn-primary">详情</a>
                            <a href="#" class="btn btn-xs btn-info">编辑</a>
                            <a href="#" class="btn btn-xs btn-success">提交审批</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>关于黄龙翔等2人去日本、泰国的出国申请</td>
                        <td>2017-09-20</td>
                        <td>未审批</td>
                        <td>已填写</td>
                        <td>未审批</td>
                        <td>
                            <a class="btn btn-xs btn-info" href="#">填写</a>
                        </td>
                        <td>
                            <a href="#" class="btn btn-xs btn-primary">详情</a>
                            <a href="#" class="btn btn-xs btn-info">编辑</a>
                            <a href="#" class="btn btn-xs btn-success">提交审批</a>
                        </td>
                    </tr>
                <tr>
                    <td colspan="8">
                        <div class="btn-toolbar pull-right">
                            <div class="btn-group">
                                <button class="btn btn-info" type="button">1</button>
                                <button class="btn btn-info active" type="button">2</button>
                                <button class="btn btn-info" type="button">3</button>
                                <button class="btn btn-info" type="button">4</button>
                                <button class="btn btn-info" type="button">5</button>
                                <button class="btn btn-info" type="button">6</button>
                                <button class="btn btn-info" type="button">7</button>
                                <button class="btn btn-info" type="button">8</button>
                            </div>
                        </div>
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
                                    <th>出访总结</th>
                                    <th width="12%">操作</th>
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
                                        今天去了日本东京，看了富士山脚下的樱花...今天去了日本东京，看了富士山脚下的樱花...今天去了日本东京，看了富士山脚下的樱花...今天去了日本东京，看了富士山脚下的樱花...今天去了日本东京，看了富士山脚下的樱花...今天去了日本东京，看了富士山脚下的樱花...今天去了日本东京，看了富士山脚下的樱花...
                                    </td>
                                    <td>
                                        <button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tDate">
                                        2017-09-23
                                    </td>
                                    <td class="tTime">
                                        上午
                                    </td>
                                    <td>
                                        1234567890 * 1024
                                    </td>
                                    <td>
                                        <button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <%--<button type="button" class="btn btn-primary ">新增行程</button>--%>
                            <div id="add">
                                <h2>新增行程</h2>
                                <div class="ln_solid"></div>
                                <form class="form-horizontal form-label-left">
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
                                        <label class="control-label col-sm-2 col-xs-12">出访总结<span class="required">*</span>
                                        </label>
                                        <div class="col-sm-8 col-xs-12">
                                           <textarea rows="10" class="form-control" id="detail"></textarea>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <button type="button" id="addVisit" class="btn btn-success pull-right">新增</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info">保存</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <script src="${ctx}/assets/build/js/jquery.min.js"></script>
    <script src="${ctx}/assets/build/js/sort.summary.js"></script>
    <script>
        $('#addApply').click(function () {
            window.location.href = '${ctx}/user/add';
        });
        //新增行程
        $('#addVisit').click(function () {
            var date = $('#date').val();
            var time = $('#time').val();
            var detail = $('#detail').val();
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
                    var op = '<tr><td class="tDate">' + date + '</td>';
                    op += '<td class="tTime">' + time + '</td>';
                    op += '<td>' + detail + '</td>';
                    op += '<td><button class="btn btn-danger btn-xs remove"><i class="fa fa-remove"></i>移除行程</button></td>';
                    $('#summaryList').append(op);
                    $('#date').val('');
                    $('#time').val('');
                    $('#detail').val('');
                }
            }else {
                alert(message);
            }
        })
        //移除一条总结
        $('body').on('click','.remove',function () {
            $(this).parents('tr:first').remove();
        })
    </script>
</body>
</html>
