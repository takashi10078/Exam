package scoremanager.main;

import bean.Subject;
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
		
		if(teacher == null) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
		}
		
		
		SubjectDao dao = new SubjectDao();
		
		Subject subject = dao.get(cd, teacher.getSchool());
		if (subject == null) {
			
			Subject inputSubject = new Subject();
			inputSubject.setCd(cd);
			inputSubject.setName(name);
			req.setAttribute("subject", inputSubject);
			req.setAttribute("error", "科目が存在していません");
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}
		dao.update(cd, name, teacher.getSchool().getCd());
		
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}

}