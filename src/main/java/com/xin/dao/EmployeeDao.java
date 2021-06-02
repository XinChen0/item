package com.xin.dao;

import com.xin.pojo.Department;
import com.xin.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//管理员Dao
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //管理员有所属的部门
//    @Autowired
//    private static DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();//创建一个管理员表
//        employees.put(1001, new Employee(1001, "张三", "123123@qq.com", 1, new Department(101, "管理员")));
        employees.put(1001, new Employee(1001, "张三", "123123@qq.com", 1, DepartmentDao.getDepartmentById(101)));
        employees.put(1002, new Employee(1002, "李四", "112323@qq.com", 0, DepartmentDao.getDepartmentById(101)));
        employees.put(1003, new Employee(1003, "王五", "131223@qq.com", 0, DepartmentDao.getDepartmentById(101)));
        employees.put(1004, new Employee(1004, "赵六", "123231@qq.com", 0, DepartmentDao.getDepartmentById(101)));
        employees.put(1005, new Employee(1005, "张三", "122313@qq.com", 1, DepartmentDao.getDepartmentById(101)));
    }

    //主键自增
    private static Integer initId = 1006;

    //增加一个管理员
    public void addEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(DepartmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询全部管理员信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过id查询管理员
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //通过id删除管理员
    public void deleteById(Integer id) {
        employees.remove(id);
    }
}
