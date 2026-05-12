<%-- ヘッダー(header.jsp) --%>

<%-- JSP基本設定 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL(coreタグ)利用設定 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- システムタイトル表示 --%>
<div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">

	<%-- タイトル見出し --%>
	<h1 class="fs-1">
		得点管理システム
	</h1>
</div>

<%-- ログイン済みの場合のみ表示 --%>
<c:if test="${user.isAuthenticated()}">

	<%-- 右側メニュー --%>
	<div class="nav align-self-end">

		<%-- ユーザー名表示 --%>
		<span class="nav-item px-2">

			${user.getName()}様

		</span>

		<%-- ログアウトリンク --%>
		<a class="nav-item px-2" href="Logout.action">

			ログアウト

		</a>
	</div>
</c:if>