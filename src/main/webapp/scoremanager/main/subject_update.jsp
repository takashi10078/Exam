<%-- 学生登録JSP --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%-- JSTL coreタグ使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウトbase.jsp読み込み --%>
<c:import url="/common/base.jsp" >

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript設定用（今回は空） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<%-- 画面全体セクション --%>
		<section>

			<%-- 見出し --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				科目情報変更
			</h2>

			<%-- 科目変更フォーム --%>
			<form action="SubjectUpdateExecute.action" method="get">

				<%-- 科目コード入力欄 --%>
				<div>

					<%-- ラベル --%>
					<label for="cd">科目コード</label><br>

					<%-- 科目コード表示 --%>
					<input
						class="border border-0 ps-3"
						type="text"
						id="cd"
						name="cd"
						value="${subject.cd }"
						readonly />

				</div>

				<%-- エラーメッセージ表示 --%>
				<c:if test="${error != null }">

					<div class="text-warning">

						<%-- エラーメッセージ表示 --%>
						${error}

					</div>

				</c:if>

				<%-- 科目名入力欄 --%>
				<div>

					<%-- ラベル --%>
					<label for="name">科目名</label><br>

					<%-- 科目名入力 --%>
					<input
						class="form-control"
						type="text"
						id="name"
						name="name"
						value="${subject.name }"
						required
						maxlength="20"
						placeholder="科目名を入力してください" />

				</div>

				<%-- ボタンエリア --%>
				<div class="mx-auto py-2">

					<%-- 変更ボタン --%>
					<button
						class="btn btn-secondary"
						id="create-button"
						name="end">

						変更

					</button>

				</div>

			</form>

			<%-- 戻るリンク --%>
			<a href="SubjectList.action">戻る</a>

		</section>

	</c:param>

</c:import>