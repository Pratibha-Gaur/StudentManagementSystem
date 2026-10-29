package com.student.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Frontend: Admin dashboard. Provides access to student management features.
 */
public class AdminDashboard extends JFrame {

    // Components
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JLabel welcomeLabel;
    private JButton addStudentButton;
    private JButton viewStudentsButton;
    private JButton searchStudentButton;
    private JButton updateStudentButton;
    private JButton deleteStudentButton;
    private JButton logoutButton;

    public AdminDashboard() {
        setupDashboard();
    }

    private void setupDashboard() {

        // Frame settings
        setTitle("Student Management System - Admin Dashboard");
        setSize(650, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(UIStyle.GRAY_BG);
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(UIStyle.PRIMARY_DARK);
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 650, 110);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(UIStyle.FONT_TITLE);
        titleLabel.setBounds(100, 20, 450, 30);
        headerPanel.add(titleLabel);

        // Welcome Label
        welcomeLabel = new JLabel("Welcome, Admin!");
        welcomeLabel.setForeground(UIStyle.PRIMARY_BLUE);
        welcomeLabel.setFont(UIStyle.FONT_SUBTITLE);
        welcomeLabel.setBounds(220, 60, 200, 25);
        headerPanel.add(welcomeLabel);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(UIStyle.GRAY_BG);
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 110, 650, 430);
        mainPanel.add(buttonPanel);

        // Panel Title
        JLabel menuLabel = new JLabel("ADMIN PANEL");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuLabel.setForeground(UIStyle.PRIMARY_DARK);
        menuLabel.setBounds(230, 20, 200, 30);
        buttonPanel.add(menuLabel);

        // Add Student Button
        addStudentButton = UIStyle.createButton("ADD STUDENT", UIStyle.SUCCESS_GREEN);
        addStudentButton.setBounds(100, 70, 180, 50);
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddStudentForm().setVisible(true);
            }
        });
        buttonPanel.add(addStudentButton);

        // View Students Button
        viewStudentsButton = UIStyle.createButton("VIEW ALL STUDENTS", UIStyle.PRIMARY_BLUE);
        viewStudentsButton.setBounds(370, 70, 180, 50);
        viewStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(viewStudentsButton);

        // Search Student Button
        searchStudentButton = UIStyle.createButton("SEARCH STUDENT", UIStyle.PURPLE);
        searchStudentButton.setBounds(100, 150, 180, 50);
        searchStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(searchStudentButton);

        // Update Student Button
        updateStudentButton = UIStyle.createButton("UPDATE STUDENT", UIStyle.WARNING_ORANGE);
        updateStudentButton.setBounds(370, 150, 180, 50);
        updateStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(updateStudentButton);

        // Delete Student Button
        deleteStudentButton = UIStyle.createButton("DELETE STUDENT", UIStyle.ERROR_RED);
        deleteStudentButton.setBounds(100, 230, 180, 50);
        deleteStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(deleteStudentButton);

        // Logout Button
        logoutButton = UIStyle.createButton("LOGOUT", new Color(127, 140, 141));
        logoutButton.setBounds(370, 230, 180, 50);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
                );
                if (choice == JOptionPane.YES_OPTION) {
                    new LoginForm().setVisible(true);
                    dispose();
                }
            }
        });
        buttonPanel.add(logoutButton);

        // Add main panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }
}
