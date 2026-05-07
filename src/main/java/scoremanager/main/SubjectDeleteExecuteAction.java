package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession httpSession = req.getSession();
		Teacher teacher = (Teacher) httpSession.getAttribute("user");
		String cd = req.getParameter("cd");
		
		SubjectDao dao = new SubjectDao();
		dao.delete(cd, teacher.getSchool().getCd());
		
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
		
	}

}
