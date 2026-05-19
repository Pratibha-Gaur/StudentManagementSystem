package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ViewStudentsForm extends JFrame {

    // Components
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel searchPanel;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton showAllButton;
    private JTable studentTable;
    private JScrollPane scrollPane;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JLabel messageLabel;
    private DefaultTableModel tableModel;

    public ViewStudentsForm() {
        setupForm();
        loadAllStudents();
    }

    private void setupForm() {

        // Frame settings
        setTitle("View All Students");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 900, 70);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("ALL STUDENTS");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(350, 20, 250, 35);
        headerPanel.add(titleLabel);

        // Search Panel
        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(189, 195, 199));
        searchPanel.setLayout(null);
        searchPanel.setBounds(0, 70, 900, 60);
        mainPanel.add(searchPanel);

        // Search Label
        searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchLabel.setBounds(20, 18, 70, 25);
        searchPanel.add(searchLabel);

        // Search Field
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBounds(100, 15, 300, 30);
        searchPanel.add(searchField);

        // Search Button
        searchButton = new JButton("SEARCH");
        searchButton.setBackground(new Color(52, 152, 219));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 13));
        searchButton.setBounds(420, 15, 100, 30);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        searchPanel.add(searchButton);

        // Show All Button
        showAllButton = new JButton("SHOW ALL");
        showAllButton.setBackground(new Color(46, 204, 113));
        showAllButton.setForeground(Color.WHITE);
        showAllButton.setFont(new Font("Arial", Font.BOLD, 13));
        showAllButton.setBounds(540, 15, 110, 30);
        showAllButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showAllButton.setFocusPainted(false);
        showAllButton.setBorderPainted(false);
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAllStudents();
            }
        });
        searchPanel.add(showAllButton);

        // Table
        String[] columns = {"ID", "First Name", "Last Name", 
                           "Email", "Phone", "Course", "Marks", "Address"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 13));
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(new Color(44, 62, 80));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionBackground(new Color(52, 152, 219));
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setGridColor(new Color(189, 195, 199));

        scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(10, 140, 875, 350);
        mainPanel.add(scrollPane);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241));
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 500, 900, 60);
        mainPanel.add(buttonPanel);

        // Update Button
        updateButton = new JButton("UPDATE STUDENT");
        updateButton.setBackground(new Color(230, 126, 34));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 13));
        updateButton.setBounds(200, 10, 160, 40);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setFocusPainted(false);
        updateButton.setBorderPainted(false);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        buttonPanel.add(updateButton);

        // Delete Button
        deleteButton = new JButton("DELETE STUDENT");
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 13));
        deleteButton.setBounds(380, 10, 160, 40);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        buttonPanel.add(deleteButton);

        // Back Button
        backButton = new JButton("BACK");
        backButton.setBackground(new Color(127, 140, 141));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setBounds(560, 10, 100, 40);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(backButton);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel.setBounds(20, 495, 400, 25);
        mainPanel.add(messageLabel);

        // Add main panel to frame
        add(mainPanel);
    }

    // Load all students from database
    private void loadAllStudents() {
        tableModel.setRowCount(0);
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM students";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getDouble("marks"),
                    rs.getString("address")
                };
                tableModel.addRow(row);
            }
            messageLabel.setText("  Total students: " + tableModel.getRowCount());
            messageLabel.setForeground(new Color(44, 62, 80));
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Error: " + e.getMessage());
            messageLabel.setForeground(new Color(231, 76, 60));
        }
    }

    // Search student by name or email
    private void searchStudent() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            messageLabel.setText("  Please enter a search keyword!");
            messageLabel.setForeground(new Color(231, 76, 60));
            return;
        }

        tableModel.setRowCount(0);
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR course LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getDouble("marks"),
                    rs.getString("address")
                };
                tableModel.addRow(row);
            }

            if (tableModel.getRowCount() == 0) {
                messageLabel.setText("  No students found!");
                messageLabel.setForeground(new Color(231, 76, 60));
            } else {
                messageLabel.setText("  Found " + tableModel.getRowCount() + " student(s)!");
                messageLabel.setForeground(new Color(46, 204, 113));
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Error: " + e.getMessage());
            messageLabel.setForeground(new Color(231, 76, 60));
        }
    }

    // Update selected student
    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a student to update!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String firstName = (String) tableModel.getValueAt(selectedRow, 1);
        String lastName = (String) tableModel.getValueAt(selectedRow, 2);
        String email = (String) tableModel.getValueAt(selectedRow, 3);
        String phone = (String) tableModel.getValueAt(selectedRow, 4);
        String course = (String) tableModel.getValueAt(selectedRow, 5);
        double marks = (double) tableModel.getValueAt(selectedRow, 6);
        String address = (String) tableModel.getValueAt(selectedRow, 7);

        // Create update dialog
        JDialog dialog = new JDialog(this, "Update Student", true);
        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(new Color(236, 240, 241));

        // Fields in dialog
        JTextField fnField = createDialogField(dialog, "First Name:", firstName, 20);
        JTextField lnField = createDialogField(dialog, "Last Name:", lastName, 70);
        JTextField emField = createDialogField(dialog, "Email:", email, 120);
        JTextField phField = createDialogField(dialog, "Phone:", phone, 170);
        JTextField coField = createDialogField(dialog, "Course:", course, 220);
        JTextField mkField = createDialogField(dialog, "Marks:", String.valueOf(marks), 270);
        JTextField adField = createDialogField(dialog, "Address:", address, 320);

        // Save button in dialog
        JButton saveBtn = new JButton("SAVE CHANGES");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Arial", Font.BOLD, 13));
        saveBtn.setBounds(100, 375, 150, 35);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorderPainted(false);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DatabaseConnection.getConnection();
                    String sql = "UPDATE students SET first_name=?, last_name=?, email=?, phone=?, course=?, marks=?, address=? WHERE id=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, fnField.getText());
                    ps.setString(2, lnField.getText());
                    ps.setString(3, emField.getText());
                    ps.setString(4, phField.getText());
                    ps.setString(5, coField.getText());
                    ps.setDouble(6, Double.parseDouble(mkField.getText()));
                    ps.setString(7, adField.getText());
                    ps.setInt(8, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(dialog,
                        "Student updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    DatabaseConnection.closeConnection(conn);
                    dialog.dispose();
                    loadAllStudents();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog,
                        "Error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(saveBtn);

        // Cancel button in dialog
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBackground(new Color(231, 76, 60));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cancelBtn.setBounds(270, 375, 100, 35);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.add(cancelBtn);

        dialog.setVisible(true);
    }

    // Helper to create fields in dialog
    private JTextField createDialogField(JDialog dialog, 
            String labelText, String value, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setBounds(20, y, 100, 25);
        dialog.add(label);

        JTextField field = new JTextField(value);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBounds(130, y, 280, 28);
        dialog.add(field);

        return field;
    }

    // Delete selected student
    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a student to delete!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = tableModel.getValueAt(selectedRow, 1) + " " + 
                      tableModel.getValueAt(selectedRow, 2);

        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete student: " + name + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "DELETE FROM students WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this,
                    "Student deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                DatabaseConnection.closeConnection(conn);
                loadAllStudents();
            } catch (Exception e) {
                messageLabel.setText("  Error: " + e.getMessage());
                messageLabel.setForeground(new Color(231, 76, 60));
            }
        }
    }
}