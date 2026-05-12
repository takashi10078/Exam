<%-- 学生情報変更JSP --%>

<%-- JSP基本設定（UTF-8文字コード） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	
<%-- JSTL(coreタグ)利用設定 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト(base.jsp)読込 --%>
<c:import url="/common/base.jsp" >

	<%-- ページタイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript用領域（今回は未使用） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<section>

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				学生情報変更
			</h2>

			<%-- 学生情報変更フォーム --%>
			<form action="StudentUpdateExecute.action" method="get">

				<%-- 入学年度表示（変更不可） --%>
				<div>

					<label class="mx-auto py-2" for="ent_year">入学年度</label><br>

					<input class="border border-0 ps-3"type="text"id="ent_year"name="ent_year"value="${ent_year }"
						   readonly />

				</div>

				<%-- 学生番号表示（変更不可） --%>
				<div class="mx-auto py-2">

					<label class="mx-auto py-2" for="no">
						学生番号
					</label><br>

					<input class="border border-0 ps-3" type="text" id="no"value="${no }"name="no"
						   readonly />

				</div>

				<%-- 氏名入力欄 --%>
				<div class="mx-auto py-2">

					<label for="name">
						氏名
					</label><br>

					<input class="form-control"type="text"id="name"name="name"value="${name }"required
						   maxlength="30" />

				</div>

				<%-- クラス選択 --%>
				<div class="mx-auto py-2">

					<label for="class_num">
						クラス
					</label><br>

					<select class="form-select"id="class_num"name="class_num">

						<%-- クラス一覧表示 --%>
						<c:forEach var="num" items="${class_num_set }">

							<%-- 現在選択中のクラスを保持 --%>
							<option value="${num }"
								<c:if test="${num==class_num }">
									selected
								</c:if>>

								${num }

							</option>

						</c:forEach>

					</select>

				</div>

				<%-- 在学中チェックボックス --%>
				<div class="mx-auto py-2">

					<label for="is_attend">
						在学中
					</label>

					<input type="checkbox"id="is_attend"name="is_attend"

						   <%-- 在学中の場合checked追加 --%>
						   <c:if test="${is_attend }">
								checked
						   </c:if> />

				</div>

				<%-- 変更ボタン --%>
				<div class="mx-auto py-2">

					<input class="btn btn-primary"type="submit" name="login"
						   value="変更"/>

				</div>

			</form>

			<%-- 学生一覧へ戻るリンク --%>
			<a href="StudentList.action">
				戻る
			</a>

		</section>

	</c:param>

</c:import>