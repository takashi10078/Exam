package bean;
 
import java.io.Serializable;
 
public class TestListStudent implements Serializable {
 
    // 入学年度
    private Integer entYear;
 
    // クラス番号
    private String classNum;
 
    // 科目コード
    private String subjectCd;
 
    // 科目名
    private String subjectName;
 
    // 学生番号
    private String studentNo;
 
    // 学生名
    private String studentName;
 
    // 回数
    private Integer count;
 
    // 点数
    private Integer point;
 
    public TestListStudent() {
    }
 
    public Integer getEntYear() {
        return entYear;
    }
 
    public void setEntYear(Integer entYear) {
        this.entYear = entYear;
    }
 
    public String getClassNum() {
        return classNum;
    }
 
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
 
    public String getSubjectCd() {
        return subjectCd;
    }
 
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
 
    public String getSubjectName() {
        return subjectName;
    }
 
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
 
    public Integer getCount() {
        return count;
    }
 
    public void setCount(Integer count) {
        this.count = count;
    }
 
    public Integer getPoint() {
        return point;
    }
 
    public void setPoint(Integer point) {
        this.point = point;
    }
}
 