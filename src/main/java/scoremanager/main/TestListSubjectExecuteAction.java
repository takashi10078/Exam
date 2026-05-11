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
        if (entYear == 0 || classNum == null || classNum.equals("")
                || subjectCd == null || subjectCd.equals("")) {

            req.setAttribute("error", "入学年度とクラスと科目を選択してください");

            // プルダウン再表示用
            java.time.LocalDate todaysDate = java.time.LocalDate.now();
            int year = todaysDate.getYear();

            java.util.ArrayList<Integer> entYearSet = new java.util.ArrayList<>();

            for (int i = year - 10; i <= year; i++) {
                entYearSet.add(i);
            }

            req.setAttribute("ent_year_set", entYearSet);

            dao.ClassNumDao classNumDao = new dao.ClassNumDao();
            List<String> classNumList = classNumDao.filter(teacher.getSchool());
            req.setAttribute("class_num_set", classNumList);

            List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
            req.setAttribute("subject_set", subjectList);

            // 元画面へ戻す
            req.getRequestDispatcher("test_list.jsp").forward(req, res);

            return;
        }

        // 5. レスポンス値をセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("subject", subject);
        req.setAttribute("tests", list);
        
        java.time.LocalDate todaysDate = java.time.LocalDate.now();
        int year = todaysDate.getYear();
        java.util.ArrayList<Integer> entYearSet = new java.util.ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        // クラス番号一覧を取得してセット
        dao.ClassNumDao classNumDao = new dao.ClassNumDao();
        List<String> classNumList = classNumDao.filter(teacher.getSchool());
        req.setAttribute("class_num_set", classNumList);

        // 科目一覧を取得してセット
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
        req.setAttribute("subject_set", subjectList);

        // 6. JSPへフォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}