<%-- 科目別成績一覧 --%>
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

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績一覧（科目）
			</h2>
			
            <%-- 検索エリア全体 --%>
            <div class="border rounded p-4 mb-4">
                
                <%-- 科目情報フォーム --%>
                <form action="TestListSubjectExecute.action" method="get" class="mb-0">
                    <input type="hidden" name="f" value="sj">
                    
                    <div class="row align-items-center">
                        <div class="col-2">
                            <p class="mb-0 text-center">科目情報</p>
                        </div>
                        
                        <div class="col-8">
                            <div class="row">
                                <div class="col-4">
                                    <label class="form-label">入学年度</label>
                                    <select name="f1" class="form-select">
                                        <option value="0">--------</option>
                                        <c:forEach var="year" items="${ent_year_set}">
                                            <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <label class="form-label">クラス</label>
                                    <select name="f2" class="form-select">
                                        <option value="0">--------</option>
                                        <c:forEach var="num" items="${class_num_set}">
                                            <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <label class="form-label">科目</label>
                                    <select name="f3" class="form-select">
                                        <option value="0">--------</option>
                                        <c:forEach var="subject" items="${subject_set}">
                                            <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-2 text-end">
                            <button type="submit" class="btn btn-secondary px-4">検索</button>
                        </div>
                    </div>
                </form>
                
                <c:if test="${error != null}">
                    <p class="text-warning small mt-2 ms-5">
                        ${error}
                    </p>
                </c:if>

                <hr class="my-4 text-secondary opacity-25">

                <%-- 学生情報フォーム --%>
                <form action="TestListStudentExecute.action" method="get">
                    <input type="hidden" name="f" value="st">

                    <div class="row align-items-center">
                        <div class="col-2">
                            <p class="mb-0 text-center">学生情報</p>
                        </div>

                        <div class="col-8">
                            <div class="row">
                                <div class="col-5">
                                    <label class="form-label">学生番号</label>
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

                        <div class="col-2 text-end">
                            <button type="submit" class="btn btn-secondary px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>

			<%-- 結果表示 --%>
			<div class="mt-4 px-2">
				<c:choose>
					<c:when test="${tests != null && tests.size() > 0}">
						
				
						<c:if test="${subject != null}">
							<div class="mb-3 px-2">科目：${subject.name}</div>
						</c:if>

						<table class="table table-hover">
						    <thead>
						        <tr>
						            <th>入学年度</th>
						            <th>クラス</th>
						            <th>学生番号</th>
						            <th>氏名</th>
						            <th>1回</th>
						            <th>2回</th>
						        </tr>
						    </thead>
						    <tbody>
						        <c:forEach var="test" items="${tests}">
						            <tr>
						                <td>${test.entYear}</td>
						                <td>${test.classNum}</td>
						                <td>${test.studentNo}</td>
						                <td>${test.studentName}</td>
						                <td>${test.point1 != null ? test.point1 : '-'}</td>
						                <td>${test.point2 != null ? test.point2 : '-'}</td>
						            </tr>
						        </c:forEach>
						    </tbody>
						</table>
					</c:when>
					
					<%-- 学生がいなかった場合 --%>
					<c:when test="${tests != null}">
						<div class="px-2">学生情報が存在しませんでした。</div>
					</c:when>

				</c:choose>
			</div>

		</section>

	</c:param>

</c:import>