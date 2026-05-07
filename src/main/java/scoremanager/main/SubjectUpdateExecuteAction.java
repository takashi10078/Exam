package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		HttpSession httpSession = req.getSession();
		Teacher teacher = (Teacher) httpSession.getAttribute("user");
		
		
		SubjectDao dao = new SubjectDao();
		dao.update(cd, name, teacher.getSchool().getCd());
		
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}

}