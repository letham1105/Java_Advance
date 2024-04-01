package IOStream.Exercise.QLNS;

import java.util.Date;

class Director extends Employee {
    private double positionFactor;

    public Director(String name,
                    Date birthDate, double baseSalary, double positionFactor) {
        super(name, birthDate, baseSalary);
        this.positionFactor = positionFactor;
    }

    public double getPositionFactor() {
        return positionFactor;
    }

    @Override
    public double calculateTotalSalary() {
        return getBaseSalary() * positionFactor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Position Factor: " + positionFactor + ", Total Salary: " + calculateTotalSalary();
    }
}
