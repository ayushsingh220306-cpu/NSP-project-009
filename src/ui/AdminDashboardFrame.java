package ui;

import dao.FeeConcessionDAO;
import dao.ScholarshipDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigDecimal;

/**
 * Admin Dashboard Frame for managing all scholarship and fee concession records.
 * Provides statistics overview, management tabs, password change, and logout flows.
 */
public class AdminDashboardFrame extends JFrame {

    private final User currentUser;
    private final ScholarshipDAO scholarshipDAO;
    private final FeeConcessionDAO feeConcessionDAO;

    private JTabbedPane tabbedPane;
    private ManageScholarshipPanel scholarshipPanel;
    private ManageFeeConcessionPanel feeConcessionPanel;

    // Stats labels on Overview Tab
    private JLabel lblTotalScholarships;
    private JLabel lblApprovedScholarshipAmount;
    private JLabel lblTotalConcessions;
    private JLabel lblApprovedConcessionAmount;

    public AdminDashboardFrame(User user) {
        this.currentUser = user;
        this.scholarshipDAO = new ScholarshipDAO();
        this.feeConcessionDAO = new FeeConcessionDAO();

        setTitle("Student Scholarship & Fee Concession System - Admin Portal | PIET (AYUSH SINGH - PIET25CR009)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setMinimumSize(new Dimension(950, 600));
        setLocationRelativeTo(null);

        initMenuBar();
        initComponents();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        // Navigation Menu
        JMenu menuNav = new JMenu("Modules");
        menuNav.setFont(UIStyle.FONT_LABEL);

        JMenuItem miOverview = new JMenuItem("Dashboard Overview");
        miOverview.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        JMenuItem miScholarships = new JMenuItem("Scholarship Management");
        miScholarships.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        JMenuItem miConcessions = new JMenuItem("Fee Concession Management");
        miConcessions.addActionListener(e -> tabbedPane.setSelectedIndex(2));

        menuNav.add(miOverview);
        menuNav.add(miScholarships);
        menuNav.add(miConcessions);

        // Account Menu
        JMenu menuAccount = new JMenu("Account");
        menuAccount.setFont(UIStyle.FONT_LABEL);

        JMenuItem miChangePass = new JMenuItem("Change Password");
        miChangePass.addActionListener(e -> openChangePassword());
        JMenuItem miLogout = new JMenuItem("Logout");
        miLogout.addActionListener(e -> handleLogout());
        JMenuItem miExit = new JMenuItem("Exit Application");
        miExit.addActionListener(e -> System.exit(0));

        menuAccount.add(miChangePass);
        menuAccount.addSeparator();
        menuAccount.add(miLogout);
        menuAccount.add(miExit);

        // Help Menu
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setFont(UIStyle.FONT_LABEL);
        JMenuItem miAbout = new JMenuItem("About PIET Minor Project");
        miAbout.addActionListener(e -> showAboutDialog());
        menuHelp.add(miAbout);

        menuBar.add(menuNav);
        menuBar.add(menuAccount);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);
    }

    private void initComponents() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        // TOP BANNER
        JPanel headerPanel = new JPanel(new BorderLayout(15, 5));
        headerPanel.setBackground(UIStyle.COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(12, 20, 12, 20));

        JPanel headerTextPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        headerTextPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("STUDENT SCHOLARSHIP & FEE CONCESSION SYSTEM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Poornima Institute of Engineering & Technology (PIET) | Session 2026-27 | Admin: " +
                currentUser.getFullName());
        lblSub.setFont(UIStyle.FONT_SUBTITLE);
        lblSub.setForeground(new Color(224, 231, 255));

        headerTextPanel.add(lblTitle);
        headerTextPanel.add(lblSub);
        headerPanel.add(headerTextPanel, BorderLayout.WEST);

        // Action Buttons in Header
        JPanel topActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        topActions.setOpaque(false);

