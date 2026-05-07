<%-- 成績登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<div id="wrap_box">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div id="wrap_box">
				<p class="text-center" style="background-color:#8cc3a9">登録が完了しました</p>

				<c:if test="${saved_list.size() > 0}">
					<table class="table table-hover mx-3">
						<tr>
							<th>学生番号</th>
							<th>科目コード</th>
							<th>試験回</th>
							<th class="text-center">得点</th>
						</tr>
						<c:forEach var="score" items="${saved_list}">
							<tr>
								<td>${score.studentNo}</td>
								<td>${score.subjectCd}</td>
								<td class="text-center">${score.no}回</td>
								<td class="text-center">${score.point}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<br>
				<br>
				<a href="TestRegist.action">戻る</a>
			</div>
		</div>
	</c:param>
</c:import>
