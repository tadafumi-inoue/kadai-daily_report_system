<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                             <c:choose>
                               <c:when test="${sessionScope.login_employee.id == report.employee.id}">
                                <td><c:out value="${report.employee.name}" /></td>
                                </c:when>
                                <c:when test="${follows_check == null}">
                                    <td><c:out value="${report.employee.name}" />
                                        <form method="POST" action="<c:url value='/follows/create' />">
                                            <input type="hidden" name="_token" value="${_token}" />
                                            <input type="hidden" name="repo_id" value="${report.employee.id}" />
                                            <button type="submit">フォローする</button>
                                        </form></td>
                                </c:when>
                                <c:otherwise>
                                    <td><c:out value="${report.employee.name}" />
                                        <form method="POST" action="<c:url value='/follows/destroy' />">
                                            <input type="hidden" name="_token" value="${_token}" />
                                            <input type="hidden" name="repo_id" value="${report.employee.id}" />
                                            <button type="submit">フォローを解除する</button>
                                        </form></td>
                                </c:otherwise>
                                </c:choose>


                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>

                        <tr>
                            <th>出勤時間</th>
                            <td><c:out value="${report.work_time}" /></td>
                        </tr>
                        <tr>
                            <th>退勤時間</th>
                            <td><c:out value="${report.leave_time}" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td><pre>
                                    <c:out value="${report.content}" />
                                </pre></td>
                        </tr>
                        <tr>
                            <th>取引先企業</th>
                            <td><c:out value="${report.client_title}" /></td>
                        </tr>

                        <tr>
                            <th>商談内容</th>
                            <td><pre>
                                    <c:out
                                        value="${report.client_content}" />
                                </pre></td>
                        <tr>


                            <c:choose>
                                <c:when test="${favorites_check != null}">
                                    <th>
                                        <form method="POST"
                                            action="<c:url value='/favorites/destroy' />">
                                            <input type="hidden" name="_token" value="${_token}" />
                                            <button type="submit">いいね！解除</button>
                                        </form>
                                    </th>
                                    <td><c:out value="${favorites_count}" />
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when
                                            test="${sessionScope.login_employee.id != report.employee.id}">
                                            <th>
                                                <form method="POST"
                                                    action="<c:url value='/favorites/create' />">
                                                    <input type="hidden" name="_token" value="${_token}" />
                                                    <button type="submit">いいね！</button>
                                                </form>
                                            </th>
                                            <td><c:out value="${favorites_count}" />
                                        </c:when>
                                        <c:otherwise>
                                            <th>いいね！</th>
                                            <td><c:out value="${favorites_count}" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </tr>

                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${report.created_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${report.updated_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </tbody>
                </table>
                <c:if
                    test="${report.app_flag == 0 && login_employee.admin_flag != 0}">
                    <br />
                    <form method="POST" action="<c:url value='/reports/app' />">
                        <input type="hidden" name="_token" value="${_token}" /> <input
                            type="hidden" name="app_flag" value="1" />
                        <button type="submit">承認</button>
                    </form>
                    <br />
                </c:if>


                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <p>
                        <a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a>
                    </p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value="/reports/index" />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>