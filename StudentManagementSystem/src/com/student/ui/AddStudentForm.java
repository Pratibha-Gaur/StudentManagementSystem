package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddStudentForm extends JFrame {

    // Components
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel courseLabel;
    private JTextField courseField;
    private JLabel marksLabel;
    private JTextField marksField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JButton saveButton;
    private JButton clearButton;
    private JButton backButton;
    private JLabel messageLabel;

    public AddStudentForm() {
        setupForm();
    }

    private void setupForm() {

        // Frame settings
        setTitle("Add New Student");
        setSize(550, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(46, 204, 113));
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 550, 70);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("ADD NEW STUDENT");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 20, 280, 30);
        headerPanel.add(titleLabel);

        // First Name
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        firstNameLabel.setBounds(50, 90, 120, 25);
        mainPanel.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 13));
        firstNameField.setBounds(200, 90, 280, 30);
        mainPanel.add(firstNameField);

        // Last Name
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lastNameLabel.setBounds(50, 140, 120, 25);
        mainPanel.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 13));
        lastNameField.setBounds(200, 140, 280, 30);
        mainPanel.add(lastNameField);

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 13));
        emailLabel.setBounds(50, 190, 120, 25);
        mainPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 13));
        emailField.setBounds(200, 190, 280, 30);
        mainPanel.add(emailField);

        // Phone
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 13));
        phoneLabel.setBounds(50, 240, 120, 25);
        mainPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Arial", Font.PLAIN, 13));
        phoneField.setBounds(200, 240, 280, 30);
        mainPanel.add(phoneField);

        // Course
        courseLabel = new JLabel("Course:");
        courseLabel.setFont(new Font("Arial", Font.BOLD, 13));
        courseLabel.setBounds(50, 290, 120, 25);
        mainPanel.add(courseLabel);

        courseField = new JTextField();
        courseField.setFont(new Font("Arial", Font.PLAIN, 13));
        courseField.setBounds(200, 290, 280, 30);
        mainPanel.add(courseField);

        // Marks
        marksLabel = new JLabel("Marks:");
        marksLabel.setFont(new Font("Arial", Font.BOLD, 13));
        marksLabel.setBounds(50, 340, 120, 25);
        mainPanel.add(marksLabel);

        marksField = new JTextField();
        marksField.setFont(new Font("Arial", Font.PLAIN, 13));
        marksField.setBounds(200, 340, 280, 30);
        mainPanel.add(marksField);

        // Address
        addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressLabel.setBounds(50, 390, 120, 25);
        mainPanel.add(addressLabel);

        addressField = new JTextField();
        addressField.setFont(new Font("Arial", Font.PLAIN, 13));
        addressField.setBounds(200, 390, 280, 30);
        mainPanel.add(addressField);

        // Save Button
        saveButton = new JButton("SAVE STUDENT");
        saveButton.setBackground(new Color(46, 204, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 13));
        saveButton.setBounds(50, 450, 150, 40);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.setFocusPainted(false);
        saveButton.setBorderPainted(false);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });
        mainPanel.add(saveButton);

        // Clear Button
        clearButton = new JButton("CLEAR");
        clearButton.setBackground(new Color(230, 126, 34));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setBounds(220, 450, 100, 40);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        mainPanel.add(clearButton);

        // Back Button
        backButton = new JButton("BACK");
        backButton.setBackground(new Color(127, 140, 141));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setBounds(340, 450, 100, 40);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(backButton);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel.setBounds(50, 505, 450, 25);
        mainPanel.add(messageLabel);

        // Add main panel to frame
        add(mainPanel);
    }

    private void saveStudent() {
        // Get values from fields
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String course = courseField.getText().trim();
        String marksText = marksField.getText().trim();
        String address = addressField.getText().trim();

        // Validation
        if (firstName.isEmpty() || lastName.isEmpty() || 
            email.isEmpty() || course.isEmpty()) {
            messageLabel.setText("  Please fill all required fields!");
            messageLabel.setForeground(new Color(231, 76, 60));
            return;
        }

        // Validate marks
        double marks = 0;
        try {
            marks = Double.parseDouble(marksText);
            if (marks < 0 || marks > 100) {
                messageLabel.setText("  Marks must be between 0 and 100!");
                messageLabel.setForeground(new Color(231, 76, 60));
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("  Please enter valid marks!");
            messageLabel.setForeground(new Color(231, 76, 60));
            return;
        }

        // Save to database
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO students (first_name, last_name, email, phone, course, marks, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, course);
            ps.setDouble(6, marks);
            ps.setString(7, address);

            int result = ps.executeUpdate();

            if (result > 0) {
                messageLabel.setText("  Student saved successfully!");
                messageLabel.setForeground(new Color(46, 204, 113));
                JOptionPane.showMessageDialog(this,
                    "Student added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Error: " + e.getMessage());
            messageLabel.setForeground(new Color(231, 76, 60));
        }
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        courseField.setText("");
        marksField.setText("");
        addressField.setText("");
        messageLabel.setText("");
        firstNameField.requestFocus();
    }
}
