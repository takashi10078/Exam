<%-- 学生変更完了JSP --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL利用設定 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト読込 --%>
<c:import url="/common/base.jsp">

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- コンテンツ設定 --%>
	<c:param name="content">

		<%-- 画面全体 --%>
		<div id="wrap_box">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">
				学生情報変更
			</h2>

			<div id="wrap_box">

				<%-- 完了メッセージ --%>
				<p class="text-center" style="background-color:#8cc3a9">
					変更が完了しました
				</p>

				<br>
				<br>
				<br>

				<%-- 学生一覧リンク --%>
				<a href="StudentList.action">学生一覧</a>

			</div>
		</div>

	</c:param>
</c:import>