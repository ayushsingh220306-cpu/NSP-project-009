import ui.LoginFrame;

import javax.swing.*;

/**
 * Application Entry Point for Student Scholarship & Fee Concession System.
 * 
 * B.Tech II Year – III Semester Minor Project (NSP)
 * Poornima Institute of Engineering & Technology (PIET)
 * Session: 2026–27 | Section: 3CSD
 * 
 * Student Name : AYUSH SINGH
 * Student ID   : PIET25CR009
 */
public class Main {

    public static void main(String[] args) {
        // Apply System Look and Feel for native OS aesthetics
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fallback to standard cross-platform look and feel
        }

        // Launch Login GUI on Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
