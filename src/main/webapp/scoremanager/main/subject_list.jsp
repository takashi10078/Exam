<%-- 学生一覧JSP --%>

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

		<%-- 画面全体セクション --%>
		<section class="me=4">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">

				科目管理

			</h2>

			<%-- 新規登録リンクエリア --%>
			<div class="my-2 text-end px-4">

				<%-- 科目新規登録画面へ移動 --%>
				<a href="SubjectCreate.action">新規登録</a>

			</div>

			<%-- 科目データ存在チェック --%>
			<c:choose>

				<%-- 科目データが1件以上存在する場合 --%>
				<c:when test="${subjects.size()>0 }">

					<%-- 科目一覧テーブル --%>
					<table class="table table-hover">

					
						<tr>

						
							<th>科目コード</th>

							<th>科目名</th>

							<th></th>

							<th></th>

						</tr>

						<%-- 科目一覧ループ開始 --%>
						<c:forEach var="subject" items="${subjects }">

							
							<tr>

								
								<td>${subject.cd }</td>

								<td>${subject.name }</td>

								<%-- 変更画面リンク --%>
								<td>

									<a href="SubjectUpdate.action?cd=${subject.cd }">

										変更

									</a>

								</td>

								<%-- 削除画面リンク --%>
								<td>

									<a href="SubjectDelete.action?cd=${subject.cd }">

										削除

									</a>

								</td>

							</tr>

						</c:forEach>

					</table>

				</c:when>

				<%-- 科目データが存在しない場合 --%>
				<c:otherwise>

					<div>

						科目情報が存在しませんでした。

					</div>

				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>