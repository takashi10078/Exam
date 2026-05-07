package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Score;
import bean.Student;

public class ScoreDao extends Dao {

	public Score get(String studentNo, String subjectCd, String schoolCd, int no) throws Exception {

		Score score = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
				"select * from test where student_no=? and subject_cd=? and school_cd=? and no=?"
			);

			statement.setString(1, studentNo);
			statement.setString(2, subjectCd);
			statement.setString(3, schoolCd);
			statement.setInt(4, no);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				score = new Score();
				score.setStudentNo(resultSet.getString("student_no"));
				score.setSubjectCd(resultSet.getString("subject_cd"));
				score.setSchoolCd(resultSet.getString("school_cd"));
				score.setNo(resultSet.getInt("no"));
				score.setPoint(resultSet.getInt("point"));
				score.setClassNum(resultSet.getString("class_num"));
			}

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return score;
	}

	// 学生一覧と既存得点を取得する（LEFT JOINで得点がない学生も含む）
	public List<Score> getRegistList(School school, String subjectCd,
			String classNum, int entYear, int no) throws Exception {

		List<Score> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "select s.no as student_no, s.name as student_name, "
					+ "s.ent_year, s.class_num, t.point, t.no as test_no "
					+ "from student s "
					+ "left join test t on s.no = t.student_no "
					+ "and t.subject_cd = ? and t.school_cd = ? and t.no = ? "
					+ "where s.school_cd = ? and s.is_attend = true";

			if (classNum != null && !classNum.equals("0") && !classNum.isEmpty()) {
				sql += " and s.class_num = ?";
			}
			if (entYear != 0) {
				sql += " and s.ent_year = ?";
			}
			sql += " order by s.no asc";

			statement = connection.prepareStatement(sql);

			int idx = 1;
			statement.setString(idx++, subjectCd);
			statement.setString(idx++, school.getCd());
			statement.setInt(idx++, no);
			statement.setString(idx++, school.getCd());

			if (classNum != null && !classNum.equals("0") && !classNum.isEmpty()) {
				statement.setString(idx++, classNum);
			}
			if (entYear != 0) {
				statement.setInt(idx++, entYear);
			}

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Score score = new Score();
				score.setStudentNo(resultSet.getString("student_no"));
				score.setSubjectCd(subjectCd);
				score.setSchoolCd(school.getCd());
				score.setNo(no);
				score.setClassNum(resultSet.getString("class_num"));

				// 得点がない場合は-1をセット
				Object pointObj = resultSet.getObject("point");
				if (pointObj != null) {
					score.setPoint(resultSet.getInt("point"));
				} else {
					score.setPoint(-1);
				}

				Student student = new Student();
				student.setNo(resultSet.getString("student_no"));
				student.setName(resultSet.getString("student_name"));
				student.setEntYear(resultSet.getInt("ent_year"));
				student.setClassNum(resultSet.getString("class_num"));
				student.setSchool(school);
				score.setStudent(student);

				list.add(score);
			}

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return list;
	}

	public boolean save(Score score) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			// 既存データを確認
			Score existing = get(score.getStudentNo(), score.getSubjectCd(),
					score.getSchoolCd(), score.getNo());

			if (existing == null) {
				// 新規登録
				statement = connection.prepareStatement(
					"insert into test (student_no, subject_cd, school_cd, no, point, class_num) "
					+ "values (?,?,?,?,?,?)"
				);
				statement.setString(1, score.getStudentNo());
				statement.setString(2, score.getSubjectCd());
				statement.setString(3, score.getSchoolCd());
				statement.setInt(4, score.getNo());
				statement.setInt(5, score.getPoint());
				statement.setString(6, score.getClassNum());
			} else {
				// 更新
				statement = connection.prepareStatement(
					"update test set point=? where student_no=? and subject_cd=? and school_cd=? and no=?"
				);
				statement.setInt(1, score.getPoint());
				statement.setString(2, score.getStudentNo());
				statement.setString(3, score.getSubjectCd());
				statement.setString(4, score.getSchoolCd());
				statement.setInt(5, score.getNo());
			}

			count = statement.executeUpdate();

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return count > 0;
	}
}
