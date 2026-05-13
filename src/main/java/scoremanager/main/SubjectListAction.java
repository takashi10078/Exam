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
        
        // 学校情報取得 / teacher ရဲ့ school ကိုယူခြင်း
        School school = teacher.getSchool();

        // 科目一覧取得 / school အလိုက် subject list ကိုယူခြင်း
        List<Subject> subjects = dao.filter(school);

        // 画面表示用にセット / JSP မှာပြဖို့ attribute ထားခြင်း
        req.setAttribute("subjects", subjects);

        // 一覧画面へ遷移 / list page သို့သွားခြင်း
        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}