package com.student.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Frontend-only UI theme helper.
 * Provides a consistent color palette, fonts and styled components for all
 * Swing forms. Contains no database or backend logic.
 */
public class UIStyle {

    // Theme colors
    public static final Color PRIMARY_DARK   = new Color(44, 62, 80);   // #2C3E50
    public static final Color PRIMARY_BLUE   = new Color(52, 152, 219); // #3498DB
    public static final Color SUCCESS_GREEN  = new Color(46, 204, 113); // #2ECC71
    public static final Color WARNING_ORANGE = new Color(230, 126, 34); // #E67E22
    public static final Color ERROR_RED      = new Color(231, 76, 60);  // #E74C3C
    public static final Color PURPLE         = new Color(155, 89, 182); // #9B59B6
    public static final Color GRAY_BG        = new Color(236, 240, 241);// #ECF0F1
    public static final Color PANEL_GRAY     = new Color(189, 195, 199);// #BDC3C7
    public static final Color TABLE_ZEBRA    = new Color(245, 247, 249);
    public static final Color WHITE          = Color.WHITE;

    // Fonts
    public static final Font FONT_TITLE    = new Font("Arial", Font.BOLD, 20);
    public static final Font FONT_SUBTITLE = new Font("Arial", Font.BOLD, 15);
    public static final Font FONT_LABEL    = new Font("Arial", Font.BOLD, 13);
    public static final Font FONT_FIELD    = new Font("Arial", Font.PLAIN, 13);
    public static final Font FONT_BUTTON   = new Font("Arial", Font.BOLD, 13);
    public static final Font FONT_MESSAGE  = new Font("Arial", Font.BOLD, 12);

    private UIStyle() {
        // Utility class - not instantiable
    }

    /**
     * Creates a themed button with hover and press effects.
     */
    public static JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(FONT_BUTTON);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(6, 16, 6, 16));

        final Color hover = darker(bgColor);
        final Color normal = bgColor;

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
            }
        });
        return button;
    }

    private static Color darker(Color c) {
        return new Color(
            Math.max(0, c.getRed() - 25),
            Math.max(0, c.getGreen() - 25),
            Math.max(0, c.getBlue() - 25));
    }

    /**
     * Applies the standard field font to a text component.
     */
    public static void styleField(JTextField field) {
        field.setFont(FONT_FIELD);
    }

    /**
     * Applies color and font to a label.
     */
    public static void styleLabel(JLabel label, Color color, Font font) {
        label.setForeground(color);
        label.setFont(font);
    }

    /**
     * Paints table rows with zebra striping when not selected.
     */
    public static void applyZebraStriping(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value,
                        isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : TABLE_ZEBRA);
                }
                return c;
            }
        });
    }
}
