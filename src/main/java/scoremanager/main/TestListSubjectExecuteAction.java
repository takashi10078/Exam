package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
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

        // 1. リクエストパラメーターの取得と整形
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        
        // URLパラメータに空白が含まれる場合(201++)への対策
        if (classNum != null) {
            classNum = classNum.trim();
        }

        int entYear = 0;
        List<TestListSubject> list = null;
        
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // 2. ビジネスロジック（型変換）
        if (entYearStr != null && !entYearStr.equals("")) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                entYear = 0;
            }
        }

        // 3. 共通データの準備（プルダウン表示用）
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        List<String> classNumList = classNumDao.filter(teacher.getSchool());
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

        // JSPへ値を引き継ぐ（選択状態の維持）
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subject_set", subjectList);
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);

        // 4. バリデーションチェック
        if (entYear == 0 || classNum == null || classNum.equals("") || subjectCd == null || subjectCd.equals("")) {
            // パラメータが足りない場合は検索を行わず、メッセージを表示
            if (entYearStr != null) {
                req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            }
            req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
            return;
        }

        // 5. DBからデータ取得（メイン処理）
        // 科目コードから科目情報を取得
        Subject subject = subjectDao.get(subjectCd, teacher.getSchool());
        
        if (subject != null) {
            // 成績一覧を抽出
            list = testListSubjectDao.filter(entYear, classNum, subject, teacher.getSchool());
        }

        // 6. 結果をセットしてJSPへ
        req.setAttribute("subject", subject);
        req.setAttribute("tests", list); 

        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}