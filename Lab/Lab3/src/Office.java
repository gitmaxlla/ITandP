import java.util.HashMap;

public class Office {
    private static HashMap<Integer, Employee> employees;

    public Office() {
        employees = new HashMap<>();
    }

    public void hire(Integer id, Employee employee) {
        employees.put(id, employee);
    }

    public Employee fire(Integer id) {
        return employees.remove(id);
    }

    public Employee checkOn(Integer employeeID) {
        return employees.get(employeeID);
    }

    public int personnelCount() {
        return employees.size();
    }

    public boolean isFree() {
        return employees.isEmpty();
    }

    public static void test() {
        Employee e1 = new Employee("Дмитрий", "Программист", 70000.0);
        Employee e2 = new Employee("Алексей", "Тестировщик", 50000.0);
        Employee e3 = new Employee("Полина", "Инженер", 80000.0);

        Office office = new Office();

        office.hire(101, e1);
        office.hire(102, e2);
        office.hire(903, e3);

        System.out.println(office.checkOn(102).getName());
        
        office.fire(101);
        System.out.println(office.personnelCount());
    }
}
