package jpmorgan.functionalprogrammingpractice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FPBoundHashMapUsageOne {

    public static void main(String ar[]) {

        Employee eOne = new Employee(1, "A", "D1", 10000);
        Employee eTwo = new Employee(2, "B", "D2", 20000);
        Employee eThree = new Employee(3, "C", "D2", 20000);
        Employee eFour = new Employee(4, "D", "D3", 180000);
        Employee eFive = new Employee(5, "E", "D3", 190000);

        List<String> skillOne = Arrays.asList("Java", "Spring", "MySQL", "Redis");
        List<String> skillTwo = Arrays.asList("Java", "Spring", "MySQL", "Redis", "NewRelic");
        List<String> skillThree = Arrays.asList("Java", "Spring", "MySQL", "Redis");

        eOne.setSkills(skillOne);
        eTwo.setSkills(skillOne);
        eThree.setSkills(skillTwo);
        eFour.setSkills(skillThree);
        eFive.setSkills(skillThree);


        List<Employee> employees = Arrays.asList(eOne, eTwo, eThree, eFour, eFive);

        Map<String, List<Employee>> intermittentResult = Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getDepartment() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment));

        Map<String, List<Employee>> topPaidEmployeesByDepartment =
                intermittentResult.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> {
                                    List<Employee> departmentEmployees = entry.getValue();

                                    int maxSalary = departmentEmployees.stream()
                                            .mapToInt(Employee::getSalary)
                                            .max()
                                            .orElseThrow(IllegalStateException::new);

                                    return departmentEmployees.stream()
                                            .filter(e -> e.getSalary() == maxSalary)
                                            .collect(Collectors.toList());
                                }
                        ));

        Map<String, List<String>> result =
                topPaidEmployeesByDepartment.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().stream()
                                        .flatMap(e -> Optional.ofNullable(e.getSkills())
                                                .orElseGet(Collections::emptyList)
                                                .stream())
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toMap(
                                                skill -> skill.toLowerCase(Locale.ROOT),
                                                Function.identity(),
                                                (first, ignoredDuplicate) -> first,
                                                LinkedHashMap::new
                                        ))
                                        .values()
                                        .stream()
                                        .sorted(String.CASE_INSENSITIVE_ORDER)
                                        .collect(Collectors.toList())
                        ));

    }


    static class Employee {
        private int id;
        private String name;
        private String department;
        private int salary;
        private List<String> skills;


        public Employee(int id, String name, String department, int salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
        }
    }
}
