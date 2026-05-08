package scoremanager.main;

import bean.Subject;
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
	        return;
	    }

		// TODO 自動生成されたメソッド・スタブ
		SubjectDao dao = new SubjectDao();
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		
		req.setAttribute("cd", cd);
		req.setAttribute("name",name);
		
		if(cd == null || cd.length() != 3) {
			req.setAttribute("lengthError", "科目コードは3文字で入力してください。");
			req.getRequestDispatcher("subject_create.jsp").forward(req,res);
			return;
		}
		
		
		Subject subject = dao.get(cd,teacher.getSchool());
		
		if(subject != null) {
			req.setAttribute("duplicateError", "科目コードはすでに存在しています。");
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
			
		}
				
		dao.create(cd, name, teacher.getSchool().getCd());
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}

}
