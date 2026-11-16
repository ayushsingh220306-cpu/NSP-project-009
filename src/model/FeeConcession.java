package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model class representing a Student Fee Concession / Waiver Record.
 */
public class FeeConcession {
    private int concessionId;
    private String studentId;
    private String studentName;
    private String concessionType;
    private BigDecimal concessionAmount;
    private String academicYear;
    private String status; // 'Approved', 'Pending', 'Rejected'
    private String remarks;
    private Date appliedDate;

    public FeeConcession() {
    }

    public FeeConcession(int concessionId, String studentId, String studentName, String concessionType,
                         BigDecimal concessionAmount, String academicYear, String status, String remarks, Date appliedDate) {
        this.concessionId = concessionId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.concessionType = concessionType;
        this.concessionAmount = concessionAmount;
        this.academicYear = academicYear;
        this.status = status;
        this.remarks = remarks;
        this.appliedDate = appliedDate;
    }

    public FeeConcession(String studentId, String studentName, String concessionType,
                         BigDecimal concessionAmount, String academicYear, String status, String remarks, Date appliedDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.concessionType = concessionType;
        this.concessionAmount = concessionAmount;
        this.academicYear = academicYear;
        this.status = status;
        this.remarks = remarks;
        this.appliedDate = appliedDate;
    }

    public int getConcessionId() {
        return concessionId;
    }

    public void setConcessionId(int concessionId) {
        this.concessionId = concessionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getConcessionType() {
        return concessionType;
    }

    public void setConcessionType(String concessionType) {
        this.concessionType = concessionType;
    }

    public BigDecimal getConcessionAmount() {
        return concessionAmount;
    }

    public void setConcessionAmount(BigDecimal concessionAmount) {
        this.concessionAmount = concessionAmount;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    @Override
    public String toString() {
        return "FeeConcession{" +
                "concessionId=" + concessionId +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", concessionType='" + concessionType + '\'' +
                ", concessionAmount=" + concessionAmount +
                ", academicYear='" + academicYear + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
