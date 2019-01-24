/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaojpa;

import dao.EmployeeDataAccess;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import model.Employee;

/**
 *
 * @author nikolaos
 */
public class JavaDAOJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmployeeDataAccess employeeDao = new EmployeeDataAccess();
        
//        Employee e1 = new Employee("629523", "Nick", "Bellias", Date.from(LocalDate.of(1985, Month.JANUARY, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        employeeDao.save(e1);
//        Employee e2 = new Employee("639590", "Oscar", "Wilson", Date.from(LocalDate.of(1989, Month.FEBRUARY, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        employeeDao.save(e2);
//        Employee e3 = new Employee("669321", "Jackie", "Cristensen", Date.from(LocalDate.of(1989, Month.OCTOBER, 21).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        employeeDao.save(e3);
//        Employee e4 = new Employee("689782", "Karl", "Biggerstaff", Date.from(LocalDate.of(1982, Month.APRIL, 7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        employeeDao.save(e4);
//        Employee e5 = new Employee("699001", "Jake", "Robinson", Date.from(LocalDate.of(1984, Month.JULY, 18).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        employeeDao.save(e5);
        
        System.out.println("Before any operation, employees are:");
        employeeDao.getAll().forEach(employee -> System.out.println(employee.toString())); // new lambdas notation
        System.out.println("------------------------------------------------");

        Employee emp1 = employeeDao.get("629523").orElseGet(() -> new Employee("non-existing ssn", "non-existing employee first name", "non-existing employee first name", null)); // new lambdas notation
        System.out.println("Updating " + emp1.toString());
        employeeDao.update(emp1, new String[]{"689900", "Lyndon", "Johnson", LocalDate.of(1989, Month.JULY, 4).toString()});

        Employee emp2 = employeeDao.get("699001").orElseGet(() -> new Employee("non-existing ssn", "non-existing employee first name", "non-existing employee first name", null)); // new lambdas notation
        System.out.println("Deleting " + emp2.toString());
        employeeDao.delete(emp2);
        Employee emp = new Employee("629567", "Julie", "Robinson", Date.from(LocalDate.of(1985, Month.JANUARY, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println("Creating and saving new employee " + emp.toString());
        employeeDao.save(emp);

        System.out.println("After previous operations, employees are:");
        employeeDao.getAll().forEach(employee -> System.out.println(employee.toString())); // new lambdas notation
    
        employeeDao.disconnect();
    }
    
}
