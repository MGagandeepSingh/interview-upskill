package com.collaboration.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;

public class VariousCollectorsExample {

    // Check out various static methods in Collectors class and their usage.

    // We will use this POJO as example
    public static class Employee {
        public enum Department {HR, TESTING, DEVELOPMENT}

        String name;
        int id;
        Department department;

        public Employee(String name, int id, Department department) {
            this.name = name;
            this.id = id;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static List<Employee> employees = Arrays.asList(
            new Employee("A", 1, Employee.Department.DEVELOPMENT),
            new Employee("B", 2, Employee.Department.TESTING),
            new Employee("C", 3, Employee.Department.HR),
            new Employee("D", 4, Employee.Department.TESTING),
            new Employee("E", 5, Employee.Department.DEVELOPMENT),
            new Employee("F", 6, Employee.Department.DEVELOPMENT),
            new Employee("G", 7, Employee.Department.HR)
    );

    // groupingBy: This has 3 flavours
    // 1. groupingBy(Function classifier): Map<{groupedByType}, List<Type>>
    //      classifier: This is the type that we will use to group up the data.

    // 2. groupingBy(Function classifier, Collector downstream): Map<{groupedByType}, {downstream}>
    //      downstream: This can be used when we want to return a different type than a List.

    // 3. groupingBy(Function classifier, Supplier mapFactory, Collector downstream)
    // We use these methods for grouping objects by some property and storing results in a Map

    // 1. Group employees department wise. (Uses first flavour)
    public static void groupDepartmentWise() {
        Map<Employee.Department, List<Employee>> grouped = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        grouped.forEach((d, e) -> System.out.println(d + " : " + e));
    }

    // 2. Group employees department wise and return only names. (Uses second flavour)
    public static void groupDepartmentWiseOnlyNames() {
        Map<Employee.Department, String> grouped = employees
                .stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.joining(", "))));
        grouped.forEach((d, e) -> System.out.println(d + " : " + e));
    }

    // 3. Similar to above but this time get only id.
    public static void groupDepartmentWiseOnlyId() {
//        Map<Employee.Department, Integer> grouped = employees
//                .stream()
//                .collect(Collectors.groupingBy
//                        (
//                                Employee::getDepartment,
//                                Employee::getId
//                        )
//                );
//        grouped.forEach((d, e) -> System.out.println(d + " : " + e));
    }

    public static void main(String[] args) {
        //groupDepartmentWise();
        groupDepartmentWiseOnlyNames();
    }
}
