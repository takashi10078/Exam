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
		
		// セッション取得 / session ကိုယူခြင်း
		HttpSession httpSession = req.getSession();
		
		// ログインユーザー取得 / login user ကိုယူခြင်း
		Teacher teacher = (Teacher) httpSession.getAttribute("user");
		
		// 削除対象コード取得 / delete လုပ်မယ့် subject code ကိုယူခြင်း
		String cd = req.getParameter("cd");
		
		// DAO作成 / DAO ဖန်တီးခြင်း
		SubjectDao dao = new SubjectDao();
		
		// データ削除 / database ထဲက subject ကိုဖျက်ခြင်း
		dao.delete(cd, teacher.getSchool().getCd());
		
		// 完了画面へ遷移 / success page သို့သွားခြင်း
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}