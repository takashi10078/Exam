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
		
		// フォーム入力取得 / form data ကိုယူခြင်း
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		
		// セッション取得 / session ကိုယူခြင်း
		HttpSession httpSession = req.getSession();
		
		// ログインユーザー取得 / login user ကိုယူခြင်း
		Teacher teacher = (Teacher) httpSession.getAttribute("user");
		
		// ログインチェック / login မဝင်ထားရင် login page သို့ပြန်ပို့ခြင်း
		if (teacher == null) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
		}
		
		// DAO作成 / DAO ဖန်တီးခြင်း
		SubjectDao dao = new SubjectDao();
		
		// 科目存在チェック / subject ရှိမရှိစစ်ခြင်း
		Subject subject = dao.get(cd, teacher.getSchool());
		
		if (subject == null) {
			
			// エラー用データ作成 / error ပြဖို့ data ဖန်တီးခြင်း
			Subject inputSubject = new Subject();
			inputSubject.setCd(cd);
			inputSubject.setName(name);
			
			// 画面へ返す / JSP ကိုပြန်ပို့ခြင်း
			req.setAttribute("subject", inputSubject);
			req.setAttribute("error", "科目が存在していません");
			
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}
		Subject updatedSubject = new Subject();
		updatedSubject.setCd(cd);
		updatedSubject.setName(name);
		updatedSubject.setSchool(teacher.getSchool());
		
		
		// 更新処理 / database update လုပ်ခြင်း
		dao.save(updatedSubject);
		
		// 完了画面へ遷移 / success page သို့သွားခြင်း
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}