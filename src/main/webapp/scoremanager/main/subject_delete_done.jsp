<%-- 学生削除完了JSP --%>

<%-- JSP設定 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL coreタグライブラリ使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウトbase.jsp読み込み --%>
<c:import url="/common/base.jsp">

	<%-- ページタイトル設定 --%>
	<c:param name="title">

		得点管理システム

	</c:param>

	<%-- メインコンテンツ設定 --%>
	<c:param name="content">

		<%-- 画面全体ラッパー --%>
		<div id="wrap_box">

			<%-- 画面タイトル表示 --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">

				科目情報削除

			</h2>

			<%-- 内容表示エリア --%>
			<div id="wrap_box">

				<%-- 削除完了メッセージ表示 --%>
				<p class="text-center" style="background-color:#8cc3a9">

					削除が完了しました

				</p>

				<%-- レイアウト調整用改行 --%>
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

</c:import