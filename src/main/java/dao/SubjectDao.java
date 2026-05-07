package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
    // 🔹 Get one subject
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where cd = ? and school_cd = ?"
            );

            statement.setString(1, cd);
            statement.setString(2, school.getCd());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));

                School sc = new School();
                sc.setCd(rs.getString("school_cd"));
                subject.setSchool(sc);
            }

            rs.close();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return subject;
    }

    // 🔹 Get all subjects
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(
            	"select * from subject where school_cd = ?"
            );
            statement.setString(1, school.getCd());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));

                School sc = new School();
                sc.setCd(rs.getString("school_cd"));
                subject.setSchool(sc);

                list.add(subject);
            }

            rs.close();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }
    
    public void create(String cd,String name,String school_cd)throws Exception{
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	
    	try {
    		statement = connection.prepareStatement("INSERT INTO SUBJECT VALUES (?,?,?)");
    		statement.setString(2, cd);
    		statement.setString(3, name);
    		statement.setString(1,school_cd);
    		
       		statement.executeUpdate();
    	} finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    
    public void update(String cd,String name,String school_cd)throws Exception{
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	
    	try {
    		statement = connection.prepareStatement("UPDATE SUBJECT SET name=? where cd=? and school_cd =  ?");
    		statement.setString(1,name);
    		statement.setString(2,cd);
    		statement.setString(3, school_cd);
    		
    		statement.executeUpdate();
    		
    	
    	}finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    
    public void delete(String cd,String school_cd)throws Exception{
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	
    	try {
    		statement = connection.prepareStatement("DELETE FROM SUBJECT WHERE cd=? and school_cd=?");
    		statement.setString(1,cd);
    	
    		statement.setString(2, school_cd);
    		
    		statement.executeUpdate();
    		
    	
    	}finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}