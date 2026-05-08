package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. ローカル変数の初期化
        String entYearStr = "";
        String classNum = "";
        String subjectCd = "";
        int entYear = 0;
        List<TestListSubject> list = null;
        
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        // 2. リクエストパラメーターの取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        subjectCd = req.getParameter("f3");

        // 3. ビジネスロジック
        if (entYearStr != null && !entYearStr.equals("")) {
            entYear = Integer.parseInt(entYearStr);
        }

        // DBから科目情報を取得
        Subject subject = subjectDao.get(subjectCd, teacher.getSchool());

        // 4. DBからデータ取得（成績一覧のフィルタリング）
        if (entYear != 0 && classNum != null && subject != null) {
            list = testListSubjectDao.filter(entYear, classNum, subject, teacher.getSchool());
        }

        // 5. レスポンス値をセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("subject", subject);
        req.setAttribute("tests", list);

        // 6. JSPへフォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}