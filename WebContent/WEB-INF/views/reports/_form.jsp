<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。 <br />
        <c:forEach var="error" items="${errors}">
   ・<c:out value="${error}" />
            <br />
        </c:forEach>
    </div>
</c:if>
<label for="report_date">日付</label>
<br />
<input type="date" name="report_date"
    value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />" />
<br />
<br />


<label for="name">氏名</label>
<br />
<c:out value="${sessionScope.login_employee.name}" />
<br />
<br />

<label for="title">タイトル</label>
<br />
<input type="text" name="title" value="${report.title}" />
<br />
<br />

<label for="content">内容</label>
<br />
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br />
<br />

<label for="client_title">取引先企業</label>
<br />
<select name="client_title">
<c:forEach var="client" items="${client}">
<option><c:out value="${client.name}" /></option>
</c:forEach>

</select>

<br />
<br />

<label for="client_content">商談内容</label>
<br />
<textarea name="client_content" rows="10" cols="50">${report.client_content}</textarea>
<br />
<br />

<label for="work_time">出勤時間</label>
<br />
<input type="time" name="work_time" value="${report.work_time}" />
<br />
<label for="leave_time">退勤時間</label>
<br />
<input type="time" name="leave_time" value="${report.leave_time}" />
<br />
<br />

<input type="hidden" name="_token" value="${_token}" />
<c:choose>
<c:when test="${sessionScope.login_employee.admin_flag == 0}">
<input type="hidden" name="app_flag" value="0" />
</c:when>
<c:otherwise>
<input type="hidden" name="app_flag" value="1" />
</c:otherwise>
</c:choose>
<button type="submit">投稿</button>