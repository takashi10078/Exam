package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    //SQL文の定義
    private String baseSql =
            " select" +
            "     st.ent_year, t.class_num, st.no as student_no, st.name as student_name, t.no as test_no, t.point" +
            " from test t" +
            " join student st on t.student_no = st.no and t.school_cd = st.school_cd" +
            " where st.ent_year = ? and t.class_num = ? and t.subject_cd = ? and t.school_cd = ?";

    /**
     * 指定した条件で成績一覧（科目別）を取得する
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // 並び順を指定
        String sql = baseSql + "order by st.no asc, t.no asc";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setString(4, school.getCd());

            resultSet = statement.executeQuery();
            // 結果セットをリストに変換
            list = postFilter(resultSet);

        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    /**
     * ResultSetの結果を学生ごとのリストにまとめる（後処理）
     */
    private List<TestListSubject> postFilter(ResultSet resultSet) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        // 現在処理中の学生情報を保持
        TestListSubject testListSubject = null;

        // 直前の学生番号を保持
        String currentStudentNo = "";

        while (resultSet.next()) {

            String studentNo = resultSet.getString("student_no");

            // 学生番号が変わった場合、新しいインスタンスを作成
            if (!studentNo.equals(currentStudentNo)) {

                testListSubject = new TestListSubject();

                testListSubject.setEntYear(resultSet.getInt("ent_year"));
                testListSubject.setClassNum(resultSet.getString("class_num"));
                testListSubject.setStudentNo(studentNo);
                testListSubject.setStudentName(resultSet.getString("student_name"));

                // リストへ追加
                list.add(testListSubject);

                // 現在の学生番号を保存
                currentStudentNo = studentNo;
            }

            // テスト回数(test_no)に応じて得点をセット
            int testNo = resultSet.getInt("test_no");
            Integer point = (Integer) resultSet.getObject("point");

            if (testNo == 1) {
                testListSubject.setPoint1(point);
            } else if (testNo == 2) {
                testListSubject.setPoint2(point);
            }
        }

        return list;
    }
}