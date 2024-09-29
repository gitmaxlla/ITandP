public class Employee {
    private String name, position;
    private double salary;

    public void setName(String value) {
        name = value;
    }

    public void setPosition(String value) {
        position = value;
    }

    public void setSalary(double value) {
        salary = value;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Employee(String name, String position, double salary) {
        setName(name);
        setPosition(position);
        setSalary(salary);
    }
}
