package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. ローカル変数の初期化
        String studentNo = "";
        List<TestListStudent> list = null;
        Student student = null;

        StudentDao studentDao = new StudentDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();

        // 2. リクエストパラメーターの取得
        studentNo = req.getParameter("f4"); // JSPのname="f4"（学生番号）

        // 3. ビジネスロジック・DBからデータ取得
        if (studentNo != null && !studentNo.equals("")) {
            // 学生情報を取得
            student = studentDao.get(studentNo);
            
            if (student != null) {
                // 学生が存在する場合、その学生の成績リストを取得
                list = testListStudentDao.filter(student);
            }
        }

        // 4. レスポンス値をセット
        req.setAttribute("f4", studentNo); // 入力値の保持用
        req.setAttribute("student", student);
        req.setAttribute("tests", list);

        // 5. JSPへフォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}