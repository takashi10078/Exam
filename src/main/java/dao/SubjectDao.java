package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    // Subject တစ်ခုကို database မှ ရှာယူသော method
    // Subjectを1件データベースから取得するメソッド
    public Subject get(String cd, School school) throws Exception {

        // Subject object ကို null နဲ့စတင်ကြေညာ
        // Subjectオブジェクトをnullで初期化
        Subject subject = null;

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // SQL command အသုံးပြုရန် PreparedStatement
        // SQL実行用のPreparedStatement
        PreparedStatement statement = null;

        try {

            // subject table မှ cd နှင့် school_cd တူသော data ကိုရှာ
            // subjectテーブルからcdとschool_cdが一致するデータを検索
            statement = connection.prepareStatement(
                "select * from subject where cd = ? and school_cd = ?"
            );

            // ပထမ parameter အဖြစ် cd ထည့်
            // 1番目のパラメータにcdを設定
            statement.setString(1, cd);

            // ဒုတိယ parameter အဖြစ် school code ထည့်
            // 2番目のパラメータにschool codeを設定
            statement.setString(2, school.getCd());

            // SQL ကို execute လုပ်ပြီး ResultSet ရယူ
            // SQLを実行してResultSetを取得
            ResultSet rs = statement.executeQuery();

            // Data ရှိလျှင်
            // データが存在する場合
            if (rs.next()) {

                // Subject object အသစ်ဖန်တီး
                // Subjectオブジェクトを作成
                subject = new Subject();

                // subject code ကို set
                // subject codeをセット
                subject.setCd(rs.getString("cd"));

                // subject name ကို set
                // subject nameをセット
                subject.setName(rs.getString("name"));

                // School object အသစ်ဖန်တီး
                // Schoolオブジェクトを作成
                School sc = new School();

                // school code ကို set
                // school codeをセット
                sc.setCd(rs.getString("school_cd"));

                // Subject object ထဲသို့ School object ထည့်
                // SubjectオブジェクトにSchoolオブジェクトをセット
                subject.setSchool(sc);
            }

            // ResultSet ကိုပိတ်
            // ResultSetを閉じる
            rs.close();

        } finally {

            // statement ကို close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection ကို close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // Subject object ကို return ပြန်
        // Subjectオブジェクトを返す
        return subject;
    }

    // School အလိုက် Subject list အားလုံးယူသော method
    // SchoolごとのSubject一覧を取得するメソッド
    public List<Subject> filter(School school) throws Exception {

        // Subject list ဖန်တီး
        // Subjectリストを作成
        List<Subject> list = new ArrayList<>();

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // PreparedStatement ကြေညာ
        // PreparedStatementを宣言
        PreparedStatement statement = null;

        try {

            // school_cd တူသော subject data များယူ
            // school_cdが一致するsubjectデータを取得
            statement = connection.prepareStatement(
                "select * from subject where school_cd = ?"
            );

            // school code ကို parameter အဖြစ်ထည့်
            // school codeをパラメータに設定
            statement.setString(1, school.getCd());

            // SQL execute
            // SQL実行
            ResultSet rs = statement.executeQuery();

            // Data အားလုံး loop ပတ်
            // 全データをループ処理
            while (rs.next()) {

                // Subject object ဖန်တီး
                // Subjectオブジェクトを作成
                Subject subject = new Subject();

                // subject code set
                // subject codeをセット
                subject.setCd(rs.getString("cd"));

                // subject name set
                // subject nameをセット
                subject.setName(rs.getString("name"));

                // School object ဖန်တီး
                // Schoolオブジェクトを作成
                School sc = new School();

                // school code set
                // school codeをセット
                sc.setCd(rs.getString("school_cd"));

                // School object ကို Subject ထဲထည့်
                // SchoolオブジェクトをSubjectにセット
                subject.setSchool(sc);

                // list ထဲသို့ subject ထည့်
                // listにsubjectを追加
                list.add(subject);
            }

            // ResultSet close
            // ResultSetを閉じる
            rs.close();

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // Subject list ကို return ပြန်
        // Subjectリストを返す
        return list;
    }

    // Subject အသစ် database ထဲသို့ထည့်သော method
    // 新しいSubjectをデータベースへ追加するメソッド
    public void create(String cd,String name,String school_cd)throws Exception{

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // PreparedStatement ကြေညာ
        // PreparedStatementを宣言
        PreparedStatement statement = null;

        try {

            // INSERT SQL ပြင်ဆင်
            // INSERT SQLを準備
            statement = connection.prepareStatement(
                "INSERT INTO SUBJECT VALUES (?,?,?)"
            );

            // subject code ထည့်
            // subject codeを設定
            statement.setString(2, cd);

            // subject name ထည့်
            // subject nameを設定
            statement.setString(3, name);

            // school code ထည့်
            // school codeを設定
            statement.setString(1,school_cd);

            // INSERT execute
            // INSERTを実行
            statement.executeUpdate();

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }
    }

    // Subject name ကို update လုပ်သော method
    // Subject名を更新するメソッド
    public void update(String cd,String name,String school_cd)throws Exception{

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // PreparedStatement ကြေညာ
        // PreparedStatementを宣言
        PreparedStatement statement = null;

        try {

            // UPDATE SQL ပြင်ဆင်
            // UPDATE SQLを準備
            statement = connection.prepareStatement(
                "UPDATE SUBJECT SET name=? where cd=? and school_cd = ?"
            );

            // subject name update value
            // 更新するsubject nameを設定
            statement.setString(1,name);

            // subject code set
            // subject codeを設定
            statement.setString(2,cd);

            // school code set
            // school codeを設定
            statement.setString(3, school_cd);

            // UPDATE execute
            // UPDATEを実行
            statement.executeUpdate();

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }
    }

    // Subject ကို delete လုပ်သော method
    // Subjectを削除するメソッド
    public void delete(String cd,String school_cd)throws Exception{

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // PreparedStatement ကြေညာ
        // PreparedStatementを宣言
        PreparedStatement statement = null;

        try {

            // DELETE SQL ပြင်ဆင်
            // DELETE SQLを準備
            statement = connection.prepareStatement(
                "DELETE FROM SUBJECT WHERE cd=? and school_cd=?"
            );

            // subject code set
            // subject codeを設定
            statement.setString(1,cd);

            // school code set
            // school codeを設定
            statement.setString(2, school_cd);

            // DELETE execute
            // DELETEを実行
            statement.executeUpdate();

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }
    }
}