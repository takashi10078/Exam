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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">

				科目情報削除

			</h2>

			<%-- 内容表示エリア --%>
			<div id="wrap_box">

				<%-- 削除確認メッセージ --%>
				<p class="text-center" style="background-color:#8cc3a9">

					「${subject.name}(${subject.cd})」を削除してもよろしいですか。

				</p>

				<%-- 削除実行ボタン --%>
				<a
					href="SubjectDeleteExecute.action?cd=${subject.cd }"
					class="btn btn-danger">

					削除

				</a>

				<%-- 空白用改行 --%>
				<br>
				<br>
				<br>

				<%-- 一覧画面へ戻るリンク --%>
				<a href="SubjectList.action">

					戻る

				</a>

			</div>

		</div>

	</c:param>

</c:import>