package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard extends JFrame {

    // Components
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JLabel titleLabel;
    private JLabel welcomeLabel;
    private JLabel studentInfoLabel;
    private JButton viewProfileButton;
    private JButton gradeCalcButton;
    private JButton logoutButton;

    // Student data
    private String username;
    private String studentName;
    private String studentCourse;
    private double studentMarks;

    public StudentDashboard(String username) {
        this.username = username;
        loadStudentData();
        setupDashboard();
    }

    // Load student data from database
    private void loadStudentData() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT s.* FROM students s " +
                        "INNER JOIN users u ON u.username = ? " +
                        "WHERE s.email LIKE CONCAT('%', ?, '%') " +
                        "OR s.first_name LIKE CONCAT('%', ?, '%')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                studentName = rs.getString("first_name") + 
                             " " + rs.getString("last_name");
                studentCourse = rs.getString("course");
                studentMarks = rs.getDouble("marks");
            } else {
                studentName = username;
                studentCourse = "N/A";
                studentMarks = 0.0;
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            studentName = username;
            studentCourse = "N/A";
            studentMarks = 0.0;
        }
    }

    private void setupDashboard() {

        // Frame settings
        setTitle("Student Management System - Student Dashboard");
        setSize(600, 550);
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
        titleLabel.setBounds(90, 15, 430, 30);
        headerPanel.add(titleLabel);

        // Welcome Label
        welcomeLabel = new JLabel("Welcome, " + studentName + "!");
        welcomeLabel.setForeground(new Color(52, 152, 219));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        welcomeLabel.setBounds(190, 55, 300, 25);
        headerPanel.add(welcomeLabel);

        // Info Panel
        infoPanel = new JPanel();
        infoPanel.setBackground(new Color(52, 152, 219));
        infoPanel.setLayout(null);
        infoPanel.setBounds(30, 120, 540, 120);
        mainPanel.add(infoPanel);

        // Student Info
        JLabel infoTitle = new JLabel("YOUR INFORMATION");
        infoTitle.setForeground(Color.WHITE);
        infoTitle.setFont(new Font("Arial", Font.BOLD, 16));
        infoTitle.setBounds(170, 10, 250, 25);
        infoPanel.add(infoTitle);

        JLabel nameInfo = new JLabel("Name     :  " + studentName);
        nameInfo.setForeground(Color.WHITE);
        nameInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        nameInfo.setBounds(30, 45, 250, 20);
        infoPanel.add(nameInfo);

        JLabel courseInfo = new JLabel("Course   :  " + studentCourse);
        courseInfo.setForeground(Color.WHITE);
        courseInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        courseInfo.setBounds(30, 75, 250, 20);
        infoPanel.add(courseInfo);

        // Calculate grade
        String grade = calculateGrade(studentMarks);

        JLabel marksInfo = new JLabel("Marks    :  " + studentMarks);
        marksInfo.setForeground(Color.WHITE);
        marksInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        marksInfo.setBounds(300, 45, 220, 20);
        infoPanel.add(marksInfo);

        JLabel gradeInfo = new JLabel("Grade    :  " + grade);
        gradeInfo.setForeground(Color.WHITE);
        gradeInfo.setFont(new Font("Arial", Font.BOLD, 14));
        gradeInfo.setBounds(300, 75, 220, 20);
        infoPanel.add(gradeInfo);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241));
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 260, 600, 280);
        mainPanel.add(buttonPanel);

        // Panel Title
        JLabel menuLabel = new JLabel("STUDENT PANEL");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuLabel.setForeground(new Color(44, 62, 80));
        menuLabel.setBounds(210, 15, 220, 30);
        buttonPanel.add(menuLabel);

        // View Profile Button
        viewProfileButton = createButton(
            "VIEW MY PROFILE", new Color(52, 152, 219));
        viewProfileButton.setBounds(100, 65, 180, 50);
        viewProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStudentProfile();
            }
        });
        buttonPanel.add(viewProfileButton);

        // Grade Calculator Button
        gradeCalcButton = createButton(
            "GRADE CALCULATOR", new Color(155, 89, 182));
        gradeCalcButton.setBounds(320, 65, 180, 50);
        gradeCalcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GradeCalculator().setVisible(true);
            }
        });
        buttonPanel.add(gradeCalcButton);

        // Logout Button
        logoutButton = createButton("LOGOUT", new Color(231, 76, 60));
        logoutButton.setBounds(210, 150, 180, 50);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);
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

    // Show student profile in a dialog
    private void showStudentProfile() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM students WHERE " +
                        "first_name LIKE CONCAT('%', ?, '%')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String info =
                    "ID        : " + rs.getInt("id") + "\n" +
                    "Name      : " + rs.getString("first_name") +
                               " " + rs.getString("last_name") + "\n" +
                    "Email     : " + rs.getString("email") + "\n" +
                    "Phone     : " + rs.getString("phone") + "\n" +
                    "Course    : " + rs.getString("course") + "\n" +
                    "Marks     : " + rs.getDouble("marks") + "\n" +
                    "Grade     : " + calculateGrade(
                                     rs.getDouble("marks")) + "\n" +
                    "Address   : " + rs.getString("address");

                JOptionPane.showMessageDialog(this,
                    info,
                    "My Profile",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Profile not found!\n" +
                    "Please ask admin to add your details.",
                    "Profile",
                    JOptionPane.WARNING_MESSAGE);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Calculate grade based on marks
    public static String calculateGrade(double marks) {
        if (marks >= 90) return "A+ (Outstanding)";
        else if (marks >= 80) return "A  (Excellent)";
        else if (marks >= 70) return "B  (Good)";
        else if (marks >= 60) return "C  (Average)";
        else if (marks >= 50) return "D  (Pass)";
        else return "F  (Fail)";
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
}