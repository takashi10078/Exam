package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ取得
		String no = req.getParameter("no");

		// DAO
		StudentDao studentDao = new StudentDao();
		ClassNumDao classNumDao = new ClassNumDao();

		// 学生取得
		Student student = studentDao.get(no);

		// クラス一覧取得
		List<String> classList = classNumDao.filter(teacher.getSchool());

		// JSPへ値セット
		req.setAttribute("ent_year", student.getEntYear());
		req.setAttribute("no", student.getNo());
		req.setAttribute("name", student.getName());
		req.setAttribute("class_num", student.getClassNum());
		req.setAttribute("is_attend", student.isAttend());
		req.setAttribute("class_num_set", classList);

		// JSPへフォワード
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}