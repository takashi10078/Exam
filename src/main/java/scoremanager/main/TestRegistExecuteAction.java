package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Score;
import bean.Teacher;
import dao.ScoreDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

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
		ScoreDao scoreDao = new ScoreDao();
		List<Score> savedList = new ArrayList<>();
		List<String> errors = new ArrayList<>();

		// リクエストパラメーターの取得 2
		subjectCd = req.getParameter("subject_cd");
		classNum = req.getParameter("class_num");
		entYear = Integer.parseInt(req.getParameter("ent_year"));
		no = Integer.parseInt(req.getParameter("no"));
		String[] studentNos = req.getParameterValues("student_no");

		// ビジネスロジック 4
		if (studentNos != null) {
			for (String studentNo : studentNos) {
				String pointStr = req.getParameter("point_" + studentNo);

				// 空欄はスキップ
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

				// DBへデータ保存 5
				scoreDao.save(score);
				savedList.add(score);
			}
		}

		// レスポンス値をセット 6
		req.setAttribute("saved_list", savedList);
		req.setAttribute("errors", errors);
		req.setAttribute("sel_subject_cd", subjectCd);
		req.setAttribute("sel_class_num", classNum);
		req.setAttribute("sel_ent_year", entYear);
		req.setAttribute("sel_no", no);

		// JSPへフォワード 7
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}
