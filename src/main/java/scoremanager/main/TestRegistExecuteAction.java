package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Score;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

//登録ボタンが押されたときの処理　バリデーションしてDBに保存する
public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// ローカル変数の指定 1
		String subjectCd = "";
		String classNum = "";
		int entYear = 0;
		int no = 1;
		TestDao testDao = new TestDao();
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		List<Score> savedList = new ArrayList<>();

		// リクエストパラメーターの取得 2
		subjectCd = req.getParameter("subject_cd");
		classNum = req.getParameter("class_num");
		entYear = Integer.parseInt(req.getParameter("ent_year"));
		no = Integer.parseInt(req.getParameter("no"));
		String[] studentNos = req.getParameterValues("student_no");

		// ビジネスロジック 4
		// 入力された点数を一時保持　エラーのとき入力値を消さないため
		Map<String, String> inputPoints = new HashMap<>();
		boolean hasError = false;

		if (studentNos != null) {
			for (String studentNo : studentNos) {
				String pointStr = req.getParameter("point_" + studentNo);
				if (pointStr == null || pointStr.equals("")) {
					inputPoints.put(studentNo, "");
					continue;
				}
				inputPoints.put(studentNo, pointStr);
				try {
					int point = Integer.parseInt(pointStr);
					// 0~100以外はエラー
					if (point < 0 || point > 100) {
						hasError = true;
					}
				} catch (NumberFormatException e) {
					hasError = true;
				}
			}
		}

		// エラーがあれば一覧画面に戻る
		if (hasError) {
			// 科目名を取得
			String subjectName = "";
			Subject subject = subjectDao.get(subjectCd, teacher.getSchool());
			if (subject != null) {
				subjectName = subject.getName();
			}

			// 入学年度リスト再構築
			LocalDate todaysDate = LocalDate.now();
			int year = todaysDate.getYear();
			List<Integer> entYearSet = new ArrayList<>();
			for (int i = year - 10; i <= year + 10; i++) {
				entYearSet.add(i);
			}

			// 学生リストを再取得してユーザーが入力した値を上書きする
			List<Score> scoreList = testDao.getRegistList(teacher.getSchool(), subjectCd, classNum, entYear, no);
			if (scoreList != null) {
				for (Score score : scoreList) {
					String inputVal = inputPoints.get(score.getStudentNo());
					if (inputVal != null && !inputVal.equals("")) {
						try {
							score.setPoint(Integer.parseInt(inputVal));
						} catch (NumberFormatException e) {
							score.setPoint(-999);
						}
					} else {
						score.setPoint(-1);
					}
				}
			}

			// ドロップダウン用のデータも再取得
			List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
			List<String> classNumList = classNumDao.filter(teacher.getSchool());

			// レスポンス値をセット（ドロップダウンの選択値も含めて全部渡す）
			req.setAttribute("subject_list", subjectList);
			req.setAttribute("class_num_set", classNumList);
			req.setAttribute("ent_year_set", entYearSet);
			req.setAttribute("score_list", scoreList);
			req.setAttribute("sel_subject_cd", subjectCd);
			req.setAttribute("sel_subject_name", subjectName);
			req.setAttribute("sel_class_num", classNum);
			req.setAttribute("sel_ent_year", entYear);
			req.setAttribute("sel_no", no);
			req.setAttribute("hasError", true);
			req.setAttribute("inputPoints", inputPoints);

			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
			return;
		}

		// DBへデータ保存 5
		if (studentNos != null) {
			for (String studentNo : studentNos) {
				String pointStr = inputPoints.get(studentNo);
				if (pointStr == null || pointStr.equals("")) {
					continue;
				}

				int point = Integer.parseInt(pointStr);

				Score score = new Score();
				score.setStudentNo(studentNo);
				score.setSubjectCd(subjectCd);
				score.setSchoolCd(teacher.getSchool().getCd());
				score.setNo(no);
				score.setPoint(point);
				score.setClassNum(classNum);

				testDao.save(score);
				savedList.add(score);
			}
		}

		// レスポンス値をセット 6
		req.setAttribute("saved_list", savedList);

		// JSPへフォワード 7
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}
