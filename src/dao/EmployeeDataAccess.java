/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javadaojpa.exceptions.NonexistentEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Employee;

/**
 *
 * @author nikolaos
 */
public class EmployeeDataAccess implements DataAccess<Employee> {

    private EntityManagerFactory emf;
    private EmployeeJpaController ejc;

    public EmployeeDataAccess() {
        this.emf = Persistence.createEntityManagerFactory("JavaDAOJPAPU");
        this.ejc = new EmployeeJpaController(this.emf);
    }

    @Override
    public Optional<Employee> get(String ssn) {
        return Optional.ofNullable(this.ejc.findEmployee(ssn));
    }

    @Override
    public List<Employee> getAll() {
        return this.ejc.findEmployeeEntities();
    }

    @Override
    public void save(Employee employee) {
        try {
            this.ejc.create(employee);
        } catch (Exception ex) {
            System.out.println("Unable to save new employee");
        }
    }

    @Override
    public void update(Employee employee, String[] params) {
        employee.setSsn(Objects.requireNonNull(params[0], "SSN cannot be null"));
        employee.setFname(Objects.requireNonNull(params[1], "First name cannot be null"));
        employee.setLname(Objects.requireNonNull(params[2], "Last name cannot be null"));
        String target = params[3];
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date result = null;
        try {
            result = df.parse(target);
        } catch (ParseException ex) {
            System.out.println("Could not parse date string");
        }
        employee.setDob(Objects.requireNonNull(result, "Date of birth cannot be null"));
        try {
            this.ejc.edit(employee);
        } catch (Exception ex) {
            System.out.println("Could not update employee");
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            this.ejc.destroy(employee.getSsn());
        } catch (NonexistentEntityException ex) {
            System.out.println("Could not delete employee");
        }
    }

    public void disconnect(){
        this.emf.close();
    }
}
