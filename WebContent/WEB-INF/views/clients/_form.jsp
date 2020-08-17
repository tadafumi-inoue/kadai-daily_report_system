<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
   <div id = "flush_error">
        入力内容にエラーがあります <br />
        <c:forEach var="error" items="${errors}">
          ・<c:out value="${error}" /> <br />
          </c:forEach>
   </div>
</c:if>
<label for="num">取引先コード</label><br />
<input type="text" name="num" value="${client.num}" />
<br /><br />

<label for="name">企業名</label><br />
<input type="text" name="name" value="${client.name}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>

