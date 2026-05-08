<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="me-4">

			<%-- タイトルを画像に合わせて動的に変更 --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				<c:choose>
					<c:when test="${student != null}">成績一覧（学生）</c:when>
					<c:when test="${subject != null}">成績一覧（科目）</c:when>
					<c:otherwise>成績参照</c:otherwise>
				</c:choose>
			</h2>
			
			<%-- 検索フォームエリア（変更なし） --%>
			<div class="border rounded p-4 mb-4">
				
				<%-- 科目情報フォーム --%>
				<form action="TestListSubjectExecute.action" method="get" class="mb-4">
					<input type="hidden" name="f" value="sj">
					
					<div class="row align-items-center">
						<%-- 左側：項目名 --%>
						<div class="col-2">
							<p class="mb-0 text-center">科目情報</p>
						</div>
						
						<%-- 中央：セレクトボックス群 --%>
						<div class="col-8">
							<div class="row">
								<div class="col-4">
									<label class="form-label small">入学年度</label>
									<select name="f1" class="form-select" required>
										<option value="">--------</option>
										<c:forEach var="year" items="${ent_year_set}">
											<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-4">
									<label class="form-label small">クラス</label>
									<select name="f2" class="form-select" required>
										<option value="">--------</option>
										<c:forEach var="num" items="${class_num_set}">
											<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-4">
									<label class="form-label small">科目</label>
									<select name="f3" class="form-select" required>
										<option value="">--------</option>
										<c:forEach var="subject" items="${subject_set}">
											<option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<%-- 右側：ボタン --%>
						<div class="col-2 text-end">
							<button type="submit" class="btn btn-secondary px-4">検索</button>
						</div>
					</div>
				</form>

				<%-- 区切り線 --%>
				<hr class="my-4 text-secondary opacity-25">

				<%-- 学生情報フォーム --%>
				<form action="TestListStudentExecute.action" method="get">
					<input type="hidden" name="f" value="st">

					<div class="row align-items-center">
						<%-- 左側：項目名 --%>
						<div class="col-2">
							<p class="mb-0 text-center">学生情報</p>
						</div>

						<%-- 中央：入力ボックス --%>
						<div class="col-8">
							<div class="row">
								<div class="col-5">
									<label class="form-label small">学生番号</label>
									<input
										type="text"
										name="f4"
										value="${f4}"
										class="form-control"
										placeholder="学生番号を入力してください"
										maxlength="10"
										required
									>
								</div>
							</div>
						</div>

						<%-- 右側：ボタン --%>
						<div class="col-2 text-end">
							<button type="submit" class="btn btn-secondary px-4">検索</button>
						</div>
					</div>
				</form>
			</div>

			<%-- 結果表示エリア --%>
			<div class="mt-4 px-2">
				<c:choose>
					<%-- 1. 検索結果がある場合 --%>
					<c:when test="${tests != null && tests.size() > 0}">
						
						<%-- 学生別検索時に氏名を表示（画像のレイアウト） --%>
						<c:if test="${student != null}">
							<div class="mb-3 px-2">氏名：${student.name}（${student.no}）</div>
						</c:if>

						<table class="table table-hover">
							<thead>
								<c:choose>
									<%-- 学生別検索（今回の画像） --%>
									<c:when test="${student != null}">
										<tr>
											<th>科目名</th>
											<th>科目コード</th>
											<th>回数</th>
											<th>点数</th>
										</tr>
									</c:when>
									<%-- 科目別検索 --%>
									<c:otherwise>
										<tr>
											<th>入学年度</th>
											<th>クラス</th>
											<th>学生番号</th>
											<th>氏名</th>
											<th>1回</th>
											<th>2回</th>
										</tr>
									</c:otherwise>
								</c:choose>
							</thead>
							<tbody>
								<c:forEach var="test" items="${tests}">
									<tr>
										<c:choose>
											<%-- 学生別検索の結果行 --%>
											<c:when test="${student != null}">
												<td>${test.subjectName}</td>
												<td>${test.subjectCd}</td>
												<td>${test.count}</td>
												<td>${test.point}</td>
											</c:when>
											<%-- 科目別検索の結果行 --%>
											<c:otherwise>
												<td>${test.entYear}</td>
												<td>${test.classNum}</td>
												<td>${test.studentNo}</td>
												<td>${test.studentName}</td>
												<td>${test.point1 != null ? test.point1 : '-'}</td>
												<td>${test.point2 != null ? test.point2 : '-'}</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					
					<%-- 2. 検索結果0件 --%>
					<c:when test="${tests != null}">
						<div class="px-2">成績情報が存在しませんでした。</div>
					</c:when>

					<%-- 3. 初期状態 --%>
					<c:otherwise>
						<p class="text-primary small px-2">
							科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
						</p>
					</c:otherwise>
				</c:choose>
			</div>

		</section>

	</c:param>

</c:import>