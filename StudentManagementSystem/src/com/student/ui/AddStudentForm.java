package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Frontend: Add new student form with client-side validation before
 * saving to the database.
 */
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
        setSize(580, 660);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(UIStyle.GRAY_BG);
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(UIStyle.SUCCESS_GREEN);
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 580, 75);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("ADD NEW STUDENT");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(UIStyle.FONT_TITLE);
        titleLabel.setBounds(150, 22, 280, 30);
        headerPanel.add(titleLabel);

        // First Name
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(UIStyle.FONT_LABEL);
        firstNameLabel.setForeground(UIStyle.PRIMARY_DARK);
        firstNameLabel.setBounds(50, 95, 120, 25);
        mainPanel.add(firstNameLabel);

        firstNameField = new JTextField();
        UIStyle.styleField(firstNameField);
        firstNameField.setBounds(200, 95, 300, 30);
        mainPanel.add(firstNameField);

        // Last Name
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(UIStyle.FONT_LABEL);
        lastNameLabel.setForeground(UIStyle.PRIMARY_DARK);
        lastNameLabel.setBounds(50, 145, 120, 25);
        mainPanel.add(lastNameLabel);

        lastNameField = new JTextField();
        UIStyle.styleField(lastNameField);
        lastNameField.setBounds(200, 145, 300, 30);
        mainPanel.add(lastNameField);

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(UIStyle.FONT_LABEL);
        emailLabel.setForeground(UIStyle.PRIMARY_DARK);
        emailLabel.setBounds(50, 195, 120, 25);
        mainPanel.add(emailLabel);

        emailField = new JTextField();
        UIStyle.styleField(emailField);
        emailField.setBounds(200, 195, 300, 30);
        mainPanel.add(emailField);

        // Phone
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(UIStyle.FONT_LABEL);
        phoneLabel.setForeground(UIStyle.PRIMARY_DARK);
        phoneLabel.setBounds(50, 245, 120, 25);
        mainPanel.add(phoneLabel);

        phoneField = new JTextField();
        UIStyle.styleField(phoneField);
        phoneField.setBounds(200, 245, 300, 30);
        mainPanel.add(phoneField);

        // Course
        courseLabel = new JLabel("Course:");
        courseLabel.setFont(UIStyle.FONT_LABEL);
        courseLabel.setForeground(UIStyle.PRIMARY_DARK);
        courseLabel.setBounds(50, 295, 120, 25);
        mainPanel.add(courseLabel);

        courseField = new JTextField();
        UIStyle.styleField(courseField);
        courseField.setBounds(200, 295, 300, 30);
        mainPanel.add(courseField);

        // Marks
        marksLabel = new JLabel("Marks:");
        marksLabel.setFont(UIStyle.FONT_LABEL);
        marksLabel.setForeground(UIStyle.PRIMARY_DARK);
        marksLabel.setBounds(50, 345, 120, 25);
        mainPanel.add(marksLabel);

        marksField = new JTextField();
        UIStyle.styleField(marksField);
        marksField.setBounds(200, 345, 300, 30);
        mainPanel.add(marksField);

        // Address
        addressLabel = new JLabel("Address:");
        addressLabel.setFont(UIStyle.FONT_LABEL);
        addressLabel.setForeground(UIStyle.PRIMARY_DARK);
        addressLabel.setBounds(50, 395, 120, 25);
        mainPanel.add(addressLabel);

        addressField = new JTextField();
        UIStyle.styleField(addressField);
        addressField.setBounds(200, 395, 300, 30);
        mainPanel.add(addressField);

        // Save Button
        saveButton = UIStyle.createButton("SAVE STUDENT", UIStyle.SUCCESS_GREEN);
        saveButton.setBounds(50, 455, 160, 42);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });
        mainPanel.add(saveButton);

        // Clear Button
        clearButton = UIStyle.createButton("CLEAR", UIStyle.WARNING_ORANGE);
        clearButton.setBounds(230, 455, 110, 42);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        mainPanel.add(clearButton);

        // Back Button
        backButton = UIStyle.createButton("BACK", new Color(127, 140, 141));
        backButton.setBounds(360, 455, 110, 42);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(backButton);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setFont(UIStyle.FONT_MESSAGE);
        messageLabel.setBounds(50, 515, 480, 25);
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

        // Validation: required fields
        if (firstName.isEmpty() || lastName.isEmpty() ||
            email.isEmpty() || course.isEmpty()) {
            messageLabel.setText("  Please fill all required fields!");
            messageLabel.setForeground(UIStyle.ERROR_RED);
            return;
        }

        // Validation: email format
        if (!isValidEmail(email)) {
            messageLabel.setText("  Please enter a valid email address!");
            messageLabel.setForeground(UIStyle.ERROR_RED);
            return;
        }

        // Validation: phone format (digits only, 10 digits if provided)
        if (!phone.isEmpty() && !isValidPhone(phone)) {
            messageLabel.setText("  Phone must be a 10-digit number!");
            messageLabel.setForeground(UIStyle.ERROR_RED);
            return;
        }

        // Validate marks
        double marks = 0;
        try {
            marks = Double.parseDouble(marksText);
            if (marks < 0 || marks > 100) {
                messageLabel.setText("  Marks must be between 0 and 100!");
                messageLabel.setForeground(UIStyle.ERROR_RED);
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("  Please enter valid marks!");
            messageLabel.setForeground(UIStyle.ERROR_RED);
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
                messageLabel.setForeground(UIStyle.SUCCESS_GREEN);
                JOptionPane.showMessageDialog(this,
                    "Student added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Error: " + e.getMessage());
            messageLabel.setForeground(UIStyle.ERROR_RED);
        }
    }

    // Frontend email format validation
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Frontend phone validation: exactly 10 digits
    private boolean isValidPhone(String phone) {
        return phone.matches("^\\d{10}$");
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
