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
					<div class="col-3">
						<label class="form-label" for="subject-select">科目</label>
						<select class="form-select" id="subject-select" name="subject_cd">
							<option value="">--------</option>
							<c:forEach var="subj" items="${subject_list}">
								<option value="${subj.cd}"
									<c:if test="${subj.cd == sel_subject_cd}">selected</c:if>>
									${subj.cd}：${subj.name}
								</option>
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
						<label class="form-label" for="no-select">試験回数</label>
						<select class="form-select" id="no-select" name="no">
							<c:forEach begin="1" end="5" var="i">
								<option value="${i}"
									<c:if test="${i == sel_no}">selected</c:if>>${i}回</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center" style="margin-top:1.8rem">
						<button class="btn btn-secondary" id="filter-button">絞込み</button>
					</div>
				</div>
			</form>

			<c:choose>
				<c:when test="${score_list == null}">
					<%-- 絞込み前は何も表示しない --%>
				</c:when>
				<c:when test="${score_list.size() > 0}">
					<div class="mx-3 mb-2">検索結果：${score_list.size()}件</div>
					<form action="TestRegistExecute.action" method="post">
						<input type="hidden" name="subject_cd" value="${sel_subject_cd}">
						<input type="hidden" name="class_num" value="${sel_class_num}">
						<input type="hidden" name="ent_year" value="${sel_ent_year}">
						<input type="hidden" name="no" value="${sel_no}">
						<table class="table table-hover mx-3">
							<tr>
								<th>学生番号</th>
								<th>氏名</th>
								<th>クラス</th>
								<th class="text-center">得点</th>
							</tr>
							<c:forEach var="score" items="${score_list}">
								<input type="hidden" name="student_no" value="${score.studentNo}">
								<tr>
									<td>${score.studentNo}</td>
									<td>${score.student.name}</td>
									<td>${score.classNum}</td>
									<td class="text-center">
										<input type="number" class="form-control form-control-sm d-inline-block"
											style="width:80px"
											name="point_${score.studentNo}"
											min="0" max="100"
											value="<c:if test='${score.point >= 0}'>${score.point}</c:if>" />
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="mx-3 mt-2">
							<button type="submit" class="btn btn-primary">登録</button>
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
