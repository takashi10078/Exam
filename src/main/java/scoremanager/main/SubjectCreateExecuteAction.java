package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");

	        // 🔴 login check
	    if (teacher == null) {
	        req.getRequestDispatcher("login.jsp").forward(req, res);
	    }

		// TODO 自動生成されたメソッド・スタブ
		SubjectDao dao = new SubjectDao();
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
				
		dao.create(cd, name, teacher.getSchool().getCd());
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}

}
