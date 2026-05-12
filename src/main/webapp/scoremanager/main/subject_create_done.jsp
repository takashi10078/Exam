<%-- 学生登録完了JSP --%>

<%-- JSP設定 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL coreタグ使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト読み込み --%>
<c:import url="/common/base.jsp">

	<%-- ページタイトル設定 --%>
	<c:param name="title">

		得点管理システム

	</c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<%-- 全体ラッパー --%>
		<div id="wrap_box">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">科目情報登録</h2>

			<%-- 内容表示エリア --%>
			<div id="wrap_box">

				<%-- 登録完了メッセージ表示 --%>
				<p class="text-center" style="background-color:#8cc3a9">登録が完了しました</p>

				<%-- 空白用改行 --%>
				<br>
				<br>
				<br>

				<%-- 科目登録画面へ戻るリンク --%>
				<a href="SubjectCreate.action">戻る</a>

				<%-- リンク間の空白 --%>
				<a>　　　　　</a>

				<%-- 科目一覧画面へ移動 --%>
				<a href="SubjectList.action">科目一覧</a>

			</div>

		</div>

	</c:param>

</c:import>