package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    // Components
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;
    private JLabel messageLabel;

    public LoginForm() {
        setupForm();
    }

    private void setupForm() {

        // Frame settings
        setTitle("Student Management System - Login");
        setSize(450, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(44, 62, 80));
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(450, 380));

        // Title Label
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(40, 30, 370, 30);
        mainPanel.add(titleLabel);

        // Username Label
        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(80, 110, 100, 25);
        mainPanel.add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBounds(190, 110, 180, 30);
        mainPanel.add(usernameField);

        // Password Label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(80, 160, 100, 25);
        mainPanel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(190, 160, 180, 30);
        mainPanel.add(passwordField);

        // Login Button
        loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(120, 220, 100, 35);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButtonClicked();
            }
        });
        mainPanel.add(loginButton);

        // Clear Button
        clearButton = new JButton("CLEAR");
        clearButton.setBackground(new Color(231, 76, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBounds(240, 220, 100, 35);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearButtonClicked();
            }
        });
        mainPanel.add(clearButton);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setForeground(new Color(231, 76, 60));
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel.setBounds(60, 275, 340, 25);
        mainPanel.add(messageLabel);

        // Add panel to frame
        add(mainPanel);
    }

    private void loginButtonClicked() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("  Please enter username and password!");
            messageLabel.setForeground(new Color(231, 76, 60));
            return;
        }

        // Check login in database
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                messageLabel.setForeground(new Color(46, 204, 113));
                messageLabel.setText("  Login Successful! Welcome " + username);

                if (role.equals("ADMIN")) {
                    new AdminDashboard().setVisible(true);
                } else {
                    new StudentDashboard(username).setVisible(true);
                }
                this.dispose();
            } else {
                messageLabel.setForeground(new Color(231, 76, 60));
                messageLabel.setText("  Invalid username or password!");
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Database error: " + e.getMessage());
        }
    }

    private void clearButtonClicked() {
        usernameField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
        usernameField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}