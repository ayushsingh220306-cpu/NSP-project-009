package ui;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * User Dashboard Frame providing student/normal users with restricted
 * read-only access to view and search scholarship and fee concession records.
 */
public class UserDashboardFrame extends JFrame {

    private final User currentUser;
    private JTabbedPane tabbedPane;

    public UserDashboardFrame(User user) {
        this.currentUser = user;

        setTitle("Student Scholarship & Fee Concession System - Student Portal | PIET (" + user.getFullName() + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 680);
        setMinimumSize(new Dimension(900, 550));
        setLocationRelativeTo(null);

        initMenuBar();
        initComponents();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        JMenu menuNav = new JMenu("View Modules");
        menuNav.setFont(UIStyle.FONT_LABEL);

        JMenuItem miScholarships = new JMenuItem("Scholarship Records");
        miScholarships.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        JMenuItem miConcessions = new JMenuItem("Fee Concessions");
        miConcessions.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        JMenuItem miGuidelines = new JMenuItem("Guidelines & Notices");
        miGuidelines.addActionListener(e -> tabbedPane.setSelectedIndex(2));

        menuNav.add(miScholarships);
        menuNav.add(miConcessions);
        menuNav.add(miGuidelines);

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

        menuBar.add(menuNav);
        menuBar.add(menuAccount);
        setJMenuBar(menuBar);
    }

    private void initComponents() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(UIStyle.COLOR_BACKGROUND);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout(15, 5));
        headerPanel.setBackground(UIStyle.COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(12, 20, 12, 20));

        JPanel headerTextPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        headerTextPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("STUDENT SCHOLARSHIP & FEE CONCESSION SYSTEM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Student Portal (Read-Only Access) | Logged in as: " +
                currentUser.getFullName() + " (" + currentUser.getUsername() + ")");
        lblSub.setFont(UIStyle.FONT_SUBTITLE);
        lblSub.setForeground(new Color(224, 231, 255));

        headerTextPanel.add(lblTitle);
        headerTextPanel.add(lblSub);
        headerPanel.add(headerTextPanel, BorderLayout.WEST);

        // Header Actions
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

        // Center Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(UIStyle.FONT_BUTTON);
        tabbedPane.setBackground(UIStyle.COLOR_BACKGROUND);

        // Tab 1: Scholarship Records (Read-Only)
        ManageScholarshipPanel scholarshipPanel = new ManageScholarshipPanel(true);
        tabbedPane.addTab("  Scholarship Records  ", scholarshipPanel);

        // Tab 2: Fee Concession Records (Read-Only)
        ManageFeeConcessionPanel concessionPanel = new ManageFeeConcessionPanel(true);
        tabbedPane.addTab("  Fee Concessions  ", concessionPanel);

        // Tab 3: Institutional Guidelines & Policy
        tabbedPane.addTab("  Institutional Guidelines  ", createGuidelinesPanel());

        rootPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(rootPanel);
    }

    private JPanel createGuidelinesPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(UIStyle.COLOR_BACKGROUND);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel card = UIStyle.createCardPanel();
        card.setLayout(new BorderLayout(12, 12));

        JLabel lblTitle = new JLabel("Poornima Institute of Engineering & Technology - Policy Guidelines");
        lblTitle.setFont(UIStyle.FONT_HEADER);
        lblTitle.setForeground(UIStyle.COLOR_PRIMARY);
        card.add(lblTitle, BorderLayout.NORTH);

        JTextArea txt = new JTextArea();
        txt.setEditable(false);
        txt.setFont(UIStyle.FONT_REGULAR);
        txt.setForeground(UIStyle.COLOR_TEXT_DARK);
        txt.setBackground(new Color(248, 250, 252));
        txt.setBorder(new EmptyBorder(12, 12, 12, 12));
        txt.setText(
                "IMPORTANT INSTRUCTIONS FOR STUDENTS / SCHOLARSHIP APPLICANTS:\n\n" +
                "1. Read-Only Access Permissions:\n" +
                "   - Students can view, browse, and search approved and pending scholarship & fee concession records.\n" +
                "   - Direct modification (Add / Update / Delete) is restricted to the Administrative Office.\n\n" +
                "2. Scholarship Application Categories:\n" +
                "   - Merit Scholarship: Minimum 8.5 SGPA / Top 5% in semester examinations.\n" +
                "   - Need-Based Financial Aid: Verified annual family income certificate from competent authority.\n" +
                "   - Sports Excellence: Medals in State or National level AIU/SGFI tournaments.\n" +
                "   - PIET Star Scholar: Special institutional performance award.\n\n" +
                "3. Fee Concession Categories:\n" +
                "   - Sibling Concession: Applicable when two or more siblings are enrolled at PIET concurrently.\n" +
                "   - EWS Waiver: Governed by state government social welfare norms.\n" +
                "   - Staff Ward Concession: Verification through HR office.\n\n" +
                "4. Queries & Discrepancies:\n" +
                "   - For any discrepancies in student record or disbursement status, contact the Accounts & Scholarship Desk.\n" +
                "   - Minor Project (NSP) Developer: AYUSH SINGH (PIET25CR009), 3CSD."
        );

        card.add(new JScrollPane(txt), BorderLayout.CENTER);
        panel.add(card, BorderLayout.CENTER);
        return panel;
    }

    private void openChangePassword() {
        ChangePasswordDialog dialog = new ChangePasswordDialog(this, currentUser);
        dialog.setVisible(true);
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out from the Student portal?",
                "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }
}
