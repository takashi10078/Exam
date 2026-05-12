package bean;

import java.io.Serializable;

public class Class implements Serializable {

	/**
	 * 学校コード
	 */
    private String schoolCd;
    
    /**
     * クラス番号
     */
    private String classNum;
    
    /**
     * ゲッタ・セッタ
     */
    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}