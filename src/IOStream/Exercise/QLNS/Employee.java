package IOStream.Exercise.QLNS;

import java.io.Serializable;
import java.util.Date;

abstract class Employee implements Serializable {
    private String name;
    private Date birthDate;
    private double baseSalary;

    public Employee(String name, Date birthDate, double baseSalary) {
        this.name = name;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Tính tổng lương (có thể được override bởi các lớp con)
    public double calculateTotalSalary() {
        return baseSalary;
    }

    // In thông tin nhân viên
    @Override
    public String toString() {
        return "Name: " + name + ", Birth Date: " + birthDate + ", Base Salary: " + baseSalary;
    }
}
