package IOStream.Exercise.QLNS;

import java.util.Date;

class Manager extends Employee {
    private int numOfEmployees;

    public Manager(String name, Date birthDate, double baseSalary, int numOfEmployees) {
        super(name, birthDate, baseSalary);
        this.numOfEmployees = numOfEmployees;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    @Override
    public String toString() {
        return super.toString() + ", Number of Employees Managed: " + numOfEmployees;
    }
}
