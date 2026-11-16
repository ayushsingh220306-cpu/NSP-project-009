package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model class representing a Student Scholarship Record.
 */
public class Scholarship {
    private int scholarshipId;
    private String studentId;
    private String studentName;
    private String scholarshipName;
    private BigDecimal amount;
    private String academicYear;
    private String status; // 'Approved', 'Pending', 'Rejected'
    private String remarks;
    private Date appliedDate;

    public Scholarship() {
    }

    public Scholarship(int scholarshipId, String studentId, String studentName, String scholarshipName,
                       BigDecimal amount, String academicYear, String status, String remarks, Date appliedDate) {
        this.scholarshipId = scholarshipId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.scholarshipName = scholarshipName;
        this.amount = amount;
        this.academicYear = academicYear;
        this.status = status;
        this.remarks = remarks;
        this.appliedDate = appliedDate;
    }

    public Scholarship(String studentId, String studentName, String scholarshipName,
                       BigDecimal amount, String academicYear, String status, String remarks, Date appliedDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.scholarshipName = scholarshipName;
        this.amount = amount;
        this.academicYear = academicYear;
        this.status = status;
        this.remarks = remarks;
        this.appliedDate = appliedDate;
    }

    public int getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(int scholarshipId) {
        this.scholarshipId = scholarshipId;
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

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        return "Scholarship{" +
                "scholarshipId=" + scholarshipId +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", scholarshipName='" + scholarshipName + '\'' +
                ", amount=" + amount +
                ", academicYear='" + academicYear + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
