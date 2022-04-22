package com.example.demo.controller

import com.example.demo.models.Employee
import com.example.demo.service.EmployeeService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/Employee")
class EmployeeController(val employeeService : EmployeeService) {
    @GetMapping
    fun getEmployees():Flux<Employee>{
        return employeeService.getEmployees()
    }

    @PostMapping
    fun addEmployee(@RequestBody employee: Employee): Mono<Employee> {
        return Mono.just(employeeService.addEmployee(employee))
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id:String): String{
        return employeeService.deleteEmployee(id)
    }

    @GetMapping("/max")
    fun maxSalariedEmployeeDetail(): Mono<Employee> {
        return employeeService.maxSalariedEmployeeDetail()
    }

    @GetMapping("/min")
    fun minSalariedEmployeeDetail():Mono<Employee>{
        return employeeService.minSalariedEmployeeDetail()
    }

    @PostMapping("/updateSalary/{id}")
    fun updateSalaryOfEmployee(@PathVariable id: String,@RequestParam salary:Int):Mono<Employee>{
        return employeeService.updateSalaryOfEmployee(id,salary)
    }


}
