package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション・共通情報の取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ローカル変数の初期化
        String entYearStr = "";
        String classNum = "";
        String subjectCd = "";
        int entYear = 0;
        
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        // リクエストパラメータの取得
        entYearStr = req.getParameter("f1"); // 入学年度
        classNum = req.getParameter("f2");   // クラス番号
        subjectCd = req.getParameter("f3");  // 科目コード

        // ビジネスロジック (型変換など)
        if (entYearStr != null && !entYearStr.equals("")) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 入学年度リストの作成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year+11; i++) {
            entYearSet.add(i);
        }

        // DBからデータ取得
        // クラス一覧と科目一覧を取得
        List<String> classNumList = classNumDao.filter(teacher.getSchool());
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

        // レスポンス値をセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subject_set", subjectList);

        // JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}