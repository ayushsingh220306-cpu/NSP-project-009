package ui;

import dao.FeeConcessionDAO;
import model.FeeConcession;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Panel for managing student fee concession and waiver records.
 * Supports full CRUD, Search, and Read-Only viewing modes.
 */
public class ManageFeeConcessionPanel extends JPanel {

    private final boolean readOnly;
    private final FeeConcessionDAO feeConcessionDAO;

    // Form inputs
    private JTextField txtConcessionId;
    private JTextField txtStudentId;
    private JTextField txtStudentName;
    private JComboBox<String> cmbConcessionType;
    private JTextField txtAmount;
    private JTextField txtAcademicYear;
    private JComboBox<String> cmbStatus;
    private JTextField txtRemarks;
    private JTextField txtAppliedDate;

    // Search controls
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnResetSearch;

    // Form Action buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel tableModel;

    // Common Concession types
    private static final String[] CONCESSION_TYPES = {
            "Sibling Concession",
            "EWS Category Waiver",
            "Staff Ward Concession",
            "Merit Fee Waiver",
            "Special Discretionary Waiver",
            "Sports Quota Concession"
    };

    private static final String[] STATUS_OPTIONS = {
            "Approved",
            "Pending",
            "Rejected"
    };

    public ManageFeeConcessionPanel(boolean readOnly) {
        this.readOnly = readOnly;
        this.feeConcessionDAO = new FeeConcessionDAO();

        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyle.COLOR_BACKGROUND);
        setBorder(new EmptyBorder(12, 12, 12, 12));

