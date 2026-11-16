package dao;

import db.DBConnection;
import model.Scholarship;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Scholarship operations (CRUD + Search).
 */
public class ScholarshipDAO {

    /**
     * Inserts a new scholarship record into MySQL database.
     */
    public boolean insert(Scholarship s) {
        String sql = "INSERT INTO scholarships (student_id, student_name, scholarship_name, amount, academic_year, status, remarks, applied_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, s.getStudentId());
            stmt.setString(2, s.getStudentName());
            stmt.setString(3, s.getScholarshipName());
            stmt.setBigDecimal(4, s.getAmount());
            stmt.setString(5, s.getAcademicYear());
            stmt.setString(6, s.getStatus());
            stmt.setString(7, s.getRemarks());
            stmt.setDate(8, s.getAppliedDate());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        s.setScholarshipId(keys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.insert error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates an existing scholarship record.
     */
    public boolean update(Scholarship s) {
        String sql = "UPDATE scholarships SET student_id = ?, student_name = ?, scholarship_name = ?, amount = ?, " +
                     "academic_year = ?, status = ?, remarks = ?, applied_date = ? WHERE scholarship_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getStudentId());
            stmt.setString(2, s.getStudentName());
            stmt.setString(3, s.getScholarshipName());
            stmt.setBigDecimal(4, s.getAmount());
            stmt.setString(5, s.getAcademicYear());
            stmt.setString(6, s.getStatus());
            stmt.setString(7, s.getRemarks());
            stmt.setDate(8, s.getAppliedDate());
            stmt.setInt(9, s.getScholarshipId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.update error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a scholarship record by primary key ID.
     */
    public boolean delete(int scholarshipId) {
        String sql = "DELETE FROM scholarships WHERE scholarship_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scholarshipId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.delete error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fetches all scholarship records ordered by ID descending.
     */
    public List<Scholarship> getAll() {
        List<Scholarship> list = new ArrayList<>();
        String sql = "SELECT scholarship_id, student_id, student_name, scholarship_name, amount, academic_year, status, remarks, applied_date " +
                     "FROM scholarships ORDER BY scholarship_id ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToScholarship(rs));
            }
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.getAll error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Finds a single scholarship record by primary key ID.
     */
    public Scholarship getById(int scholarshipId) {
        String sql = "SELECT scholarship_id, student_id, student_name, scholarship_name, amount, academic_year, status, remarks, applied_date " +
                     "FROM scholarships WHERE scholarship_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scholarshipId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToScholarship(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.getById error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Searches scholarship records by keyword across student_id, student_name,
     * scholarship_name, academic_year, and status.
     */
    public List<Scholarship> search(String keyword) {
        List<Scholarship> list = new ArrayList<>();
        String sql = "SELECT scholarship_id, student_id, student_name, scholarship_name, amount, academic_year, status, remarks, applied_date " +
                     "FROM scholarships WHERE student_id LIKE ? OR student_name LIKE ? OR scholarship_name LIKE ? " +
                     "OR academic_year LIKE ? OR status LIKE ? ORDER BY scholarship_id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String pattern = "%" + (keyword == null ? "" : keyword.trim()) + "%";
            for (int i = 1; i <= 5; i++) {
                stmt.setString(i, pattern);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToScholarship(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.search error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Returns total count of scholarship records.
     */
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM scholarships";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.getTotalCount error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Returns sum of approved scholarship amounts.
     */
    public BigDecimal getTotalApprovedAmount() {
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM scholarships WHERE status = 'Approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            System.err.println("ScholarshipDAO.getTotalApprovedAmount error: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    private Scholarship mapResultSetToScholarship(ResultSet rs) throws SQLException {
        return new Scholarship(
                rs.getInt("scholarship_id"),
                rs.getString("student_id"),
                rs.getString("student_name"),
                rs.getString("scholarship_name"),
                rs.getBigDecimal("amount"),
                rs.getString("academic_year"),
                rs.getString("status"),
                rs.getString("remarks"),
                rs.getDate("applied_date")
        );
    }
}
