package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Frontend: Login screen. Authenticates users against the database and
 * routes them to the Admin or Student dashboard.
 */
public class LoginForm extends JFrame {

    // Components
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JCheckBox showPasswordBox;
    private JButton loginButton;
    private JButton clearButton;
    private JLabel messageLabel;

    public LoginForm() {
        setupForm();
    }

    private void setupForm() {

        // Frame settings
        setTitle("Student Management System - Login");
        setSize(450, 410);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(UIStyle.PRIMARY_DARK);
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(450, 410));

        // Title Label
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(UIStyle.FONT_TITLE);
        titleLabel.setBounds(40, 25, 370, 30);
        mainPanel.add(titleLabel);

        // Username Label
        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(UIStyle.FONT_LABEL);
        usernameLabel.setBounds(80, 110, 100, 25);
        mainPanel.add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        UIStyle.styleField(usernameField);
        usernameField.setBounds(190, 110, 180, 30);
        mainPanel.add(usernameField);

        // Password Label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(UIStyle.FONT_LABEL);
        passwordLabel.setBounds(80, 160, 100, 25);
        mainPanel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        UIStyle.styleField(passwordField);
        passwordField.setBounds(190, 160, 180, 30);
        mainPanel.add(passwordField);

        // Show password checkbox
        showPasswordBox = new JCheckBox("Show password");
        showPasswordBox.setBackground(UIStyle.PRIMARY_DARK);
        showPasswordBox.setForeground(Color.WHITE);
        showPasswordBox.setFont(new Font("Arial", Font.PLAIN, 12));
        showPasswordBox.setFocusPainted(false);
        showPasswordBox.setBounds(190, 200, 150, 25);
        showPasswordBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u25CF');
                }
            }
        });
        mainPanel.add(showPasswordBox);

        // Login Button
        loginButton = UIStyle.createButton("LOGIN", UIStyle.PRIMARY_BLUE);
        loginButton.setBounds(120, 245, 100, 38);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButtonClicked();
            }
        });
        mainPanel.add(loginButton);

        // Clear Button
        clearButton = UIStyle.createButton("CLEAR", UIStyle.ERROR_RED);
        clearButton.setBounds(230, 245, 100, 38);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearButtonClicked();
            }
        });
        mainPanel.add(clearButton);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setForeground(new Color(255, 200, 200));
        messageLabel.setFont(UIStyle.FONT_MESSAGE);
        messageLabel.setBounds(60, 295, 340, 25);
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
            messageLabel.setForeground(new Color(255, 200, 200));
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
                messageLabel.setForeground(new Color(200, 255, 200));
                messageLabel.setText("  Login Successful! Welcome " + username);

                if (role.equals("ADMIN")) {
                    new AdminDashboard().setVisible(true);
                } else {
                    new StudentDashboard(username).setVisible(true);
                }
                this.dispose();
            } else {
                messageLabel.setForeground(new Color(255, 200, 200));
                messageLabel.setText("  Invalid username or password!");
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Database error: " + e.getMessage());
            messageLabel.setForeground(new Color(255, 200, 200));
        }
    }

    private void clearButtonClicked() {
        usernameField.setText("");
        passwordField.setText("");
        showPasswordBox.setSelected(false);
        passwordField.setEchoChar('\u25CF');
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
