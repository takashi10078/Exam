<%-- 学生登録完了JSP --%>

<%-- JSP設定 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL coreタグ使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト読み込み --%>
<c:import url="/common/base.jsp">

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<%-- 全体ラッパー --%>
		<div id="wrap_box">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">
				科目情報変更
			</h2>

			<%-- 内容エリア --%>
			<div id="wrap_box">

				<%-- 完了メッセージ表示 --%>
				<p class="text-center" style="background-color:#8cc3a9">

					変更が完了しました

				</p>

				<%-- 改行 --%>
				<br>
				<br>
				<br>

				<%-- 科目一覧画面へ戻るリンク --%>
				<a href="SubjectList.action">

					科目一覧

				</a>

			</div>

		</div>

	</c:param>

</c:import>