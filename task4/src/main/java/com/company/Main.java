package com.company;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final String csvFilePath = "foreign_names.csv";
        try {
            List<Employee> list = parseEmployees(csvFilePath);
            System.out.println(list);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public static List<Employee> parseEmployees(String filepath) throws Exception {
        ArrayList<Employee> employees = new ArrayList<>();
        try (InputStream in = Employee.class.getClassLoader().getResourceAsStream(filepath)) {
            HashMap<String, Subdivision> subdivisionMap = new HashMap<>();

            CSVReader reader = in == null ? null : new CSVReader(new InputStreamReader(in), ';');
            if (reader == null) {
                throw new FileNotFoundException(filepath);
            }

            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                String[] date = nextLine[3].split("\\.");
                Calendar birthday = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));

                String subdivisionName = nextLine[4];
                Subdivision employeeSubdivision;
                if (subdivisionMap.containsKey(subdivisionName)) {
                    employeeSubdivision = subdivisionMap.get(subdivisionName);
                } else {
                    employeeSubdivision= new Subdivision(subdivisionName);
                    subdivisionMap.put(subdivisionName, employeeSubdivision);
                }

                Employee employee = new Employee(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2], Integer.parseInt(nextLine[5]), birthday, employeeSubdivision);
                employees.add(employee);
            }
        }
        return employees;
    }
}
