package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
			
		//セッション取得
		HttpSession session = req.getSession(); 
		//セッションからログインユーザ情報取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		//クラス番号DAO生成
		ClassNumDao classNumDao = new ClassNumDao(); 
		
		//現在日付取得
		LocalDate todaysDate = LocalDate.now(); 
		
		//現在の年を取得
		int year = todaysDate.getYear(); // 現在の年を取得

		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = classNumDao.filter(teacher.getSchool());


		//入学年度一覧リスト生成
		List<Integer> entYearSet = new ArrayList<>();
		
		// 10年前から10年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}


		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード 
		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}

}
