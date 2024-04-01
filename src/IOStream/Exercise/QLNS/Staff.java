package IOStream.Exercise.QLNS;

import java.util.Date;

class Staff extends Employee {
    private String department;

    public Staff(String name, Date birthDate, double baseSalary, String department) {
        super(name, birthDate, baseSalary);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return super.toString() + ", Department: " + department;
    }
}
