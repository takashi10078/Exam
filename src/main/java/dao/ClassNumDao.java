package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    public ClassNum get(String class_num, School school) throws Exception {

        ClassNum classNum = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "select * from class_num where class_num = ? and school_cd = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, class_num);
            statement.setString(2, school.getCd());

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                classNum = new ClassNum();
                classNum.setClassNum(rSet.getString("class_num"));
                classNum.setSchool(school); // ← 常にセット
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return classNum;
    }

    public List<String> filter(School school) throws Exception {

        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "select * from class_num where school_cd = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, school.getCd());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String classNum = resultSet.getString("class_num");

                if (classNum != null) {
                    classNum = classNum.trim();
                }

                list.add(classNum);
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public boolean save(ClassNum classNum) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "insert into class_num(class_num, school_cd) values(?, ?)"
            );

            statement.setString(1, classNum.getClassNum());
            statement.setString(2, classNum.getSchool().getCd());

            count = statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // class_num テーブル更新
            String sql = "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_cd = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newClassNum);
            statement.setString(2, classNum.getClassNum());
            statement.setString(3, classNum.getSchool().getCd());

            count += statement.executeUpdate();

            statement.close();

            // student テーブル更新
            sql = "UPDATE student SET class_num = ? WHERE class_num = ? AND school_cd = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newClassNum);
            statement.setString(2, classNum.getClassNum());
            statement.setString(3, classNum.getSchool().getCd());

            count += statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }
}