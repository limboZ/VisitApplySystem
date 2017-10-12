<%@ tag pageEncoding="UTF-8" isELIgnored="false" %>
<%@ tag import="java.util.List" %>
<%@attribute name="pagination" type="cn.com.scal.components.utils.Pagination" required="true" %>
<%@attribute name="count" type="java.lang.Integer" required="true" %>
<%@attribute name="currentPage" type="java.lang.Integer" required="true" %>
<%@attribute name="showFlag" required="false" type="java.lang.Boolean" %>
<%
    if (null != count && count.intValue() > 0) {
        pagination.setCount(count);
        pagination.setPageUrl(request.getQueryString());
        pagination.setCurrentPage(currentPage);
%>
<div class="paging pull-right">

    <a href="?<%=pagination.generateUrl(pagination.getPreviousPageNumber())%>" class="btn btn-default">&laquo;前一页</a>
    <%
        List<Integer> list = pagination.getPageNumList();
        for (Integer i : list) {
            if (i == pagination.getCurrentPage()) {
    %>
    <span class="btn btn-info active"><%=i%></span>
    <% } else {
    %>
    <a href="?<%=pagination.generateUrl(i)%>" class="btn btn-info"><%=i%>
    </a>
    <%
            }
        }
    %>
    <a href="?<%=pagination.generateUrl(pagination.getNextPageNumber())%>" class="btn btn-default">后一页&raquo;</a>

    <label>总<%=pagination.getLastPageNumber()%>页&nbsp;&nbsp;&nbsp;&nbsp;共<%=count%>条

    </label>

</div>
<%}%>

