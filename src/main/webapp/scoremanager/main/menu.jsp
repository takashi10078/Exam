<%-- メニューJSP：システムのメインメニュー画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL（タグライブラリ）を使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト（base.jsp）を読み込む --%>
<c:import url="/common/base.jsp">

	<%-- ページタイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- 追加スクリプト（今回はなし） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ部分 --%>
	<c:param name="content">

		<section class="me-4">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				メニュー
			</h2>

			<%-- メニュー全体のレイアウト（横並び） --%>
			<div class="row text-center px-4 fs-3 my-5">

				<%-- 学生管理メニュー --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbb;">
					<a href="StudentList.action">学生管理</a>
				</div>

				<%-- 成績管理メニュー（登録・参照） --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bdb;">

					<div>

						<%-- 見出し（リンクなし） --%>
						<div>成績管理</div>

						<%-- 成績登録リンク --%>
						<div>
							<a href="TestRegist.action">成績登録</a>
						</div>

						<%-- 成績参照リンク --%>
						<div>
							<a href="TestList.action">成績参照</a>
						</div>

					</div>
				</div>

				<%-- 科目管理メニュー --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bbd;">
					<a href="SubjectList.action">科目管理</a>
				</div>

			</div>
		</section>

	</c:param>
</c:import>