<%-- ログインJSP --%>

<%-- JSPの設定（文字コードなど） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL(coreタグ)を使用するための宣言 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト(base.jsp)を読み込む --%>
<c:import url="/common/base.jsp">

	<%-- ページタイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript設定 --%>
	<c:param name="scripts">

		<script type="text/javascript">

			// ページ読み込み後に実行
			$(function() {

				// チェックボックス変更時の処理
				$('#password-display').change(function() {

					// チェックされている場合
					if ($(this).prop('checked')) {

						// パスワード表示
						$('#password-input').attr('type', 'text');

					} else {

						// パスワード非表示
						$('#password-input').attr('type', 'password');
					}
				});
			});
		</script>
	</c:param>

	<%-- メイン画面内容 --%>
	<c:param name="content">

		<%-- ログインフォーム全体 --%>
		<section class="w-75 text-center m-auto border pb-3">

			<%-- LoginExecute.actionへPOST送信 --%>
			<form action="LoginExecute.action" method="post">

				<div id="wrap_box">

					<%-- 画面タイトル --%>
					<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">
						ログイン
					</h2>

					<%-- エラーメッセージ表示 --%>
					<c:if test="${errors.size()>0}">
						<div>
							<ul>

								<%-- エラー一覧を繰り返し表示 --%>
								<c:forEach var="error" items="${errors}">
									<li>${error}</li>
								</c:forEach>

							</ul>
						</div>
					</c:if>

					<div>

						<%-- ID入力欄 --%>
						<div class="form-floating mx-5">

							<input
								class="form-control px-5 fs-5"
								autocomplete="off"
								id="id-input"
								maxlength="20"
								name="id"
								placeholder="半角でご入力下さい"
								style="ime-mode: disabled"
								type="text"
								value="${id}"
								required />

							<label>ＩＤ</label>
						</div>

						<%-- パスワード入力欄 --%>
						<div class="form-floating mx-5 mt-3">

							<input
								class="form-control px-5 fs-5"
								autocomplete="off"
								id="password-input"
								maxlength="20"
								name="password"
								placeholder="20文字以内の半角英数字でご入力下さい"
								style="ime-mode: disabled"
								type="password"
								required />

							<label>パスワード</label>
						</div>

						<%-- パスワード表示チェックボックス --%>
						<div class="form-check mt-3">

							<label class="form-check-label" for="password-display">

								<input
									class="form-check-input"
									id="password-display"
									name="chk_d_ps"
									type="checkbox" />

								パスワードを表示
							</label>
						</div>
					</div>

					<%-- ログインボタン --%>
					<div class="mt-4">

						<input
							class="w-25 btn btn-lg btn-primary"
							type="submit"
							name="login"
							value="ログイン" />
					</div>

				</div>
			</form>
		</section>
	</c:param>
</c:import>