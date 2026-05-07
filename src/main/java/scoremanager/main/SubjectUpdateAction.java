package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		SubjectDao dao = new SubjectDao();
		
		String cd = req.getParameter("cd");
		
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		Subject subject = dao.get(cd, teacher.getSchool());
		req.setAttribute("subject", subject);
		
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	
	

}
