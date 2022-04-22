package com.example.demo.service

import com.example.demo.Repository.EmployeeRepository
import com.example.demo.models.Employee
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {
    fun getEmployees(): Flux<Employee> {
        return Flux.fromIterable(employeeRepository.getEmployees())
    }

    fun addEmployee(employee: Employee): Employee {
        return employeeRepository.addEmployee(employee)
    }

    fun deleteEmployee(empId: String): String {
        return employeeRepository.deleteEmployee(empId)
    }

    fun maxSalariedEmployeeDetail(): Mono<Employee> {
        return Mono.just(employeeRepository.getEmployees().stream().max(Comparator.comparing<Employee,Int>(Employee::salary))
            .get())

    }

    fun minSalariedEmployeeDetail(): Mono<Employee> {
        return Mono.just(employeeRepository.getEmployees().stream().min(Comparator.comparing<Employee,Int>(Employee::salary))
            .get())
    }

    fun updateSalaryOfEmployee(id: String, salary: Int): Mono<Employee> {
        return employeeRepository.updateSalaryOfEmployee(id,salary)
    }

}