<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
<c:choose>
<c:when test="${client != null}">
<h2>id : ${client.id} の企業情報 編集ページ</h2>
<form method="POSt" action="<c:url value='/clients/update' />">
<c:import url="_form.jsp" />
</form>
</c:when>
<c:otherwise>
<h2>お探しのデータは見つかりませんでした。</h2>
</c:otherwise>
</c:choose>

<p><a href="<c:url value='/clients/index' />">一覧に戻る</a></p>

</c:param>
</c:import>