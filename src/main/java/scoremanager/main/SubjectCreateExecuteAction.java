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
		
		// セッション取得 / session ကိုယူခြင်း
		HttpSession session = req.getSession();
		
		// ログインユーザー取得 / login user ကိုယူခြင်း
	    Teacher teacher = (Teacher) session.getAttribute("user");
 
	    // ログインチェック / login မဝင်ထားရင် login page သို့ပြန်ပို့ခြင်း
	    if (teacher == null) {
	        req.getRequestDispatcher("login.jsp").forward(req, res);
	        return;
	    }
 
		// DAO作成 / DAO ဖန်တီးခြင်း
		SubjectDao dao = new SubjectDao();
 
		// フォーム入力取得 / form data ကိုယူခြင်း
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		
		// 入力値保持 / error ဖြစ်ရင်ပြန်ပြရန် value သိမ်းခြင်း
		req.setAttribute("cd", cd);
		req.setAttribute("name", name);
		
		// 桁数チェック / code length စစ်ခြင်း
		if(cd == null || cd.length() != 3) {
			req.setAttribute("lengthError", "科目コードは3文字で入力してください。");
			req.getRequestDispatcher("subject_create.jsp").forward(req,res);
			return;
		}
		
		// 重複チェック / duplicate ရှိမရှိစစ်ခြင်း
		Subject subject = dao.get(cd, teacher.getSchool());
		
		if(subject != null) {
			req.setAttribute("duplicateError", "科目コードが重複しています");
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		
		
		Subject newSubject = new Subject();
		newSubject.setCd(cd);
		newSubject.setName(name);
		newSubject.setSchool(teacher.getSchool());
				
		// 登録処理 / database ထဲသို့သိမ်းခြင်း
		dao.save(newSubject);
		
		// 完了画面へ遷移 / success page သို့သွားခြင်း
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
 
}
 