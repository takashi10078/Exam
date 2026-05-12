<%-- 成績登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<p class="text-center py-2" style="background-color:#8cc3a9">登録が完了しました</p>
			<br>
			<a href="TestRegist.action">戻る</a>
			<a href="TestList.action" class="ms-4">成績参照</a>
		</section>
	</c:param>
</c:import>
