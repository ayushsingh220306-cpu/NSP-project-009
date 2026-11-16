package ui;

import dao.UserDAO;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ScreenshotGenerator captures genuine, high-resolution PNG screenshots
 * of all application screens directly from the live Java Swing component tree.
 */
public class ScreenshotGenerator {

    private static final String OUTPUT_DIR = "screenshots";

    public static void main(String[] args) {
        System.out.println("Starting automated UI Screenshot Generator...");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        File dir = new File(OUTPUT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        UserDAO userDAO = new UserDAO();
        User admin = userDAO.authenticate("admin", "admin123");
        if (admin == null) {
            admin = new User(1, "admin", "admin123", "Ayush Singh (Admin)", "Admin", null);
        }

        User student = userDAO.authenticate("user1", "user123");
        if (student == null) {
            student = new User(2, "user1", "user123", "Rahul Sharma", "User", null);
        }

        // 1. Capture Login Screen
        captureLoginScreen();

        // 2. Capture Admin Dashboard (Overview)
        captureAdminOverview(admin);

        // 3. Capture Scholarship Management (Admin CRUD)
        captureScholarshipManagement(admin);

        // 4. Capture Fee Concession Management (Admin CRUD)
        captureFeeConcessionManagement(admin);

        // 5. Capture Student User Dashboard (Read-Only)
        captureUserDashboard(student);

        // 6. Capture Change Password Dialog
        captureChangePasswordDialog(admin);

        // 7. Capture Delete Confirmation Dialog
        captureDeleteConfirmationDialog(admin);

        System.out.println("All screenshots successfully captured in: " + dir.getAbsolutePath());
        System.exit(0);
    }

    private static void captureLoginScreen() {
        try {
            LoginFrame frame = new LoginFrame();
            frame.pack();
            frame.setSize(520, 580);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            Thread.sleep(200);

            saveWindowImage(frame, OUTPUT_DIR + "/01_login_screen.png");
            frame.dispose();
            System.out.println("  Saved: 01_login_screen.png");
        } catch (Exception e) {
            System.err.println("Error capturing Login Screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void captureAdminOverview(User admin) {
        try {
            AdminDashboardFrame frame = new AdminDashboardFrame(admin);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = findTabbedPane(frame);
            if (tabs != null) tabs.setSelectedIndex(0);

            frame.setVisible(true);
            Thread.sleep(200);

            saveWindowImage(frame, OUTPUT_DIR + "/02_admin_dashboard.png");
            frame.dispose();
            System.out.println("  Saved: 02_admin_dashboard.png");
        } catch (Exception e) {
            System.err.println("Error capturing Admin Overview: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void captureScholarshipManagement(User admin) {
        try {
            AdminDashboardFrame frame = new AdminDashboardFrame(admin);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = findTabbedPane(frame);
            if (tabs != null) tabs.setSelectedIndex(1);

            frame.setVisible(true);
            Thread.sleep(200);

            saveWindowImage(frame, OUTPUT_DIR + "/03_scholarship_management.png");
            frame.dispose();
            System.out.println("  Saved: 03_scholarship_management.png");
        } catch (Exception e) {
            System.err.println("Error capturing Scholarship Management: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void captureFeeConcessionManagement(User admin) {
        try {
            AdminDashboardFrame frame = new AdminDashboardFrame(admin);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = findTabbedPane(frame);
            if (tabs != null) tabs.setSelectedIndex(2);

            frame.setVisible(true);
            Thread.sleep(200);

            saveWindowImage(frame, OUTPUT_DIR + "/04_fee_concession_management.png");
            frame.dispose();
            System.out.println("  Saved: 04_fee_concession_management.png");
        } catch (Exception e) {
            System.err.println("Error capturing Fee Concession Management: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void captureUserDashboard(User student) {
        try {
            UserDashboardFrame frame = new UserDashboardFrame(student);
            frame.setSize(1050, 680);
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
            Thread.sleep(200);

            saveWindowImage(frame, OUTPUT_DIR + "/05_user_dashboard.png");
            frame.dispose();
            System.out.println("  Saved: 05_user_dashboard.png");
        } catch (Exception e) {
            System.err.println("Error capturing User Dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void captureChangePasswordDialog(User admin) {
        try {
            JFrame dummy = new JFrame();
            dummy.setSize(1, 1);
            dummy.setLocationRelativeTo(null);
            dummy.setVisible(true);

            ChangePasswordDialog dialog = new ChangePasswordDialog(dummy, admin);
            dialog.setSize(440, 320);
            dialog.setLocationRelativeTo(null);

            // Run in a thread since modal dialog blocks
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(150);
                    saveWindowImage(dialog, OUTPUT_DIR + "/06_change_password_dialog.png");
                    System.out.println("  Saved: 06_change_password_dialog.png");
                    dialog.dispose();
                    dummy.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            dialog.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error capturing Change Password Dialog: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void captureDeleteConfirmationDialog(User admin) {
        try {
            JFrame dummy = new JFrame();
            dummy.setSize(1, 1);
            dummy.setLocationRelativeTo(null);
            dummy.setVisible(true);

            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(150);
                    Window[] windows = Window.getWindows();
                    for (Window w : windows) {
                        if (w instanceof JDialog && w.isVisible()) {
                            saveWindowImage(w, OUTPUT_DIR + "/07_delete_confirmation_dialog.png");
                            System.out.println("  Saved: 07_delete_confirmation_dialog.png");
                            w.dispose();
                            break;
                        }
                    }
                    dummy.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            JOptionPane.showConfirmDialog(dummy,
                    "Are you sure you want to delete scholarship record ID 6 for Neha Choudhary?\nThis action cannot be undone.",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error capturing Delete Confirmation Dialog: " + e.getMessage());
        }
    }

    private static JTabbedPane findTabbedPane(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTabbedPane) {
                return (JTabbedPane) c;
            } else if (c instanceof Container) {
                JTabbedPane found = findTabbedPane((Container) c);
                if (found != null) return found;
            }
        }
        return null;
    }

    private static void saveWindowImage(Window window, String fileName) throws Exception {
        int width = Math.max(window.getWidth(), 300);
        int height = Math.max(window.getHeight(), 200);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Fill background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        window.paint(g2d);
        g2d.dispose();

        ImageIO.write(image, "png", new File(fileName));
    }
}
