package com.example.demo.Repository

import com.example.demo.models.Employee
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import reactor.core.publisher.Mono

@Repository
data class EmployeeRepository(private var employees: ArrayList<Employee>) {
    init {
        employees = arrayListOf(
            Employee("DAP123", "Daisy Pascal", "SDE1", 50000, "BLR"),
            Employee("ABH045", "Abby Haize", "SDE2", 75000, "CPH"),
            Employee("JKL123", "Jake Lesnar", "SDE3", 100000, "CPH"),
            Employee("TEM078", "Tera Miller", "SDE1", 50000, "CPH"),
            Employee("CAC025", "Cadence Chandler", "SDE3", 100000, "CPH")
        )
    }

    fun getEmployees(): ArrayList<Employee> {
        return employees
    }

    fun addEmployee(employee: Employee): Employee {
        employees.add(employee)
        return employee
    }

    fun deleteEmployee(empId: String): String {
        return employees.filter { emp -> emp.empId.equals(empId) }
            .map {
                employees.remove(it)
                "sucessfully deleted "
            }
            .ifEmpty { listOf("Required Employee data is already deleted / invalid Employee data for deletion") }
            .first()


    }

    fun getMaxSalaryAmount(): Int {
        return employees.maxOf { it.salary }
    }

    fun updateSalaryOfEmployee(id: String, salary: Int): Mono<Employee> {
        return Mono.just(employees)
            .map { emps->var employeeUpdated: Employee? =emps.find { employee -> employee.empId.equals(id) }
                if (employeeUpdated != null) {
                    employeeUpdated.salary=salary
                }
                if (employeeUpdated != null) {
                    employees.set(employees.indexOf(emps.find { employee -> employee.empId.equals(id)}),employeeUpdated)
                }
                employeeUpdated
            }
       /* return Mono.just(employees)
            .filter{employee-> employee.find { employee -> employee.empId.equals(id) }!=null}
            .map { employee->employees.set(id:Int,Employee(employee.)) }*/
    }


}
