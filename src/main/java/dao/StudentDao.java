package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private String baseSql = "select * from student where school_cd = ?";

    public Student get(String no) throws Exception {

        Student student = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from student where no=?"
            );

            statement.setString(1, no);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = new Student();

                student.setNo(resultSet.getString("no"));
                student.setName(resultSet.getString("name"));
                student.setEntYear(resultSet.getInt("ent_year"));
                student.setClassNum(resultSet.getString("class_num"));
                student.setAttend(resultSet.getBoolean("is_attend"));

                School school = new School();
                school.setCd(resultSet.getString("school_cd"));
                student.setSchool(school);
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return student;
    }

    private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        while (resultSet.next()) {
            Student student = new Student();

            student.setNo(resultSet.getString("no"));
            student.setName(resultSet.getString("name"));
            student.setEntYear(resultSet.getInt("ent_year"));
            student.setClassNum(resultSet.getString("class_num"));
            student.setAttend(resultSet.getBoolean("is_attend"));
            student.setSchool(school);

            list.add(student);
        }

        return list;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        List<Student> list;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String sql = baseSql + " and ent_year = ? and class_num = ?";
        if (isAttend) {
            sql += " and is_attend = true";
        }
        sql += " order by no asc";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);

            ResultSet resultSet = statement.executeQuery();

            list = postFilter(resultSet, school);

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        List<Student> list;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String sql = baseSql + " and ent_year = ?";
        if (isAttend) {
            sql += " and is_attend = true";
        }
        sql += " order by no asc";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);

            ResultSet resultSet = statement.executeQuery();

            list = postFilter(resultSet, school);

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {

        List<Student> list;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String sql = baseSql;
        if (isAttend) {
            sql += " and is_attend = true";
        }
        sql += " order by no asc";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, school.getCd());

            ResultSet resultSet = statement.executeQuery();

            list = postFilter(resultSet, school);

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public boolean save(Student student) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count;

        try {
            Student old = get(student.getNo());

            if (old == null) {
                statement = connection.prepareStatement(
                    "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)"
                );

                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());

            } else {
                statement = connection.prepareStatement(
                    "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
                );

                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
            }

            count = statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }
}