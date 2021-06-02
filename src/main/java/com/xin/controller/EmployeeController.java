package com.xin.controller;

import com.xin.dao.DepartmentDao;
import com.xin.dao.EmployeeDao;
import com.xin.pojo.Department;
import com.xin.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model) {
        //查出所有用户类别信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    //添加管理员
    public String addEmp(Employee employee) {
        System.out.println("addEmp````````" + employee);
        //添加的操作
        employeeDao.addEmployee(employee);//调用底层业务方法添加管理员信息
        // forward
        return "redirect:/emps";
    }

    //进入管理员的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        //查找原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    //修改管理员数据
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeDao.addEmployee(employee);//覆盖:添加就是修改
        return "redirect:/emps";
    }


    //删除管理员
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") int id) {
        employeeDao.deleteById(id);
        return "redirect:/emps";
    }
}
