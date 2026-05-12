package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	//SQL文の定義
    private String baseSql = 
        "select t.subject_cd, sub.name as subject_name, t.no as count, t.point " +
        "from test t " +
        "join subject sub on t.subject_cd = sub.cd and t.school_cd = sub.school_cd " +
        "where t.student_no = ? and t.point is not null ";

    /**
     * 学生を指定して成績一覧（学生別）を取得する
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // 並び順を指定
        String sql = baseSql + "order by t.subject_cd asc, t.no asc";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNo());

            resultSet = statement.executeQuery();
            // 変換処理を外部メソッドに委譲
            list = postFilter(resultSet);

        } finally {
            // リソースの解放を確実に行う
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    /**
     * ResultSetの結果をTestListStudentのリストに変換する（後処理）
     */
    private List<TestListStudent> postFilter(ResultSet resultSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        while (resultSet.next()) {
            TestListStudent test = new TestListStudent();
            
            test.setSubjectCd(resultSet.getString("subject_cd"));
            test.setSubjectName(resultSet.getString("subject_name"));
            test.setCount(resultSet.getInt("count"));
            test.setPoint(resultSet.getInt("point"));

            list.add(test);
        }

        return list;
    }
}