<%-- 共通テンプレート(base.jsp) --%>

<%-- JSP基本設定 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL利用設定 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ja">

<head>

	<%-- 文字コード設定 --%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%-- レスポンシブ対応設定 --%>
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<%-- Bootstrap CSS読み込み --%>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
		crossorigin="anonymous">

	<%-- ページタイトル表示 --%>
	<title>${param.title}</title>

	<%-- jQuery読み込み --%>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

	<%-- 各JSPから渡されたJavaScriptを表示 --%>
	${param.scripts}

</head>

<body>

	<%-- 画面全体ラッパー --%>
	<div id="wrapper" class="container">

		<%-- ヘッダー部分 --%>
		<header
			class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">

			<%-- header.jsp読み込み --%>
			<c:import url="/common/header.jsp" />
		</header>

		<%-- メインレイアウト --%>
		<div class="row justify-content-center">

			<%-- 条件分岐 --%>
			<c:choose>

				<%-- ログイン済みの場合 --%>
				<c:when test="${user.isAuthenticated()}">

					<%-- ナビゲーションメニュー表示 --%>
					<nav class="col-3" style="height:40rem;">

						<%-- navigation.jsp読み込み --%>
						<c:import url="/common/navigation.jsp" />
					</nav>

					<%-- メインコンテンツ表示 --%>
					<main class="col-9 border-start">
						${param.content}
					</main>

				</c:when>

				<%-- 未ログインの場合 --%>
				<c:otherwise>

					<%-- ログイン画面などを中央表示 --%>
					<main class="col-8">
						${param.content}
					</main>

				</c:otherwise>
			</c:choose>
		</div>

		<%-- フッター部分 --%>
		<footer
			class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3 align-bottom">

			<%-- footer.jsp読み込み --%>
			<c:import url="/common/footer.jsp" />
		</footer>

	</div>
</body>
</html>