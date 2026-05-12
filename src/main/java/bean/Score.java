package bean;

import java.io.Serializable;

/**
 * 得点Bean
 * TESTテーブル: STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM
 */
public class Score implements Serializable {
	/**
	 * 学生番号
	 */
    private String studentNo;
    
    /**
     * 科目コード
     */
    private String subjectCd;
    
    /**
     * 学校コード
     */
    private String schoolCd;
    
    /**
     * 回数
     */
    private int no;
    
    /**
     * 点数
     */
    private int point;
    
    /**
     * クラス番号
     */
    private String classNum;

    // --- 関連オブジェクト (JOIN取得時に使用) ---
    /**
     * 学生情報
     */
    private Student student;
    /**
     * 科目情報
     */
    private Subject subject;

    /** ゲッタ・セッタ */
    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}
