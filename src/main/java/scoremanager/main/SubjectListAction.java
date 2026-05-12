package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	//セッション取得
        HttpSession session = req.getSession();
        
        //セッションからログインユーザー取得
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        //ログインチェック
        if (teacher == null) {
        	
        	//ログインしていない場合ログイン画面へ
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
        
        //SubjectDao作成
        SubjectDao dao = new SubjectDao();
        
        //ログイン
        School school = teacher.getSchool();

        List<Subject> subjects = dao.filter(school);

        req.setAttribute("subjects", subjects);

        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}