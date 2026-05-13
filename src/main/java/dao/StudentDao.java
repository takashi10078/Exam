package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    // Student table အတွက် အခြေခံ SQL
    // Studentテーブル用の基本SQL
    private String baseSql = "select * from student where school_cd = ?";

    // Student တစ်ယောက်ကို no ဖြင့်ရှာယူ
    // noを使ってStudentを1件取得
    public Student get(String no) throws Exception {

        // Student object ကြေညာ
        // Studentオブジェクトを宣言
        Student student = null;

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // SQL execute အတွက် PreparedStatement
        // SQL実行用のPreparedStatement
        PreparedStatement statement = null;

        try {

            // Student table မှ no တူသော data ရှာ
            // Studentテーブルからnoが一致するデータを検索
            statement = connection.prepareStatement(
                "select * from student where no=?"
            );

            // no parameter ထည့်
            // noパラメータを設定
            statement.setString(1, no);

            // SQL execute
            // SQL実行
            ResultSet resultSet = statement.executeQuery();

            // Data ရှိလျှင်
            // データが存在する場合
            if (resultSet.next()) {

                // Student object ဖန်တီး
                // Studentオブジェクトを作成
                student = new Student();

                // student number set
                // student numberを設定
                student.setNo(resultSet.getString("no"));

                // student name set
                // student nameを設定
                student.setName(resultSet.getString("name"));

                // entrance year set
                // entrance yearを設定
                student.setEntYear(resultSet.getInt("ent_year"));

                // class number set
                // class numberを設定
                student.setClassNum(resultSet.getString("class_num"));

                // attendance status set
                // attendance statusを設定
                student.setAttend(resultSet.getBoolean("is_attend"));

                // School object ဖန်တီး
                // Schoolオブジェクトを作成
                School school = new School();

                // school code set
                // school codeを設定
                school.setCd(resultSet.getString("school_cd"));

                // School object ကို Student ထဲထည့်
                // SchoolオブジェクトをStudentにセット
                student.setSchool(school);
            }

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // Student object return
        // Studentオブジェクトを返す
        return student;
    }

    // ResultSet မှ Student list ပြောင်း
    // ResultSetからStudentリストへ変換
    private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {

        // Student list ဖန်တီး
        // Studentリストを作成
        List<Student> list = new ArrayList<>();

        // ResultSet loop
        // ResultSetをループ
        while (resultSet.next()) {

            // Student object ဖန်တီး
            // Studentオブジェクトを作成
            Student student = new Student();

            // student number set
            // student numberを設定
            student.setNo(resultSet.getString("no"));

            // student name set
            // student nameを設定
            student.setName(resultSet.getString("name"));

            // entrance year set
            // entrance yearを設定
            student.setEntYear(resultSet.getInt("ent_year"));

            // class number set
            // class numberを設定
            student.setClassNum(resultSet.getString("class_num"));

            // attendance status set
            // attendance statusを設定
            student.setAttend(resultSet.getBoolean("is_attend"));

            // school object set
            // schoolオブジェクトを設定
            student.setSchool(school);

            // list ထဲထည့်
            // listへ追加
            list.add(student);
        }

        // Student list return
        // Studentリストを返す
        return list;
    }

    // year နှင့် class အလိုက် Student list ရှာ
    // yearとclassでStudent一覧を検索
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        // Student list ကြေညာ
        // Studentリストを宣言
        List<Student> list;

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        // SQL create
        // SQL作成
        String sql = baseSql + " and ent_year = ? and class_num = ?";

        // attendance filter
        // attendance条件追加
        if (isAttend) {
            sql += " and is_attend = true";
        }

        // sorting
        // 並び替え
        sql += " order by no asc";

        try {

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // school code set
            // school codeを設定
            statement.setString(1, school.getCd());

            // entrance year set
            // entrance yearを設定
            statement.setInt(2, entYear);

            // class number set
            // class numberを設定
            statement.setString(3, classNum);

            // SQL execute
            // SQL実行
            ResultSet resultSet = statement.executeQuery();

            // ResultSet convert
            // ResultSet変換
            list = postFilter(resultSet, school);

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // Student list return
        // Studentリストを返す
        return list;
    }

    // year အလိုက် Student list ရှာ
    // yearでStudent一覧を検索
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        // Student list
        // Studentリスト
        List<Student> list;

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        // SQL create
        // SQL作成
        String sql = baseSql + " and ent_year = ?";

        // attendance filter
        // attendance条件追加
        if (isAttend) {
            sql += " and is_attend = true";
        }

        // sorting
        // 並び替え
        sql += " order by no asc";

        try {

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // school code set
            // school codeを設定
            statement.setString(1, school.getCd());

            // entrance year set
            // entrance yearを設定
            statement.setInt(2, entYear);

            // SQL execute
            // SQL実行
            ResultSet resultSet = statement.executeQuery();

            // ResultSet convert
            // ResultSet変換
            list = postFilter(resultSet, school);

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // Student list return
        // Studentリストを返す
        return list;
    }

    // School အလိုက် Student list ရှာ
    // SchoolごとのStudent一覧を検索
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        // Student list
        // Studentリスト
        List<Student> list;

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        // SQL create
        // SQL作成
        String sql = baseSql;

        // attendance filter
        // attendance条件追加
        if (isAttend) {
            sql += " and is_attend = true";
        }

        // sorting
        // 並び替え
        sql += " order by no asc";

        try {

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // school code set
            // school codeを設定
            statement.setString(1, school.getCd());

            // SQL execute
            // SQL実行
            ResultSet resultSet = statement.executeQuery();

            // ResultSet convert
            // ResultSet変換
            list = postFilter(resultSet, school);

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // Student list return
        // Studentリストを返す
        return list;
    }

    // Student data save သို့ update
    // Studentデータを保存または更新
    public boolean save(Student student) throws Exception {

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        // affected row count
        // 更新件数
        int count;

        try {

            // old data ရှာ
            // 既存データ検索
            Student old = get(student.getNo());

            // old data မရှိလျှင် insert
            // 既存データがなければinsert
            if (old == null) {

                // INSERT SQL
                // INSERT SQL
                statement = connection.prepareStatement(
                    "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)"
                );

                // parameter set
                // パラメータ設定
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());

            } else {

                // UPDATE SQL
                // UPDATE SQL
                statement = connection.prepareStatement(
                    "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
                );

                // parameter set
                // パラメータ設定
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
            }

            // SQL execute
            // SQL実行
            count = statement.executeUpdate();

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // success check
        // 成功確認
        return count > 0;
    }
}