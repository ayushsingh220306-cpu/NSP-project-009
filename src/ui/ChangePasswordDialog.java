package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Modal dialog allowing users to securely change their password.
 */
public class ChangePasswordDialog extends JDialog {

    private final User currentUser;
    private final UserDAO userDAO;

    private JPasswordField txtCurrentPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnSubmit;
    private JButton btnCancel;

    public ChangePasswordDialog(Frame parent, User user) {
        super(parent, "Change Password - Student Scholarship & Fee System", true);
        this.currentUser = user;
        this.userDAO = new UserDAO();

        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(UIStyle.COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Header
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        headerPanel.setBackground(UIStyle.COLOR_BACKGROUND);
        JLabel lblTitle = new JLabel("Update Account Password");
        lblTitle.setFont(UIStyle.FONT_HEADER);
        lblTitle.setForeground(UIStyle.COLOR_PRIMARY);

        JLabel lblSub = new JLabel("User: " + currentUser.getUsername() + " (" + currentUser.getFullName() + ")");
        lblSub.setFont(UIStyle.FONT_SUBTITLE);
        lblSub.setForeground(UIStyle.COLOR_TEXT_MUTED);

        headerPanel.add(lblTitle);
        headerPanel.add(lblSub);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form Card Panel
        JPanel cardPanel = UIStyle.createCardPanel();
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCur = new JLabel("Current Password:");
        lblCur.setFont(UIStyle.FONT_LABEL);
        txtCurrentPassword = new JPasswordField(18);
        UIStyle.styleTextField(txtCurrentPassword);

        JLabel lblNew = new JLabel("New Password:");
        lblNew.setFont(UIStyle.FONT_LABEL);
        txtNewPassword = new JPasswordField(18);
        UIStyle.styleTextField(txtNewPassword);

        JLabel lblConfirm = new JLabel("Confirm New Password:");
        lblConfirm.setFont(UIStyle.FONT_LABEL);
        txtConfirmPassword = new JPasswordField(18);
        UIStyle.styleTextField(txtConfirmPassword);

        // Row 0: Current Password
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        cardPanel.add(lblCur, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        cardPanel.add(txtCurrentPassword, gbc);

        // Row 1: New Password
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        cardPanel.add(lblNew, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        cardPanel.add(txtNewPassword, gbc);

        // Row 2: Confirm Password
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        cardPanel.add(lblConfirm, gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.7;
        cardPanel.add(txtConfirmPassword, gbc);

        mainPanel.add(cardPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        btnPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        btnSubmit = UIStyle.createPrimaryButton("Update Password");
        btnCancel = UIStyle.createNeutralButton("Cancel");

        btnSubmit.addActionListener(e -> handleChangePassword());
        btnCancel.addActionListener(e -> dispose());

        btnPanel.add(btnSubmit);
        btnPanel.add(btnCancel);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void handleChangePassword() {
        String currentPass = new String(txtCurrentPassword.getPassword()).trim();
        String newPass = new String(txtNewPassword.getPassword()).trim();
        String confirmPass = new String(txtConfirmPassword.getPassword()).trim();

        // Validations
        if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all password fields.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (newPass.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "New password must be at least 4 characters long.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this,
                    "New password and confirm password do not match.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verify old password against database
        boolean matches = userDAO.verifyCurrentPassword(currentUser.getUserId(), currentPass);
        if (!matches) {
            JOptionPane.showMessageDialog(this,
                    "Current password entered is incorrect.",
                    "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform update in database
        boolean success = userDAO.changePassword(currentUser.getUserId(), newPass);
        if (success) {
            currentUser.setPassword(newPass);
            JOptionPane.showMessageDialog(this,
                    "Password updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update password. Please check database connection.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
