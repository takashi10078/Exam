package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    // ClassNum တစ်ခုကိုရှာယူသော method
    // ClassNumを1件取得するメソッド
    public ClassNum get(String class_num, School school) throws Exception {

        // ClassNum object ကြေညာ
        // ClassNumオブジェクトを宣言
        ClassNum classNum = null;

        // Database connection ရယူ
        // データベース接続を取得
        Connection connection = getConnection();

        // SQL execute အတွက် PreparedStatement
        // SQL実行用のPreparedStatement
        PreparedStatement statement = null;

        try {

            // class_num table မှ data ရှာရန် SQL
            // class_numテーブルからデータ検索用SQL
            String sql = "select * from class_num where class_num = ? and school_cd = ?";

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // class number parameter set
            // class numberパラメータ設定
            statement.setString(1, class_num);

            // school code parameter set
            // school codeパラメータ設定
            statement.setString(2, school.getCd());

            // SQL execute
            // SQL実行
            ResultSet rSet = statement.executeQuery();

            // Data ရှိလျှင်
            // データが存在する場合
            if (rSet.next()) {

                // ClassNum object ဖန်တီး
                // ClassNumオブジェクトを作成
                classNum = new ClassNum();

                // class number set
                // class numberを設定
                classNum.setClassNum(rSet.getString("class_num"));

                // school object set
                // schoolオブジェクトを設定
                classNum.setSchool(school);
            }

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // ClassNum object return
        // ClassNumオブジェクトを返す
        return classNum;
    }

    // School အလိုက် ClassNum list ရယူ
    // SchoolごとのClassNum一覧を取得
    public List<String> filter(School school) throws Exception {

        // ClassNum list ဖန်တီး
        // ClassNumリストを作成
        List<String> list = new ArrayList<>();

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        try {

            // class_num table မှ school_cd တူသော data ရှာ
            // class_numテーブルからschool_cdが一致するデータを検索
            String sql = "select * from class_num where school_cd = ?";

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // school code set
            // school codeを設定
            statement.setString(1, school.getCd());

            // SQL execute
            // SQL実行
            ResultSet resultSet = statement.executeQuery();

            // ResultSet loop
            // ResultSetをループ
            while (resultSet.next()) {

                // class number ရယူ
                // class number取得
                String classNum = resultSet.getString("class_num");

                // null မဟုတ်လျှင် trim လုပ်
                // nullでなければtrim実行
                if (classNum != null) {
                    classNum = classNum.trim();
                }

                // list ထဲထည့်
                // listへ追加
                list.add(classNum);
            }

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // ClassNum list return
        // ClassNumリストを返す
        return list;
    }

    // ClassNum data အသစ် save
    // 新しいClassNumデータを保存
    public boolean save(ClassNum classNum) throws Exception {

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        // affected row count
        // 更新件数
        int count = 0;

        try {

            // INSERT SQL
            // INSERT SQL
            statement = connection.prepareStatement(
                "insert into class_num(class_num, school_cd) values(?, ?)"
            );

            // class number set
            // class numberを設定
            statement.setString(1, classNum.getClassNum());

            // school code set
            // school codeを設定
            statement.setString(2, classNum.getSchool().getCd());

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

        // save success check
        // 保存成功確認
        return count > 0;
    }

    // ClassNum data update
    // ClassNumデータ更新
    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

        // Database connection
        // データベース接続
        Connection connection = getConnection();

        // PreparedStatement
        // PreparedStatement
        PreparedStatement statement = null;

        // affected row count
        // 更新件数
        int count = 0;

        try {

            // class_num table update SQL
            // class_numテーブル更新SQL
            String sql = "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_cd = ?";

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // new class number set
            // 新しいclass numberを設定
            statement.setString(1, newClassNum);

            // old class number set
            // 古いclass numberを設定
            statement.setString(2, classNum.getClassNum());

            // school code set
            // school codeを設定
            statement.setString(3, classNum.getSchool().getCd());

            // SQL execute
            // SQL実行
            count += statement.executeUpdate();

            // statement close
            // statementを閉じる
            statement.close();

            // student table update SQL
            // studentテーブル更新SQL
            sql = "UPDATE student SET class_num = ? WHERE class_num = ? AND school_cd = ?";

            // SQL prepare
            // SQL準備
            statement = connection.prepareStatement(sql);

            // new class number set
            // 新しいclass numberを設定
            statement.setString(1, newClassNum);

            // old class number set
            // 古いclass numberを設定
            statement.setString(2, classNum.getClassNum());

            // school code set
            // school codeを設定
            statement.setString(3, classNum.getSchool().getCd());

            // SQL execute
            // SQL実行
            count += statement.executeUpdate();

        } finally {

            // statement close
            // statementを閉じる
            if (statement != null) statement.close();

            // connection close
            // connectionを閉じる
            if (connection != null) connection.close();
        }

        // update success check
        // 更新成功確認
        return count > 0;
    }
}