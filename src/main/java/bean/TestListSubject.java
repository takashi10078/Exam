package bean;
 
import java.io.Serializable;
 
public class TestListSubject implements Serializable {
	
	/**
	 * 入学年度
	 */
    private int entYear;
    /**
     * クラス番号
     */
    private String classNum;
    /**
     * 学生番号
     */
    private String studentNo;
    /**
     * 氏名
     */
    private String studentName;
    /**
     * 点数
     */
    private Integer point1;
    /**
     * 点数（２回目）
     */
    private Integer point2;
    
    /**
     * ゲッタ・セッタ
     */
    public TestListSubject() {
    }
 
    public int getEntYear() {
        return entYear;
    }
 
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }
 
    public String getClassNum() {
        return classNum;
    }
 
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
 
    public String getStudentNo() {
        return studentNo;
    }
 
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
 
    public String getStudentName() {
        return studentName;
    }
 
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
 
    public Integer getPoint1() {
        return point1;
    }
 
    public void setPoint1(Integer point1) {
        this.point1 = point1;
    }
 
    public Integer getPoint2() {
        return point2;
    }
 
    public void setPoint2(Integer point2) {
        this.point2 = point2;
    }
}
 