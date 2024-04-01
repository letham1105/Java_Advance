package IOStream.Exercise.QLNS;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class EmployeeManagementGUI extends JFrame {
    private EmployeeManagementSystem system;
    private JTextArea displayArea;

    public EmployeeManagementGUI() {
        system = new EmployeeManagementSystem();

        setTitle("Employee Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Employees");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadEmployees();
            }
        });
        panel.add(loadButton, BorderLayout.NORTH);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        panel.add(addButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void loadEmployees() {
        system.loadFromFile("employees.dat");
        displayArea.setText("Employees loaded:\n");
        for (Employee employee : system.getEmployees()) {
            displayArea.append(employee.toString() + "\n");
        }
    }

    private void addEmployee() {
        JFrame frame = new JFrame("Add Employee");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel birthDateLabel = new JLabel("Birth Date (YYYY-MM-DD):");
        JTextField birthDateField = new JTextField();
        JLabel baseSalaryLabel = new JLabel("Base Salary:");
        JTextField baseSalaryField = new JTextField();
        JLabel departmentLabel = new JLabel("Department:");
        JTextField departmentField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(birthDateLabel);
        panel.add(birthDateField);
        panel.add(baseSalaryLabel);
        panel.add(baseSalaryField);
        panel.add(departmentLabel);
        panel.add(departmentField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String[] dateParts = birthDateField.getText().split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]) - 1;
                int day = Integer.parseInt(dateParts[2]);
                Date birthDate = new Date(year - 1900, month, day);
                double baseSalary = Double.parseDouble(baseSalaryField.getText());
                String department = departmentField.getText();

                Employee employee = new Staff(name, birthDate, baseSalary, department);
                system.addEmployee(employee);
                displayArea.append("Employee added: " + employee.toString() + "\n");

                frame.dispose();
            }
        });

        panel.add(addButton);
        frame.add(panel);
        frame.setVisible(true);
    }
    JButton filterSalaryButton = new JButton("Filter Highest Total Salary");
//        filterSalaryButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            // Xử lý sự kiện cho chức năng Filter Highest Total Salary
//        }
//    });
//        panel.add(filterSalaryButton);

    // Thêm nút chức năng Filter Birthday in December
    JButton filterDecemberButton = new JButton("Filter Birthday in December");
//        filterDecemberButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            // Xử lý sự kiện cho chức năng Filter Birthday in December
//        }
//    });
//        panel.add(filterDecemberButton);

    // Thêm nút chức năng Filter By Department
    JButton filterDepartmentButton = new JButton("Filter By Department");
//        filterDepartmentButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            // Xử lý sự kiện cho chức năng Filter By Department
//        }
//    });
//        panel.add(filterDepartmentButton);

    // Thêm nút chức năng Sort Employees By Total Salary Ascending
    JButton sortButton = new JButton("Sort Employees By Total Salary Ascending");
//        sortButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            // Xử lý sự kiện cho chức năng Sort Employees By Total Salary Ascending
//        }
//    });
//        panel.add(sortButton);

    // Thêm nút chức năng Search Employee By Name or Birth Year
    JButton searchButton = new JButton("Search Employee By Name or Birth Year");
//        searchButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            // Xử lý sự kiện cho chức năng Search Employee By Name or Birth Year
//        }
//    });
//        panel.add(searchButton);
//}
    public static void main(String[] args) {
        new EmployeeManagementGUI();
    }
}
