<%-- 成績管理一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2">
						<label class="form-label" for="year-select">入学年度</label>
						<select class="form-select" id="year-select" name="ent_year">
							<option value="0">--------</option>
							<c:forEach var="yr" items="${ent_year_set}">
								<option value="${yr}"
									<c:if test="${yr == sel_ent_year}">selected</c:if>>${yr}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="class-select">クラス</label>
						<select class="form-select" id="class-select" name="class_num">
							<option value="0">--------</option>
							<c:forEach var="cn" items="${class_num_set}">
								<option value="${cn}"
									<c:if test="${cn == sel_class_num}">selected</c:if>>${cn}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-3">
						<label class="form-label" for="subject-select">科目</label>
						<select class="form-select" id="subject-select" name="subject_cd">
							<option value="">--------</option>
							<c:forEach var="subj" items="${subject_list}">
								<option value="${subj.cd}"
									<c:if test="${subj.cd == sel_subject_cd}">selected</c:if>>
									${subj.name}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="no-select">回数</label>
						<select class="form-select" id="no-select" name="no">
							<option value="0">--------</option>
							<c:forEach begin="1" end="2" var="i">
								<option value="${i}"
									<c:if test="${i == sel_no}">selected</c:if>>${i}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center" style="margin-top:1.8rem">
						<button class="btn btn-secondary">検索</button>
					</div>
				</div>
			</form>

			<c:choose>
				<c:when test="${score_list == null}">
					<%-- 検索前は何も表示しない --%>
				</c:when>
				<c:when test="${score_list.size() > 0}">
					<div class="mx-3 mb-2">科目：${sel_subject_name}（${sel_no}回）</div>

					<form action="TestRegistExecute.action" method="post">
						<input type="hidden" name="subject_cd" value="${sel_subject_cd}">
						<input type="hidden" name="class_num" value="${sel_class_num}">
						<input type="hidden" name="ent_year" value="${sel_ent_year}">
						<input type="hidden" name="no" value="${sel_no}">
						<table class="table table-hover mx-3">
							<colgroup>
								<col style="width:150px">
								<col style="width:120px">
								<col style="width:150px">
								<col style="width:250px">
								<col style="width:200px">
							</colgroup>
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>点数</th>
							</tr>
							<c:forEach var="score" items="${score_list}">
								<input type="hidden" name="student_no" value="${score.studentNo}">
								<tr>
									<td>${score.student.entYear}</td>
									<td>${score.classNum}</td>
									<td>${score.studentNo}</td>
									<td>${score.student.name}</td>
									<td>
										<input type="text" class="form-control form-control-sm"
											style="width:120px"
											name="point_${score.studentNo}"
											value="<c:choose><c:when test='${inputPoints != null}'>${inputPoints[score.studentNo]}</c:when><c:when test='${score.point >= 0}'>${score.point}</c:when></c:choose>" />
										<c:if test="${errorMap[score.studentNo]}">
											<span style="color:orange; font-size:0.8em;">0〜100の範囲で入力してください</span>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="mx-3 mt-2">
							<button type="submit" class="btn btn-secondary">登録して終了</button>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<div class="mx-3">学生情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>

		</section>
	</c:param>
</c:import>
