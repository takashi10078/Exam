package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

//成績管理一覧画面　検索条件を受け取って学生と得点を表示する
public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// ローカル変数の指定 1
		String subjectCd = "";
		String classNum = "";
		String entYearStr = "";
		String noStr = "";
		int entYear = 0;
		int no = 0;
		List<Score> scoreList = null;
		String subjectName = "";
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		TestDao scoreDao = new TestDao();

		// リクエストパラメーターの取得 2
		subjectCd = req.getParameter("subject_cd");
		classNum = req.getParameter("class_num");
		entYearStr = req.getParameter("ent_year");
		noStr = req.getParameter("no");

		// DBからデータ取得 3
		List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
		List<String> classNumList = classNumDao.filter(teacher.getSchool());

		// ビジネスロジック 4
		// 入学年度のリストを作る　現在から前後10年
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year + 10; i++) {
			entYearSet.add(i);
		}

		if (entYearStr != null && !entYearStr.equals("") && !entYearStr.equals("0")) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (noStr != null && !noStr.equals("") && !noStr.equals("0")) {
			no = Integer.parseInt(noStr);
		}

		// 科目と回数が両方選ばれたときだけ学生一覧を取得する
		if (subjectCd != null && !subjectCd.equals("") && no != 0) {
			scoreList = scoreDao.getRegistList(teacher.getSchool(), subjectCd, classNum, entYear, no);

			// 画面に表示する科目名を取得
			Subject subject = subjectDao.get(subjectCd, teacher.getSchool());
			if (subject != null) {
				subjectName = subject.getName();
			}
		}

		// レスポンス値をセット 6
		req.setAttribute("subject_list", subjectList);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("score_list", scoreList);
		req.setAttribute("sel_subject_cd", subjectCd);
		req.setAttribute("sel_subject_name", subjectName);
		req.setAttribute("sel_class_num", classNum);
		req.setAttribute("sel_ent_year", entYear);
		req.setAttribute("sel_no", no);
		req.setAttribute("inputPoints", null);
		req.setAttribute("hasError", false);

		// JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
