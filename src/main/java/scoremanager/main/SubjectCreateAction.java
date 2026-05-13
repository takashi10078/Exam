package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 画面遷移：科目作成ページへフォワード
		// Myanmar: Subject create page ကို forward လုပ်ပေးခြင်း
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}
}