        JButton btnChangePassword = UIStyle.createCustomButton("Change Password", new Color(37, 99, 235), Color.WHITE);
        JButton btnLogout = UIStyle.createCustomButton("Logout", UIStyle.COLOR_DANGER, Color.WHITE);

        btnChangePassword.addActionListener(e -> openChangePassword());
        btnLogout.addActionListener(e -> handleLogout());

        topActions.add(btnChangePassword);
        topActions.add(btnLogout);
        headerPanel.add(topActions, BorderLayout.EAST);

        rootPanel.add(headerPanel, BorderLayout.NORTH);

        // CENTER TABBED PANE
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(UIStyle.FONT_BUTTON);
        tabbedPane.setBackground(UIStyle.COLOR_BACKGROUND);

        // Tab 1: Overview
        tabbedPane.addTab("  Dashboard Overview  ", createOverviewTab());

        // Tab 2: Scholarship Management
        scholarshipPanel = new ManageScholarshipPanel(false);
        tabbedPane.addTab("  Scholarship Records  ", scholarshipPanel);

        // Tab 3: Fee Concession Management
        feeConcessionPanel = new ManageFeeConcessionPanel(false);
        tabbedPane.addTab("  Fee Concessions  ", feeConcessionPanel);

        // Refresh stats when switching to Overview tab
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) {
                refreshStats();
            }
        });

        rootPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(rootPanel);
    }

    private JPanel createOverviewTab() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(UIStyle.COLOR_BACKGROUND);
        panel.setBorder(new EmptyBorder(18, 20, 18, 20));

        // STATS CARDS GRID (2x2)
        JPanel statsGrid = new JPanel(new GridLayout(2, 2, 16, 16));
        statsGrid.setOpaque(false);

        lblTotalScholarships = new JLabel("0", JLabel.CENTER);
        lblApprovedScholarshipAmount = new JLabel("Rs 0.00", JLabel.CENTER);
        lblTotalConcessions = new JLabel("0", JLabel.CENTER);
        lblApprovedConcessionAmount = new JLabel("Rs 0.00", JLabel.CENTER);

        statsGrid.add(createStatCard("Total Scholarship Applications", lblTotalScholarships, new Color(37, 99, 235)));
        statsGrid.add(createStatCard("Total Approved Scholarship Fund", lblApprovedScholarshipAmount, new Color(22, 163, 74)));
        statsGrid.add(createStatCard("Total Fee Concession Requests", lblTotalConcessions, new Color(147, 51, 234)));
        statsGrid.add(createStatCard("Total Approved Concession Amount", lblApprovedConcessionAmount, new Color(217, 119, 6)));

        panel.add(statsGrid, BorderLayout.NORTH);

        // QUICK LAUNCH & SYSTEM INFO
        JPanel centerGrid = new JPanel(new GridLayout(1, 2, 16, 16));
        centerGrid.setOpaque(false);

        // Quick Launch Actions Card
        JPanel launchCard = UIStyle.createCardPanel();
        launchCard.setLayout(new BorderLayout(10, 10));

        JLabel lblLaunchTitle = new JLabel("Quick Action Modules");
        lblLaunchTitle.setFont(UIStyle.FONT_HEADER);
        lblLaunchTitle.setForeground(UIStyle.COLOR_PRIMARY);
        launchCard.add(lblLaunchTitle, BorderLayout.NORTH);

        JPanel btnGrid = new JPanel(new GridLayout(3, 1, 10, 10));
        btnGrid.setOpaque(false);

        JButton btnGoScholarship = UIStyle.createPrimaryButton("Open Scholarship Management Module");
        JButton btnGoConcession = UIStyle.createPrimaryButton("Open Fee Concession Management Module");
        JButton btnRefreshAll = UIStyle.createSearchButton("Refresh Live Statistics & Database Cache");

        btnGoScholarship.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        btnGoConcession.addActionListener(e -> tabbedPane.setSelectedIndex(2));
        btnRefreshAll.addActionListener(e -> {
            refreshStats();
            scholarshipPanel.refreshData();
            feeConcessionPanel.refreshData();
            JOptionPane.showMessageDialog(this, "Data and statistics refreshed from MySQL!", "Refreshed", JOptionPane.INFORMATION_MESSAGE);
        });

        btnGrid.add(btnGoScholarship);
        btnGrid.add(btnGoConcession);
        btnGrid.add(btnRefreshAll);
        launchCard.add(btnGrid, BorderLayout.CENTER);

        // Student Project Information Card
        JPanel infoCard = UIStyle.createCardPanel();
        infoCard.setLayout(new BorderLayout(10, 10));

        JLabel lblInfoTitle = new JLabel("PIET Minor Project NSP Credentials");
        lblInfoTitle.setFont(UIStyle.FONT_HEADER);
        lblInfoTitle.setForeground(UIStyle.COLOR_PRIMARY);
        infoCard.add(lblInfoTitle, BorderLayout.NORTH);

        JTextArea txtProjectDetails = new JTextArea();
        txtProjectDetails.setEditable(false);
        txtProjectDetails.setFont(UIStyle.FONT_REGULAR);
        txtProjectDetails.setForeground(UIStyle.COLOR_TEXT_DARK);
        txtProjectDetails.setText(
                "Student Name  : AYUSH SINGH\n" +
                "Student ID    : PIET25CR009\n" +
                "Project Title : Student Scholarship & Fee Concession System\n" +
                "Institution   : Poornima Institute of Engineering & Technology (PIET)\n" +
                "Course        : B.Tech II Year - III Semester\n" +
                "Session       : 2026-27 | Section: 3CSD\n" +
                "Tech Stack    : Java Swing, JDBC, MySQL, IntelliJ IDEA\n" +
                "Target        : PIET Minor Project Evaluation & Demonstration"
        );
        txtProjectDetails.setBackground(new Color(248, 250, 252));
        txtProjectDetails.setBorder(new EmptyBorder(10, 10, 10, 10));
        infoCard.add(txtProjectDetails, BorderLayout.CENTER);

        centerGrid.add(launchCard);
        centerGrid.add(infoCard);
        panel.add(centerGrid, BorderLayout.CENTER);

        refreshStats();
        return panel;
    }

    private JPanel createStatCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(UIStyle.COLOR_BORDER, 1, true),
                new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(UIStyle.FONT_LABEL);
        lblTitle.setForeground(UIStyle.COLOR_TEXT_MUTED);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        valueLabel.setForeground(accentColor);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    private void refreshStats() {
        int countSch = scholarshipDAO.getTotalCount();
        BigDecimal sumSch = scholarshipDAO.getTotalApprovedAmount();
        int countCon = feeConcessionDAO.getTotalCount();
        BigDecimal sumCon = feeConcessionDAO.getTotalApprovedAmount();

        lblTotalScholarships.setText(String.valueOf(countSch));
        lblApprovedScholarshipAmount.setText("Rs " + String.format("%,.2f", sumSch));
        lblTotalConcessions.setText(String.valueOf(countCon));
        lblApprovedConcessionAmount.setText("Rs " + String.format("%,.2f", sumCon));
    }

    private void openChangePassword() {
        ChangePasswordDialog dialog = new ChangePasswordDialog(this, currentUser);
        dialog.setVisible(true);
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out from the Admin portal?",
                "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
                "Student Scholarship & Fee Concession System\n" +
                "Minor Project (NSP) - B.Tech II Year, III Semester\n" +
                "Poornima Institute of Engineering & Technology (PIET)\n\n" +
                "Student Name : AYUSH SINGH\n" +
                "Student ID   : PIET25CR009\n" +
                "Session      : 2026-27 | Section: 3CSD\n\n" +
                "Developed using Java, Java Swing, JDBC, and MySQL.",
                "About Project", JOptionPane.INFORMATION_MESSAGE);
    }
}
