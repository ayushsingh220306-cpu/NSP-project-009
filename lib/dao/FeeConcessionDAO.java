package dao;

import db.DBConnection;
import model.FeeConcession;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Fee Concession operations (CRUD + Search).
 */
public class FeeConcessionDAO {

    /**
     * Inserts a new fee concession record into MySQL database.
     */
    public boolean insert(FeeConcession c) {
        String sql = "INSERT INTO fee_concessions (student_id, student_name, concession_type, concession_amount, academic_year, status, remarks, applied_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getStudentId());
            stmt.setString(2, c.getStudentName());
            stmt.setString(3, c.getConcessionType());
            stmt.setBigDecimal(4, c.getConcessionAmount());
            stmt.setString(5, c.getAcademicYear());
            stmt.setString(6, c.getStatus());
            stmt.setString(7, c.getRemarks());
            stmt.setDate(8, c.getAppliedDate());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        c.setConcessionId(keys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.insert error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates an existing fee concession record.
     */
    public boolean update(FeeConcession c) {
        String sql = "UPDATE fee_concessions SET student_id = ?, student_name = ?, concession_type = ?, concession_amount = ?, " +
                     "academic_year = ?, status = ?, remarks = ?, applied_date = ? WHERE concession_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getStudentId());
            stmt.setString(2, c.getStudentName());
            stmt.setString(3, c.getConcessionType());
            stmt.setBigDecimal(4, c.getConcessionAmount());
            stmt.setString(5, c.getAcademicYear());
            stmt.setString(6, c.getStatus());
            stmt.setString(7, c.getRemarks());
            stmt.setDate(8, c.getAppliedDate());
            stmt.setInt(9, c.getConcessionId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.update error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a fee concession record by primary key ID.
     */
    public boolean delete(int concessionId) {
        String sql = "DELETE FROM fee_concessions WHERE concession_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, concessionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.delete error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fetches all fee concession records ordered by ID descending.
     */
    public List<FeeConcession> getAll() {
        List<FeeConcession> list = new ArrayList<>();
        String sql = "SELECT concession_id, student_id, student_name, concession_type, concession_amount, academic_year, status, remarks, applied_date " +
                     "FROM fee_concessions ORDER BY concession_id ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToFeeConcession(rs));
            }
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.getAll error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Finds a single fee concession record by primary key ID.
     */
    public FeeConcession getById(int concessionId) {
        String sql = "SELECT concession_id, student_id, student_name, concession_type, concession_amount, academic_year, status, remarks, applied_date " +
                     "FROM fee_concessions WHERE concession_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, concessionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToFeeConcession(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.getById error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Searches fee concession records by keyword across student_id, student_name,
     * concession_type, academic_year, and status.
     */
    public List<FeeConcession> search(String keyword) {
        List<FeeConcession> list = new ArrayList<>();
        String sql = "SELECT concession_id, student_id, student_name, concession_type, concession_amount, academic_year, status, remarks, applied_date " +
                     "FROM fee_concessions WHERE student_id LIKE ? OR student_name LIKE ? OR concession_type LIKE ? " +
                     "OR academic_year LIKE ? OR status LIKE ? ORDER BY concession_id ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String pattern = "%" + (keyword == null ? "" : keyword.trim()) + "%";
            for (int i = 1; i <= 5; i++) {
                stmt.setString(i, pattern);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToFeeConcession(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.search error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Returns total count of fee concession records.
     */
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM fee_concessions";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.getTotalCount error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Returns sum of approved fee concession amounts.
     */
    public BigDecimal getTotalApprovedAmount() {
        String sql = "SELECT COALESCE(SUM(concession_amount), 0) FROM fee_concessions WHERE status = 'Approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            System.err.println("FeeConcessionDAO.getTotalApprovedAmount error: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    private FeeConcession mapResultSetToFeeConcession(ResultSet rs) throws SQLException {
        return new FeeConcession(
                rs.getInt("concession_id"),
                rs.getString("student_id"),
                rs.getString("student_name"),
                rs.getString("concession_type"),
                rs.getBigDecimal("concession_amount"),
                rs.getString("academic_year"),
                rs.getString("status"),
                rs.getString("remarks"),
                rs.getDate("applied_date")
        );
    }
}
