<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	
<%--JSTL利用設定 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%--共通レイアウト読込 --%>
<c:import url="/common/base.jsp" >

	<%--ページタイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%--JavaScript用 --%>
	<c:param name="scripts"></c:param>

	<%--メインコンテンツ開始 --%>
	<c:param name="content">
		<section>
		
			<%--画面タイトル --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
			
			<%--学生登録フォーム --%>
			<form action="StudentCreateExecute.action" method="get">
			
				<%--入学年度選択 --%>
				<div>
					<label for="ent_year">入学年度</label>
					<select class="form-select" id="ent_year" name="ent_year">
						<%--未選択option --%>
						<option value="0">--------</option>
						
						<%--入学年度一覧表示 --%>
						<c:forEach var="year" items="${ent_year_set }">
							
							<%--選択されていた年度を保持 --%>
							<option value="${year }" <c:if test="${year==ent_year }">selected</c:if>>${year }</option>
						</c:forEach>
					</select>
				</div>
				
				<%--入学年度エラーメッセージ --%>
				<div class="mt-2 text-warning">${errors.get("1") }</div>
				
				<%--学生番号入力 --%>
				<div>
					<label for="no">学生番号</label><br>
					<input class="form-control" type="text" id="no" name="no" value="${no }" required maxlength="10" placeholder="学生番号を入力してください" />
				</div>
				
				<%--学生番号エラーメッセージ --%>
				<div class="mt-2 text-warning">${errors.get("2") }</div>
				
				<%--氏名入力 --%>
				<div>
					<label for="name">氏名</label><br>
					<input class="form-control" type="text" id="name" name="name" value="${name }" required maxlength="30" placeholder="氏名を入力してください" />
				</div>
				
				<%--クラス選択 --%>
				<div class="mx-auto py-2">
					<label for="class_num">クラス</label>
					<select class="form-select" id="class_num" name="class_num">
					
						<%--クラス一覧 --%>
						<c:forEach var="num" items="${class_num_set }">
							<%-- 現在のnumと選択されていたclass_numが一致していた場合selectedを追記 --%>
							<option value="${num }" <c:if test="${num==class_num }">selected</c:if>>${num }</option>
						</c:forEach>
					</select>
				</div>
				
				<%--登録ボタン --%>
				<div class="mx-auto py-2">
					<button class="btn btn-secondary" id="create-button" name="end">登録して終了</button>
				</div>
			</form>
			<a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>