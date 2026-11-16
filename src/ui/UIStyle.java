package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * UIStyle defines a consistent, modern, and accessible styling palette
 * for the Swing application.
 * 
 * Ensures button texts are explicitly colored with high contrast
 * regardless of the native Windows Look & Feel.
 */
public class UIStyle {

    // Color Palette
    public static final Color COLOR_PRIMARY = new Color(30, 58, 138);       // Deep Navy
    public static final Color COLOR_PRIMARY_HOVER = new Color(37, 99, 235); // Royal Blue
    public static final Color COLOR_BACKGROUND = new Color(241, 245, 249);   // Slate 100
    public static final Color COLOR_SURFACE = new Color(255, 255, 255);      // Pure White
    public static final Color COLOR_TEXT_DARK = new Color(15, 23, 42);       // Slate 900
    public static final Color COLOR_TEXT_MUTED = new Color(71, 85, 105);    // Slate 600
    public static final Color COLOR_BORDER = new Color(203, 213, 225);       // Slate 300

    // Status / Action Colors
    public static final Color COLOR_SUCCESS = new Color(22, 163, 74);       // Emerald Green
    public static final Color COLOR_DANGER = new Color(220, 38, 38);         // Crimson Red
    public static final Color COLOR_WARNING = new Color(217, 119, 6);       // Amber
    public static final Color COLOR_INFO = new Color(2, 132, 199);          // Sky Blue
    public static final Color COLOR_NEUTRAL = new Color(75, 85, 99);        // Slate Gray

    // Typography
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_TABLE_ROW = new Font("Segoe UI", Font.PLAIN, 12);

    /**
     * Creates a styled button with explicit foreground and background colors.
     */
    public static JButton createCustomButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(FONT_BUTTON);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new CompoundBorder(
                new LineBorder(bgColor.darker(), 1, true),
                new EmptyBorder(7, 16, 7, 16)
        ));
        return button;
    }

    public static JButton createPrimaryButton(String text) {
        return createCustomButton(text, COLOR_PRIMARY, Color.WHITE);
    }

    public static JButton createSuccessButton(String text) {
        return createCustomButton(text, COLOR_SUCCESS, Color.WHITE);
    }

    public static JButton createDangerButton(String text) {
        return createCustomButton(text, COLOR_DANGER, Color.WHITE);
    }

    public static JButton createWarningButton(String text) {
        return createCustomButton(text, COLOR_WARNING, Color.WHITE);
    }

    public static JButton createNeutralButton(String text) {
        return createCustomButton(text, COLOR_NEUTRAL, Color.WHITE);
    }

    public static JButton createSearchButton(String text) {
        return createCustomButton(text, COLOR_INFO, Color.WHITE);
    }

    /**
     * Styles text field with clean borders and padding.
     */
    public static void styleTextField(JTextField field) {
        field.setFont(FONT_REGULAR);
        field.setForeground(COLOR_TEXT_DARK);
        field.setBackground(Color.WHITE);
        field.setCaretColor(COLOR_PRIMARY);
        field.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1),
                new EmptyBorder(5, 8, 5, 8)
        ));
    }

    /**
     * Styles combo box.
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(FONT_REGULAR);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(COLOR_TEXT_DARK);
        comboBox.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1),
                new EmptyBorder(3, 5, 3, 5)
        ));
    }

    /**
     * Styles JTable with header styling, alternating row colors, row height, and alignment.
     */
    public static void styleTable(JTable table) {
        table.setFont(FONT_TABLE_ROW);
        table.setRowHeight(26);
        table.setGridColor(COLOR_BORDER);
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(219, 234, 254)); // Soft blue
        table.setSelectionForeground(COLOR_TEXT_DARK);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        // Style Header with explicit custom renderer for 100% visibility across L&Fs
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 32));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                label.setBackground(COLOR_PRIMARY);
                label.setForeground(Color.WHITE);
                label.setFont(FONT_TABLE_HEADER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(59, 130, 246)),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                ));
                return label;
            }
        };
        header.setDefaultRenderer(headerRenderer);

        // Center align text in columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Creates a white surface card panel with padding.
     */
    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_SURFACE);
        panel.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));
        return panel;
    }
}
