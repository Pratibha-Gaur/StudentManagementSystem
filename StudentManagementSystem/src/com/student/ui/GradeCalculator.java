package com.student.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculator extends JFrame {

    // Components
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JLabel titleLabel;

    // Subject fields
    private JTextField subject1Field;
    private JTextField marks1Field;
    private JTextField subject2Field;
    private JTextField marks2Field;
    private JTextField subject3Field;
    private JTextField marks3Field;
    private JTextField subject4Field;
    private JTextField marks4Field;
    private JTextField subject5Field;
    private JTextField marks5Field;

    // Result labels
    private JLabel totalMarksLabel;
    private JLabel percentageLabel;
    private JLabel gradeLabel;
    private JLabel remarkLabel;

    // Buttons
    private JButton calculateButton;
    private JButton clearButton;
    private JButton backButton;

    public GradeCalculator() {
        setupForm();
    }

    private void setupForm() {

        // Frame settings
        setTitle("Grade Calculator");
        setSize(600, 680);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(155, 89, 182));
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 600, 70);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("GRADE CALCULATOR");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(170, 20, 300, 35);
        headerPanel.add(titleLabel);

        // Column Headers
        JLabel subjectHeader = new JLabel("Subject Name");
        subjectHeader.setFont(new Font("Arial", Font.BOLD, 14));
        subjectHeader.setForeground(new Color(44, 62, 80));
        subjectHeader.setBounds(60, 85, 150, 25);
        mainPanel.add(subjectHeader);

        JLabel marksHeader = new JLabel("Marks (out of 100)");
        marksHeader.setFont(new Font("Arial", Font.BOLD, 14));
        marksHeader.setForeground(new Color(44, 62, 80));
        marksHeader.setBounds(330, 85, 180, 25);
        mainPanel.add(marksHeader);

        // Divider line
        JSeparator separator = new JSeparator();
        separator.setBounds(30, 115, 540, 2);
        mainPanel.add(separator);

        // Subject 1
        subject1Field = createSubjectField("Mathematics", 125);
        marks1Field = createMarksField(125);

        // Subject 2
        subject2Field = createSubjectField("Science", 175);
        marks2Field = createMarksField(175);

        // Subject 3
        subject3Field = createSubjectField("English", 225);
        marks3Field = createMarksField(225);

        // Subject 4
        subject4Field = createSubjectField("Computer Science", 275);
        marks4Field = createMarksField(275);

        // Subject 5
        subject5Field = createSubjectField("Social Studies", 325);
        marks5Field = createMarksField(325);

        // Add all fields to panel
        mainPanel.add(subject1Field);
        mainPanel.add(marks1Field);
        mainPanel.add(subject2Field);
        mainPanel.add(marks2Field);
        mainPanel.add(subject3Field);
        mainPanel.add(marks3Field);
        mainPanel.add(subject4Field);
        mainPanel.add(marks4Field);
        mainPanel.add(subject5Field);
        mainPanel.add(marks5Field);

        // Subject row numbers
        addRowNumber("1.", 125);
        addRowNumber("2.", 175);
        addRowNumber("3.", 225);
        addRowNumber("4.", 275);
        addRowNumber("5.", 325);

        // Buttons
        calculateButton = new JButton("CALCULATE GRADE");
        calculateButton.setBackground(new Color(155, 89, 182));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBounds(60, 380, 180, 40);
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calculateButton.setFocusPainted(false);
        calculateButton.setBorderPainted(false);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });
        mainPanel.add(calculateButton);

        clearButton = new JButton("CLEAR ALL");
        clearButton.setBackground(new Color(230, 126, 34));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBounds(260, 380, 130, 40);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
        mainPanel.add(clearButton);

        backButton = new JButton("CLOSE");
        backButton.setBackground(new Color(127, 140, 141));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(410, 380, 130, 40);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(backButton);

        // Result Panel
        resultPanel = new JPanel();
        resultPanel.setBackground(new Color(44, 62, 80));
        resultPanel.setLayout(null);
        resultPanel.setBounds(30, 440, 540, 180);
        mainPanel.add(resultPanel);

        // Result Title
        JLabel resultTitle = new JLabel("RESULT");
        resultTitle.setForeground(Color.WHITE);
        resultTitle.setFont(new Font("Arial", Font.BOLD, 16));
        resultTitle.setBounds(230, 10, 100, 25);
        resultPanel.add(resultTitle);

        // Total Marks
        totalMarksLabel = new JLabel("Total Marks   :  --");
        totalMarksLabel.setForeground(Color.WHITE);
        totalMarksLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        totalMarksLabel.setBounds(30, 45, 240, 25);
        resultPanel.add(totalMarksLabel);

        // Percentage
        percentageLabel = new JLabel("Percentage    :  --");
        percentageLabel.setForeground(Color.WHITE);
        percentageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        percentageLabel.setBounds(30, 80, 240, 25);
        resultPanel.add(percentageLabel);

        // Grade
        gradeLabel = new JLabel("Grade           :  --");
        gradeLabel.setForeground(new Color(46, 204, 113));
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gradeLabel.setBounds(290, 45, 230, 25);
        resultPanel.add(gradeLabel);

        // Remark
        remarkLabel = new JLabel("Remark        :  --");
        remarkLabel.setForeground(new Color(52, 152, 219));
        remarkLabel.setFont(new Font("Arial", Font.BOLD, 14));
        remarkLabel.setBounds(290, 80, 230, 25);
        resultPanel.add(remarkLabel);

        // Divider in result
        JSeparator resSep = new JSeparator();
        resSep.setBounds(20, 120, 500, 2);
        resSep.setForeground(Color.GRAY);
        resultPanel.add(resSep);

        // Grade Scale info
        JLabel scaleLabel = new JLabel(
            "A+(90-100)  A(80-89)  B(70-79)  C(60-69)  D(50-59)  F(<50)");
        scaleLabel.setForeground(new Color(189, 195, 199));
        scaleLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        scaleLabel.setBounds(30, 135, 490, 20);
        resultPanel.add(scaleLabel);

        // Add main panel
        add(mainPanel);
    }

    // Helper to create subject field
    private JTextField createSubjectField(String placeholder, int y) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBounds(60, y, 240, 32);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getForeground() == Color.GRAY) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
        });
        return field;
    }

    // Helper to create marks field
    private JTextField createMarksField(int y) {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBounds(330, y, 180, 32);
        field.setHorizontalAlignment(JTextField.CENTER);
        return field;
    }

    // Helper to add row numbers
    private void addRowNumber(String num, int y) {
        JLabel label = new JLabel(num);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(155, 89, 182));
        label.setBounds(30, y, 25, 32);
        mainPanel.add(label);
    }

    // Calculate grade
    private void calculateGrade() {
        try {
            double m1 = Double.parseDouble(marks1Field.getText().trim());
            double m2 = Double.parseDouble(marks2Field.getText().trim());
            double m3 = Double.parseDouble(marks3Field.getText().trim());
            double m4 = Double.parseDouble(marks4Field.getText().trim());
            double m5 = Double.parseDouble(marks5Field.getText().trim());

            // Validate marks
            if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 ||
                m3 < 0 || m3 > 100 || m4 < 0 || m4 > 100 ||
                m5 < 0 || m5 > 100) {
                JOptionPane.showMessageDialog(this,
                    "Marks must be between 0 and 100!",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            double total = m1 + m2 + m3 + m4 + m5;
            double percentage = total / 5;
            String grade = StudentDashboard.calculateGrade(percentage);
            String remark = getRemark(percentage);

            // Update result labels
            totalMarksLabel.setText("Total Marks   :  " + 
                total + " / 500");
            percentageLabel.setText("Percentage    :  " + 
                String.format("%.2f", percentage) + "%");
            gradeLabel.setText("Grade           :  " + grade);
            remarkLabel.setText("Remark        :  " + remark);

            // Change grade color based on result
            if (percentage >= 80) {
                gradeLabel.setForeground(new Color(46, 204, 113));
            } else if (percentage >= 60) {
                gradeLabel.setForeground(new Color(230, 126, 34));
            } else {
                gradeLabel.setForeground(new Color(231, 76, 60));
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid marks in all fields!",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    // Get remark based on percentage
    private String getRemark(double percentage) {
        if (percentage >= 90) return "Outstanding!";
        else if (percentage >= 80) return "Excellent!";
        else if (percentage >= 70) return "Good!";
        else if (percentage >= 60) return "Average";
        else if (percentage >= 50) return "Pass";
        else return "Fail - Need Improvement";
    }

    // Clear all fields
    private void clearAll() {
        marks1Field.setText("");
        marks2Field.setText("");
        marks3Field.setText("");
        marks4Field.setText("");
        marks5Field.setText("");
        totalMarksLabel.setText("Total Marks   :  --");
        percentageLabel.setText("Percentage    :  --");
        gradeLabel.setText("Grade           :  --");
        gradeLabel.setForeground(new Color(46, 204, 113));
        remarkLabel.setText("Remark        :  --");
    }
}