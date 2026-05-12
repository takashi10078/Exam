<%-- 学生登録完了画面JSP --%>

<%@ page language="java"contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL(coreタグ)利用設定 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト(base.jsp)読込 --%>
<c:import url="/common/base.jsp">

	<%-- ページタイトル設定 --%>
	<c:param name="title">

		得点管理システム

	</c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<%-- 全体囲み用div --%>
		<div id="wrap_box">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">学生情報登録</h2>

			<%-- 完了メッセージエリア --%>
			<div id="wrap_box">

				<%-- 登録完了メッセージ --%>
				<p class="text-center"style="background-color:#8cc3a9">登録が完了しました</p>
				
				<%-- 空白調整 --%>
				<br>
				<br>
				<br>
				<%-- 学生登録画面へ戻るリンク --%>
				<a href="StudentCreate.action">戻る</a>
				
				<%-- リンク間スペース --%>
				<a>　　　　　</a>

				<%-- 学生一覧画面へのリンク --%>
				<a href="StudentList.action">学生一覧</a>

			</div>

		</div>

	</c:param>

</c:import>