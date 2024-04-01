package IOStream.Exercise.QLNS;

import java.io.*;
import java.util.*;

public class EmployeeManagementSystem {
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }

    // Thêm một nhân viên vào hệ thống
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Ghi dữ liệu xuống file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employees);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu từ file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Lọc nhân sự có tổng lương cao nhất
    public List<Employee> filterHighestTotalSalary() {
        if (employees.isEmpty()) return new ArrayList<>();

        double maxTotalSalary = employees.stream()
                .mapToDouble(Employee::calculateTotalSalary)
                .max().orElse(0);

        return employees.stream()
                .filter(e -> e.calculateTotalSalary() == maxTotalSalary)
                .toList();
    }

    // Lọc tên các nhân sự có sinh nhật trong tháng 12
    public List<Employee> filterBirthdayInDecember() {
        if (employees.isEmpty()) return new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.DECEMBER);

        return employees.stream()
                .filter(e -> {
                    Calendar birthCal = Calendar.getInstance();
                    birthCal.setTime(e.getBirthDate());
                    return birthCal.get(Calendar.MONTH) == Calendar.DECEMBER;
                })
                .toList();
    }

    // Lọc tất cả nhân viên thuộc một phòng nào đó
    public List<Staff> filterByDepartment(String department) {
        if (employees.isEmpty()) return new ArrayList<>();

        return employees.stream()
                .filter(e -> e instanceof Staff)
                .map(e -> (Staff) e)
                .filter(s -> s.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    // Hiển thị nhân sự theo chiều tăng dần của tổng lương
    public List<Employee> sortEmployeesByTotalSalaryAscending() {
        if (employees.isEmpty()) return new ArrayList<>();

        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::calculateTotalSalary))
                .toList();
    }

    // Tìm kiếm nhân viên theo tên hoặc năm sinh
    public List<Employee> searchEmployeeByNameOrBirthYear(String keyword) {
        if (employees.isEmpty()) return new ArrayList<>();

        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(keyword) || getYearFromDate(e.getBirthDate()) == Integer.parseInt(keyword))
                .toList();
    }

    // Phương thức hỗ trợ lấy năm từ ngày
    private int getYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    // Trả về mảng chứa các nhân viên
    public Employee[] getEmployees() {
        return employees.toArray(new Employee[0]);
    }
}
