package ui;

import dao.UserDAO;
import db.DBConnection;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Login Screen for Student Scholarship & Fee Concession System.
 * Supports Admin and Student/User authentication against MySQL.
 */
public class LoginFrame extends JFrame {

    private final UserDAO userDAO;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnReset;
    private JLabel lblDbStatus;

    public LoginFrame() {
        this.userDAO = new UserDAO();

        setTitle("Student Scholarship & Fee Concession System - Login | PIET");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 560);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();
        checkDatabaseStatus();
    }

    private void initComponents() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        // TOP BANNER
        JPanel bannerPanel = new JPanel(new GridLayout(3, 1, 3, 3));
        bannerPanel.setBackground(UIStyle.COLOR_PRIMARY);
        bannerPanel.setBorder(new EmptyBorder(22, 20, 22, 20));

        JLabel lblCollege = new JLabel("POORNIMA INSTITUTE OF ENGINEERING & TECHNOLOGY", JLabel.CENTER);
        lblCollege.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCollege.setForeground(new Color(224, 231, 255));

        JLabel lblSystemTitle = new JLabel("Scholarship & Fee Concession System", JLabel.CENTER);
        lblSystemTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblSystemTitle.setForeground(Color.WHITE);

        JLabel lblStudent = new JLabel("B.Tech Minor Project (NSP) | AYUSH SINGH (PIET25CR009)", JLabel.CENTER);
        lblStudent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStudent.setForeground(new Color(191, 219, 254));

        bannerPanel.add(lblCollege);
        bannerPanel.add(lblSystemTitle);
        bannerPanel.add(lblStudent);
        rootPanel.add(bannerPanel, BorderLayout.NORTH);

        // CENTER: Login Card
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UIStyle.COLOR_BACKGROUND);
        centerWrapper.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel loginCard = UIStyle.createCardPanel();
        loginCard.setLayout(new BorderLayout(15, 15));
        loginCard.setPreferredSize(new Dimension(420, 260));

        JLabel lblCardTitle = new JLabel("Account Login", JLabel.CENTER);
        lblCardTitle.setFont(UIStyle.FONT_TITLE);
        lblCardTitle.setForeground(UIStyle.COLOR_PRIMARY);
        loginCard.add(lblCardTitle, BorderLayout.NORTH);

        // Fields Panel
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(UIStyle.COLOR_SURFACE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(UIStyle.FONT_LABEL);
        txtUsername = new JTextField(15);
        UIStyle.styleTextField(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(UIStyle.FONT_LABEL);
        txtPassword = new JPasswordField(15);
        UIStyle.styleTextField(txtPassword);

        // Enter key triggers login
        KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }
        };
        txtUsername.addKeyListener(enterListener);
        txtPassword.addKeyListener(enterListener);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        fieldsPanel.add(lblUser, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        fieldsPanel.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        fieldsPanel.add(lblPass, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        fieldsPanel.add(txtPassword, gbc);

        loginCard.add(fieldsPanel, BorderLayout.CENTER);

        // Action Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.setBackground(UIStyle.COLOR_SURFACE);

        btnLogin = UIStyle.createPrimaryButton("Login");
        btnReset = UIStyle.createNeutralButton("Clear");

        btnLogin.setPreferredSize(new Dimension(130, 36));
        btnReset.setPreferredSize(new Dimension(100, 36));

        btnLogin.addActionListener(e -> handleLogin());
        btnReset.addActionListener(e -> {
            txtUsername.setText("");
            txtPassword.setText("");
            txtUsername.requestFocus();
        });

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnReset);
        loginCard.add(buttonPanel, BorderLayout.SOUTH);

        centerWrapper.add(loginCard);
        rootPanel.add(centerWrapper, BorderLayout.CENTER);

        // BOTTOM: Demo Credentials Helper & DB Status
        JPanel footerPanel = new JPanel(new BorderLayout(5, 5));
        footerPanel.setBackground(UIStyle.COLOR_BACKGROUND);
        footerPanel.setBorder(new EmptyBorder(5, 20, 15, 20));

        JPanel demoHintPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        demoHintPanel.setBackground(new Color(238, 242, 255));
        demoHintPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(199, 210, 254), 1, true),
                new EmptyBorder(6, 12, 6, 12)
        ));

        JLabel lblHint1 = new JLabel("Demo Admin: admin / admin123  |  Demo Student: user1 / user123");
        lblHint1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblHint1.setForeground(UIStyle.COLOR_PRIMARY);

        JLabel lblHint2 = new JLabel("Roles automatically route to Admin or Student portal upon authentication.");
        lblHint2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblHint2.setForeground(UIStyle.COLOR_TEXT_MUTED);

        demoHintPanel.add(lblHint1);
        demoHintPanel.add(lblHint2);
        footerPanel.add(demoHintPanel, BorderLayout.NORTH);

        lblDbStatus = new JLabel("Checking MySQL connection...", JLabel.CENTER);
        lblDbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDbStatus.setBorder(new EmptyBorder(6, 0, 0, 0));
        footerPanel.add(lblDbStatus, BorderLayout.SOUTH);

        rootPanel.add(footerPanel, BorderLayout.SOUTH);

        setContentPane(rootPanel);
    }

    private void checkDatabaseStatus() {
        SwingUtilities.invokeLater(() -> {
            boolean connected = DBConnection.testConnection();
            if (connected) {
                lblDbStatus.setText("● MySQL Connected (localhost:3306 / scholarship_fee_db)");
                lblDbStatus.setForeground(UIStyle.COLOR_SUCCESS);
            } else {
                lblDbStatus.setText("▲ Database Offline - Please ensure MySQL is running on port 3306");
                lblDbStatus.setForeground(UIStyle.COLOR_DANGER);
            }
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // 1. Validation: Empty fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username and password fields cannot be empty.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Database Connection Check
        if (!DBConnection.testConnection()) {
            JOptionPane.showMessageDialog(this,
                    "Could not connect to MySQL database.\nPlease verify MySQL is running on port 3306 and schema.sql has been executed.",
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Authenticate User
        User user = userDAO.authenticate(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password. Please check your credentials.",
                    "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
            return;
        }

        // 4. Role-based Redirection
        JOptionPane.showMessageDialog(this,
                "Login Successful!\nWelcome, " + user.getFullName() + " (" + user.getRole() + ")",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();

        SwingUtilities.invokeLater(() -> {
            if (user.isAdmin()) {
                new AdminDashboardFrame(user).setVisible(true);
            } else {
                new UserDashboardFrame(user).setVisible(true);
            }
        });
    }
}
