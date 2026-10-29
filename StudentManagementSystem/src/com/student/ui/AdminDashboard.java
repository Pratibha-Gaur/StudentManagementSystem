package com.student.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80));
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 600, 100);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(100, 20, 400, 30);
        headerPanel.add(titleLabel);

        // Welcome Label
        welcomeLabel = new JLabel("Welcome, Admin!");
        welcomeLabel.setForeground(new Color(52, 152, 219));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setBounds(220, 55, 200, 25);
        headerPanel.add(welcomeLabel);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241));
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 100, 600, 400);
        mainPanel.add(buttonPanel);

        // Panel Title
        JLabel menuLabel = new JLabel("ADMIN PANEL");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuLabel.setForeground(new Color(44, 62, 80));
        menuLabel.setBounds(230, 20, 200, 30);
        buttonPanel.add(menuLabel);

        // Add Student Button
        addStudentButton = createButton("ADD STUDENT", new Color(46, 204, 113));
        addStudentButton.setBounds(100, 70, 180, 50);
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddStudentForm().setVisible(true);
            }
        });
        buttonPanel.add(addStudentButton);

        // View Students Button
        viewStudentsButton = createButton("VIEW ALL STUDENTS", new Color(52, 152, 219));
        viewStudentsButton.setBounds(320, 70, 180, 50);
        viewStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(viewStudentsButton);

        // Search Student Button
        searchStudentButton = createButton("SEARCH STUDENT", new Color(155, 89, 182));
        searchStudentButton.setBounds(100, 150, 180, 50);
        searchStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(searchStudentButton);

        // Update Student Button
        updateStudentButton = createButton("UPDATE STUDENT", new Color(230, 126, 34));
        updateStudentButton.setBounds(320, 150, 180, 50);
        updateStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(updateStudentButton);

        // Delete Student Button
        deleteStudentButton = createButton("DELETE STUDENT", new Color(231, 76, 60));
        deleteStudentButton.setBounds(100, 230, 180, 50);
        deleteStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsForm().setVisible(true);
            }
        });
        buttonPanel.add(deleteStudentButton);

        // Logout Button
        logoutButton = createButton("LOGOUT", new Color(127, 140, 141));
        logoutButton.setBounds(320, 230, 180, 50);
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

    // Helper method to create styled buttons
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }
}