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
		
		// DAO作成 / DAO ဖန်တီးခြင်း
		SubjectDao dao = new SubjectDao();
		
		// リクエストパラメータ取得 / update လုပ်မယ့် code ကိုယူခြင်း
		String cd = req.getParameter("cd");
		
		// セッション取得 / session ကိုယူခြင်း
		HttpSession session = req.getSession();
		
		// ログインユーザー取得 / login user ကိုယူခြင်း
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		// 科目情報取得 / subject data ကို DB ကနေယူခြင်း
		Subject subject = dao.get(cd, teacher.getSchool());
		
		// 画面表示用にセット / JSP မှာပြဖို့ attribute ထားခြင်း
		req.setAttribute("subject", subject);
		
		// 更新画面へ遷移 / update page သို့သွားခြင်း
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}