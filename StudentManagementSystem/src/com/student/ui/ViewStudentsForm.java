package com.student.ui;

import com.student.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

/**
 * Frontend: View, search, update and delete student records in a table.
 */
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
        setSize(950, 640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(UIStyle.GRAY_BG);
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(UIStyle.PRIMARY_BLUE);
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 950, 75);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("ALL STUDENTS");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(UIStyle.FONT_TITLE);
        titleLabel.setBounds(370, 22, 250, 35);
        headerPanel.add(titleLabel);

        // Search Panel
        searchPanel = new JPanel();
        searchPanel.setBackground(UIStyle.PANEL_GRAY);
        searchPanel.setLayout(null);
        searchPanel.setBounds(0, 75, 950, 60);
        mainPanel.add(searchPanel);

        // Search Label
        searchLabel = new JLabel("Search:");
        searchLabel.setFont(UIStyle.FONT_LABEL);
        searchLabel.setForeground(UIStyle.PRIMARY_DARK);
        searchLabel.setBounds(20, 18, 70, 25);
        searchPanel.add(searchLabel);

        // Search Field
        searchField = new JTextField();
        UIStyle.styleField(searchField);
        searchField.setBounds(100, 15, 300, 30);
        searchPanel.add(searchField);

        // Search Button
        searchButton = UIStyle.createButton("SEARCH", UIStyle.PRIMARY_BLUE);
        searchButton.setBounds(420, 15, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        searchPanel.add(searchButton);

        // Show All Button
        showAllButton = UIStyle.createButton("SHOW ALL", UIStyle.SUCCESS_GREEN);
        showAllButton.setBounds(540, 15, 110, 30);
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
        studentTable.setFont(UIStyle.FONT_FIELD);
        studentTable.setRowHeight(26);
        studentTable.getTableHeader().setFont(UIStyle.FONT_LABEL);
        studentTable.getTableHeader().setBackground(UIStyle.PRIMARY_DARK);
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionBackground(UIStyle.PRIMARY_BLUE);
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setGridColor(UIStyle.PANEL_GRAY);
        studentTable.setIntercellSpacing(new Dimension(0, 0));
        UIStyle.applyZebraStriping(studentTable);

        scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(10, 145, 925, 370);
        mainPanel.add(scrollPane);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(UIStyle.GRAY_BG);
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 530, 950, 60);
        mainPanel.add(buttonPanel);

        // Update Button
        updateButton = UIStyle.createButton("UPDATE STUDENT", UIStyle.WARNING_ORANGE);
        updateButton.setBounds(200, 10, 170, 40);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        buttonPanel.add(updateButton);

        // Delete Button
        deleteButton = UIStyle.createButton("DELETE STUDENT", UIStyle.ERROR_RED);
        deleteButton.setBounds(390, 10, 170, 40);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        buttonPanel.add(deleteButton);

        // Back Button
        backButton = UIStyle.createButton("BACK", new Color(127, 140, 141));
        backButton.setBounds(580, 10, 110, 40);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(backButton);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setFont(UIStyle.FONT_MESSAGE);
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
            messageLabel.setForeground(UIStyle.PRIMARY_DARK);
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Error: " + e.getMessage());
            messageLabel.setForeground(UIStyle.ERROR_RED);
        }
    }

    // Search student by name or email
    private void searchStudent() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            messageLabel.setText("  Please enter a search keyword!");
            messageLabel.setForeground(UIStyle.ERROR_RED);
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
                messageLabel.setForeground(UIStyle.ERROR_RED);
            } else {
                messageLabel.setText("  Found " + tableModel.getRowCount() + " student(s)!");
                messageLabel.setForeground(UIStyle.SUCCESS_GREEN);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
            messageLabel.setText("  Error: " + e.getMessage());
            messageLabel.setForeground(UIStyle.ERROR_RED);
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
        dialog.setSize(470, 480);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(UIStyle.GRAY_BG);

        // Fields in dialog
        JTextField fnField = createDialogField(dialog, "First Name:", firstName, 25);
        JTextField lnField = createDialogField(dialog, "Last Name:", lastName, 75);
        JTextField emField = createDialogField(dialog, "Email:", email, 125);
        JTextField phField = createDialogField(dialog, "Phone:", phone, 175);
        JTextField coField = createDialogField(dialog, "Course:", course, 225);
        JTextField mkField = createDialogField(dialog, "Marks:", String.valueOf(marks), 275);
        JTextField adField = createDialogField(dialog, "Address:", address, 325);

        // Save button in dialog
        JButton saveBtn = UIStyle.createButton("SAVE CHANGES", UIStyle.SUCCESS_GREEN);
        saveBtn.setBounds(100, 385, 150, 38);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newEmail = emField.getText().trim();
                String newPhone = phField.getText().trim();
                String newMarks = mkField.getText().trim();

                // Frontend validation
                if (!isValidEmail(newEmail)) {
                    JOptionPane.showMessageDialog(dialog,
                        "Please enter a valid email address!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!newPhone.isEmpty() && !isValidPhone(newPhone)) {
                    JOptionPane.showMessageDialog(dialog,
                        "Phone must be a 10-digit number!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    Double.parseDouble(newMarks);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog,
                        "Please enter valid marks!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Connection conn = DatabaseConnection.getConnection();
                    String sql = "UPDATE students SET first_name=?, last_name=?, email=?, phone=?, course=?, marks=?, address=? WHERE id=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, fnField.getText());
                    ps.setString(2, lnField.getText());
                    ps.setString(3, newEmail);
                    ps.setString(4, newPhone);
                    ps.setString(5, coField.getText());
                    ps.setDouble(6, Double.parseDouble(newMarks));
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
        JButton cancelBtn = UIStyle.createButton("CANCEL", UIStyle.ERROR_RED);
        cancelBtn.setBounds(270, 385, 100, 38);
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
        label.setFont(UIStyle.FONT_LABEL);
        label.setForeground(UIStyle.PRIMARY_DARK);
        label.setBounds(20, y, 100, 25);
        dialog.add(label);

        JTextField field = new JTextField(value);
        UIStyle.styleField(field);
        field.setBounds(130, y, 300, 28);
        dialog.add(field);

        return field;
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
                messageLabel.setForeground(UIStyle.ERROR_RED);
            }
        }
    }
}
