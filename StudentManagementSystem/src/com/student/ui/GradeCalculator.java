package com.student.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Frontend: 5-subject grade calculator with auto percentage and grade.
 */
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
        setSize(640, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(UIStyle.GRAY_BG);
        mainPanel.setLayout(null);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(UIStyle.PURPLE);
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 640, 75);
        mainPanel.add(headerPanel);

        // Title Label
        titleLabel = new JLabel("GRADE CALCULATOR");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(UIStyle.FONT_TITLE);
        titleLabel.setBounds(170, 22, 300, 35);
        headerPanel.add(titleLabel);

        // Column Headers
        JLabel subjectHeader = new JLabel("Subject Name");
        subjectHeader.setFont(UIStyle.FONT_LABEL);
        subjectHeader.setForeground(UIStyle.PRIMARY_DARK);
        subjectHeader.setBounds(60, 90, 150, 25);
        mainPanel.add(subjectHeader);

        JLabel marksHeader = new JLabel("Marks (out of 100)");
        marksHeader.setFont(UIStyle.FONT_LABEL);
        marksHeader.setForeground(UIStyle.PRIMARY_DARK);
        marksHeader.setBounds(350, 90, 180, 25);
        mainPanel.add(marksHeader);

        // Divider line
        JSeparator separator = new JSeparator();
        separator.setBounds(30, 120, 580, 2);
        mainPanel.add(separator);

        // Subject 1
        subject1Field = createSubjectField("Mathematics", 130);
        marks1Field = createMarksField(130);

        // Subject 2
        subject2Field = createSubjectField("Science", 180);
        marks2Field = createMarksField(180);

        // Subject 3
        subject3Field = createSubjectField("English", 230);
        marks3Field = createMarksField(230);

        // Subject 4
        subject4Field = createSubjectField("Computer Science", 280);
        marks4Field = createMarksField(280);

        // Subject 5
        subject5Field = createSubjectField("Social Studies", 330);
        marks5Field = createMarksField(330);

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
        addRowNumber("1.", 130);
        addRowNumber("2.", 180);
        addRowNumber("3.", 230);
        addRowNumber("4.", 280);
        addRowNumber("5.", 330);

        // Buttons
        calculateButton = UIStyle.createButton("CALCULATE GRADE", UIStyle.PURPLE);
        calculateButton.setBounds(60, 390, 180, 42);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });
        mainPanel.add(calculateButton);

        clearButton = UIStyle.createButton("CLEAR ALL", UIStyle.WARNING_ORANGE);
        clearButton.setBounds(260, 390, 130, 42);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
        mainPanel.add(clearButton);

        backButton = UIStyle.createButton("CLOSE", new Color(127, 140, 141));
        backButton.setBounds(410, 390, 130, 42);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(backButton);

        // Result Panel
        resultPanel = new JPanel();
        resultPanel.setBackground(UIStyle.PRIMARY_DARK);
        resultPanel.setLayout(null);
        resultPanel.setBounds(30, 450, 580, 200);
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
        totalMarksLabel.setFont(UIStyle.FONT_FIELD);
        totalMarksLabel.setBounds(30, 45, 240, 25);
        resultPanel.add(totalMarksLabel);

        // Percentage
        percentageLabel = new JLabel("Percentage    :  --");
        percentageLabel.setForeground(Color.WHITE);
        percentageLabel.setFont(UIStyle.FONT_FIELD);
        percentageLabel.setBounds(30, 80, 240, 25);
        resultPanel.add(percentageLabel);

        // Grade
        gradeLabel = new JLabel("Grade           :  --");
        gradeLabel.setForeground(UIStyle.SUCCESS_GREEN);
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gradeLabel.setBounds(320, 45, 230, 25);
        resultPanel.add(gradeLabel);

        // Remark
        remarkLabel = new JLabel("Remark        :  --");
        remarkLabel.setForeground(UIStyle.PRIMARY_BLUE);
        remarkLabel.setFont(new Font("Arial", Font.BOLD, 14));
        remarkLabel.setBounds(320, 80, 230, 25);
        resultPanel.add(remarkLabel);

        // Divider in result
        JSeparator resSep = new JSeparator();
        resSep.setBounds(20, 125, 540, 2);
        resSep.setForeground(Color.GRAY);
        resultPanel.add(resSep);

        // Grade Scale info
        JLabel scaleLabel = new JLabel(
            "A+(90-100)  A(80-89)  B(70-79)  C(60-69)  D(50-59)  F(<50)");
        scaleLabel.setForeground(new Color(189, 195, 199));
        scaleLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        scaleLabel.setBounds(30, 140, 490, 20);
        resultPanel.add(scaleLabel);

        // Add main panel
        add(mainPanel);
    }

    // Helper to create subject field
    private JTextField createSubjectField(String placeholder, int y) {
        JTextField field = new JTextField(placeholder);
        field.setFont(UIStyle.FONT_FIELD);
        field.setBounds(60, y, 260, 32);
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
        field.setFont(UIStyle.FONT_FIELD);
        field.setBounds(350, y, 200, 32);
        field.setHorizontalAlignment(JTextField.CENTER);
        return field;
    }

    // Helper to add row numbers
    private void addRowNumber(String num, int y) {
        JLabel label = new JLabel(num);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(UIStyle.PURPLE);
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
                gradeLabel.setForeground(UIStyle.SUCCESS_GREEN);
            } else if (percentage >= 60) {
                gradeLabel.setForeground(UIStyle.WARNING_ORANGE);
            } else {
                gradeLabel.setForeground(UIStyle.ERROR_RED);
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
        gradeLabel.setForeground(UIStyle.SUCCESS_GREEN);
        remarkLabel.setText("Remark        :  --");
    }
}
