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
        List<TestListStudent> list = new java.util.ArrayList<>();
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
            else {
                list = new java.util.ArrayList<>();
            }
        }

        // 4. レスポンス値をセット
        req.setAttribute("f4", studentNo); // 入力値の保持用
        req.setAttribute("student", student);
        req.setAttribute("tests", list);
        
        java.time.LocalDate todaysDate = java.time.LocalDate.now();
        int year = todaysDate.getYear();
        java.util.List<Integer> entYearSet = new java.util.ArrayList<>();
        for (int i = year - 10; i < year+11; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

       
        dao.ClassNumDao classNumDao = new dao.ClassNumDao();
        java.util.List<String> classNumList = classNumDao.filter(teacher.getSchool());
        req.setAttribute("class_num_set", classNumList);

        
        dao.SubjectDao subjectDao = new dao.SubjectDao();
        java.util.List<bean.Subject> subjectList = subjectDao.filter(teacher.getSchool());
        req.setAttribute("subject_set", subjectList);

        // 5. JSPへフォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}