        initComponents();
        loadTableData(feeConcessionDAO.getAll());
    }

    private void initComponents() {
        // TOP: Search Bar & Section Header
        JPanel topPanel = new JPanel(new BorderLayout(10, 8));
        topPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        JPanel headerTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        headerTitlePanel.setBackground(UIStyle.COLOR_BACKGROUND);
        JLabel lblHeader = new JLabel(readOnly ? "Fee Concession Records (Student View)" : "Fee Concession Management");
        lblHeader.setFont(UIStyle.FONT_TITLE);
        lblHeader.setForeground(UIStyle.COLOR_PRIMARY);
        headerTitlePanel.add(lblHeader);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        searchPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setFont(UIStyle.FONT_LABEL);
        txtSearch = new JTextField(16);
        UIStyle.styleTextField(txtSearch);
        txtSearch.setToolTipText("Search by Student ID, Name, Concession Type, Year, or Status");

        btnSearch = UIStyle.createSearchButton("Search");
        btnResetSearch = UIStyle.createNeutralButton("Show All");

        btnSearch.addActionListener(e -> handleSearch());
        btnResetSearch.addActionListener(e -> {
            txtSearch.setText("");
            loadTableData(feeConcessionDAO.getAll());
        });
        txtSearch.addActionListener(e -> handleSearch());

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnResetSearch);

        topPanel.add(headerTitlePanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // CENTER: Split into Form Card (West) and Table Card (Center)
        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        // 1. Form Card
        JPanel formCard = UIStyle.createCardPanel();
        formCard.setLayout(new BorderLayout(8, 8));
        formCard.setPreferredSize(new Dimension(380, 0));

        JLabel lblFormTitle = new JLabel(readOnly ? "Record Details" : "Concession Entry Form");
        lblFormTitle.setFont(UIStyle.FONT_HEADER);
        lblFormTitle.setForeground(UIStyle.COLOR_PRIMARY);
        formCard.add(lblFormTitle, BorderLayout.NORTH);

        JPanel formFields = new JPanel(new GridBagLayout());
        formFields.setBackground(UIStyle.COLOR_SURFACE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtConcessionId = new JTextField();
        txtConcessionId.setEditable(false);
        txtConcessionId.setBackground(new Color(245, 245, 245));
        UIStyle.styleTextField(txtConcessionId);

        txtStudentId = new JTextField();
        UIStyle.styleTextField(txtStudentId);

        txtStudentName = new JTextField();
        UIStyle.styleTextField(txtStudentName);

        cmbConcessionType = new JComboBox<>(CONCESSION_TYPES);
        cmbConcessionType.setEditable(true);
        UIStyle.styleComboBox(cmbConcessionType);

        txtAmount = new JTextField();
        UIStyle.styleTextField(txtAmount);

        txtAcademicYear = new JTextField("2026-27");
        UIStyle.styleTextField(txtAcademicYear);

        cmbStatus = new JComboBox<>(STATUS_OPTIONS);
        UIStyle.styleComboBox(cmbStatus);

        txtRemarks = new JTextField();
        UIStyle.styleTextField(txtRemarks);

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        txtAppliedDate = new JTextField(today);
        txtAppliedDate.setToolTipText("Date format: YYYY-MM-DD");
        UIStyle.styleTextField(txtAppliedDate);

        // Add fields to form
        addFormField(formFields, gbc, 0, "Concession ID:", txtConcessionId);
        addFormField(formFields, gbc, 1, "Student ID *:", txtStudentId);
        addFormField(formFields, gbc, 2, "Student Name *:", txtStudentName);
        addFormField(formFields, gbc, 3, "Concession Type *:", cmbConcessionType);
        addFormField(formFields, gbc, 4, "Amount (Rs) *:", txtAmount);
        addFormField(formFields, gbc, 5, "Academic Year *:", txtAcademicYear);
        addFormField(formFields, gbc, 6, "Status *:", cmbStatus);
        addFormField(formFields, gbc, 7, "Applied Date:", txtAppliedDate);
        addFormField(formFields, gbc, 8, "Remarks:", txtRemarks);

        formCard.add(formFields, BorderLayout.CENTER);

        // Buttons under Form
        JPanel formBtnPanel = new JPanel(new GridLayout(readOnly ? 1 : 2, 2, 8, 8));
        formBtnPanel.setBackground(UIStyle.COLOR_SURFACE);

        if (!readOnly) {
            btnAdd = UIStyle.createSuccessButton("Add Record");
            btnUpdate = UIStyle.createWarningButton("Update Record");
            btnDelete = UIStyle.createDangerButton("Delete Record");
            btnClear = UIStyle.createNeutralButton("Clear Form");

            btnAdd.addActionListener(e -> handleAdd());
            btnUpdate.addActionListener(e -> handleUpdate());
            btnDelete.addActionListener(e -> handleDelete());
            btnClear.addActionListener(e -> clearForm());

            formBtnPanel.add(btnAdd);
            formBtnPanel.add(btnUpdate);
            formBtnPanel.add(btnDelete);
            formBtnPanel.add(btnClear);
        } else {
            btnClear = UIStyle.createNeutralButton("Clear Selection");
            btnClear.addActionListener(e -> clearForm());
            formBtnPanel.add(btnClear);

            // Disable editing fields in read-only mode
            txtStudentId.setEditable(false);
            txtStudentName.setEditable(false);
            cmbConcessionType.setEnabled(false);
            txtAmount.setEditable(false);
            txtAcademicYear.setEditable(false);
            cmbStatus.setEnabled(false);
            txtRemarks.setEditable(false);
            txtAppliedDate.setEditable(false);
        }

        formCard.add(formBtnPanel, BorderLayout.SOUTH);
        contentPanel.add(formCard, BorderLayout.WEST);

        // 2. Table Card
        JPanel tableCard = UIStyle.createCardPanel();
        tableCard.setLayout(new BorderLayout(8, 8));

        JLabel lblTableTitle = new JLabel("Fee Concession Records Database");
        lblTableTitle.setFont(UIStyle.FONT_HEADER);
        lblTableTitle.setForeground(UIStyle.COLOR_PRIMARY);
        tableCard.add(lblTableTitle, BorderLayout.NORTH);

        String[] columnNames = {
                "ID", "Student ID", "Student Name", "Concession Type",
                "Amount (Rs)", "Academic Year", "Status", "Applied Date", "Remarks"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        UIStyle.styleTable(table);

        // Adjust column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);
        table.getColumnModel().getColumn(6).setPreferredWidth(85);
        table.getColumnModel().getColumn(7).setPreferredWidth(95);
        table.getColumnModel().getColumn(8).setPreferredWidth(160);

        // Table Row Selection Listener
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                populateFormFromSelectedRow();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tableCard, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.35;
        JLabel label = new JLabel(labelText);
        label.setFont(UIStyle.FONT_LABEL);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 0.65;
        panel.add(field, gbc);
    }

    private void populateFormFromSelectedRow() {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtConcessionId.setText(table.getValueAt(row, 0).toString());
            txtStudentId.setText(table.getValueAt(row, 1).toString());
            txtStudentName.setText(table.getValueAt(row, 2).toString());
            cmbConcessionType.setSelectedItem(table.getValueAt(row, 3).toString());
            txtAmount.setText(table.getValueAt(row, 4).toString());
            txtAcademicYear.setText(table.getValueAt(row, 5).toString());
            cmbStatus.setSelectedItem(table.getValueAt(row, 6).toString());
            txtAppliedDate.setText(table.getValueAt(row, 7).toString());
            Object remarksVal = table.getValueAt(row, 8);
            txtRemarks.setText(remarksVal == null ? "" : remarksVal.toString());
        }
    }

    public void loadTableData(List<FeeConcession> list) {
        tableModel.setRowCount(0);
        for (FeeConcession c : list) {
            tableModel.addRow(new Object[]{
                    c.getConcessionId(),
                    c.getStudentId(),
                    c.getStudentName(),
                    c.getConcessionType(),
                    String.format("%.2f", c.getConcessionAmount()),
                    c.getAcademicYear(),
                    c.getStatus(),
                    c.getAppliedDate().toString(),
                    c.getRemarks() == null ? "" : c.getRemarks()
            });
        }
    }

    private void handleSearch() {
        String query = txtSearch.getText().trim();
        if (query.isEmpty()) {
            loadTableData(feeConcessionDAO.getAll());
        } else {
            List<FeeConcession> results = feeConcessionDAO.search(query);
            loadTableData(results);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No fee concession records found matching: \"" + query + "\"",
                        "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void handleAdd() {
        FeeConcession c = validateAndBuildConcession(false);
        if (c == null) return;

        boolean success = feeConcessionDAO.insert(c);
        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Fee concession record successfully added!",
                    "Record Created", JOptionPane.INFORMATION_MESSAGE);
            loadTableData(feeConcessionDAO.getAll());
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to add fee concession record. Check database connection.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String idStr = txtConcessionId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a record from the table to update.",
                    "No Record Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FeeConcession c = validateAndBuildConcession(true);
        if (c == null) return;

        boolean success = feeConcessionDAO.update(c);
        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Fee concession record successfully updated!",
                    "Record Updated", JOptionPane.INFORMATION_MESSAGE);
            loadTableData(feeConcessionDAO.getAll());
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update fee concession record.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDelete() {
        String idStr = txtConcessionId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a record from the table to delete.",
                    "No Record Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int concessionId = Integer.parseInt(idStr);
        String studentName = txtStudentName.getText().trim();

        // Strict PIET Requirement: Deletion must ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete fee concession record ID " + concessionId +
                        " for " + studentName + "?\nThis action cannot be undone.",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = feeConcessionDAO.delete(concessionId);
            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Record deleted successfully.",
                        "Deleted", JOptionPane.INFORMATION_MESSAGE);
                loadTableData(feeConcessionDAO.getAll());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to delete record.",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private FeeConcession validateAndBuildConcession(boolean isUpdate) {
        String studentId = txtStudentId.getText().trim();
        String studentName = txtStudentName.getText().trim();
        Object conTypeObj = cmbConcessionType.getSelectedItem();
        String concessionType = conTypeObj == null ? "" : conTypeObj.toString().trim();
        String amountStr = txtAmount.getText().trim();
        String academicYear = txtAcademicYear.getText().trim();
        String status = (String) cmbStatus.getSelectedItem();
        String remarks = txtRemarks.getText().trim();
        String appliedDateStr = txtAppliedDate.getText().trim();

        // Validations
        if (studentId.isEmpty()) {
            showValidationError("Student ID is required (e.g. PIET25CR009).");
            txtStudentId.requestFocus();
            return null;
        }

        if (studentName.isEmpty()) {
            showValidationError("Student Name is required.");
            txtStudentName.requestFocus();
            return null;
        }

        if (concessionType.isEmpty()) {
            showValidationError("Please select or enter a Concession Type.");
            cmbConcessionType.requestFocus();
            return null;
        }

        if (amountStr.isEmpty()) {
            showValidationError("Amount is required.");
            txtAmount.requestFocus();
            return null;
        }

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                showValidationError("Amount must be greater than 0.");
                txtAmount.requestFocus();
                return null;
            }
        } catch (NumberFormatException e) {
            showValidationError("Invalid amount format. Please enter a valid number (e.g., 10000.00).");
            txtAmount.requestFocus();
            return null;
        }

        if (academicYear.isEmpty()) {
            showValidationError("Academic Year is required (e.g., 2026-27).");
            txtAcademicYear.requestFocus();
            return null;
        }

        Date appliedDate;
        try {
            appliedDate = Date.valueOf(appliedDateStr);
        } catch (IllegalArgumentException e) {
            showValidationError("Applied Date must be in valid format (YYYY-MM-DD).");
            txtAppliedDate.requestFocus();
            return null;
        }

        FeeConcession c = new FeeConcession(studentId, studentName, concessionType, amount, academicYear, status, remarks, appliedDate);
        if (isUpdate) {
            c.setConcessionId(Integer.parseInt(txtConcessionId.getText().trim()));
        }
        return c;
    }

    private void showValidationError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
    }

    public void clearForm() {
        txtConcessionId.setText("");
        txtStudentId.setText("");
        txtStudentName.setText("");
        if (cmbConcessionType.getItemCount() > 0) cmbConcessionType.setSelectedIndex(0);
        txtAmount.setText("");
        txtAcademicYear.setText("2026-27");
        if (cmbStatus.getItemCount() > 0) cmbStatus.setSelectedIndex(0);
        txtRemarks.setText("");
        txtAppliedDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        table.clearSelection();
    }

    public void refreshData() {
        loadTableData(feeConcessionDAO.getAll());
    }
}
