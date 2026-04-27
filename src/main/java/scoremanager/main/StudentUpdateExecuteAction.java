package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ取得
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		int entYear = Integer.parseInt(req.getParameter("ent_year"));
		String classNum = req.getParameter("class_num");
		String isAttendStr = req.getParameter("is_attend");

		// チェックボックス判定
		boolean isAttend = false;
		if (isAttendStr != null) {
			isAttend = true;
		}

		// Studentオブジェクト作成
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);

		// Schoolセット（重要）
		School school = teacher.getSchool();
		student.setSchool(school);

		// DB更新
		StudentDao dao = new StudentDao();
		dao.save(student);

		// JSPへフォワード
		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}
}