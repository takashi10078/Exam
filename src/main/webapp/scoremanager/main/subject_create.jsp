<%-- 学生登録JSP --%>

<%-- JSP設定 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%-- JSTL coreタグ使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト読み込み --%>
<c:import url="/common/base.jsp" >

	<%-- ページタイトル設定 --%>
	<c:param name="title">

		得点管理システム

	</c:param>

	<%-- JavaScript設定（今回はなし） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<%-- 画面セクション開始 --%>
		<section>

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">

				科目情報登録

			</h2>

			<%-- 科目登録フォーム --%>
			<form action="SubjectCreateExecute.action" method="get">

				<%-- 科目コード入力エリア --%>
				<div>

					<%-- 科目コードラベル --%>
					<label for="cd">

						科目コード

					</label><br>

					<%-- 科目コード入力欄 --%>
					<input
						class="form-control"
						type="text"
						id="cd"
						name="cd"
						value="${cd}"
						required
						maxlength="3"
						placeholder="科目コードを入力してください" />

					<%-- 桁数エラーチェック --%>
					<c:if test="${lengthError != null }">

						<%-- 桁数エラーメッセージ表示 --%>
						<div class="text-danger">

							${lengthError}

						</div>

					</c:if>

					<%-- 重複エラーチェック --%>
					<c:if test="${duplicateError != null }">

						<%-- 重複エラーメッセージ表示 --%>
						<div class="text-danger">

							${duplicateError}

						</div>

					</c:if>

				</div>

				<%-- 科目名入力エリア --%>
				<div>

					<%-- 科目名ラベル --%>
					<label for="name">

						科目名

					</label><br>

					<%-- 科目名入力欄 --%>
					<input
						class="form-control"
						type="text"
						id="name"
						name="name"
						value="${name}"
						required
						maxlength="20"
						placeholder="科目名を入力してください" />

				</div>

				<%-- 登録ボタンエリア --%>
				<div class="mx-auto py-2">

					<%-- 登録ボタン --%>
					<button
						class="btn btn-secondary"
						id="create-button"
						name="end">

						登録

					</button>

				</div>

			</form>

			<%-- 一覧画面へ戻るリンク --%>
			<a href="SubjectList.action">

				戻る

			</a>

		</section>

	</c:param>

</c:import>