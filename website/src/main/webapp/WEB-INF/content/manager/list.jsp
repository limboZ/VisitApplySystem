<%--
  Created by IntelliJ IDEA.
  User: limboZ
  Date: 2017/9/28
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请审核</title>
</head>
<body>
<div class="x_panel">
    <div class="x_title">
        <h2>申请审核</h2>
        <%--<ul class="nav navbar-right panel_toolbox">--%>
            <%--<li>--%>
                <%--<button type="button" id="addApply" class="btn btn-info"><i class="fa fa-plus"></i> 创建出访申请</button>--%>
            <%--</li>--%>
        <%--</ul>--%>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <table class="table table-striped">
            <thead>
            <tr>
                <th width="5%">序号</th>
                <th width="30%">出访团名称</th>
                <th width="10%">申请创建时间</th>
                <th width="10%">申请审批状态</th>
                <th width="7%">出访总结</th>
                <th width="10%">总结审批状态</th>
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
                    <a href="${ctx}/manager/show" class="btn btn-xs btn-primary">详情</a>
                    <button type="button" class="btn btn-danger btn-xs delete">删除</button>
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
                    <a href="#" class="btn btn-xs btn-primary">详情</a>
                    <button type="button" class="btn btn-danger btn-xs delete">删除</button>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                        <%--<c:if test="${taskFlag != '1'}">--%>
                        <%--//分页--%>
                        <%--<tags:pagination pagination="${pageCommand}" currentPage="${currentPage}" count="${count}" />--%>
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
                    </div>                        <%--</c:if>--%>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="${ctx}/assets/build/js/jquery.min.js"></script>
<script>
    $('.delete').click(function () {
        if(confirm("确定删除该条申请？")){

        }
    })
</script>
</body>
</html>
