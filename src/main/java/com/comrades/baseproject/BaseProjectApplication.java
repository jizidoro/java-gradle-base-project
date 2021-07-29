package com.comrades.baseproject;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BaseProjectApplication {

    public static void main(String[] args) {
        fetchData();
    }

    public static List<Employee> fetchData() {
        final String SQL_QUERY = "select * from emp";
        List<Employee> employees = null;
        try (Connection con = DataSource.getConnection(); PreparedStatement pst = con.prepareStatement(SQL_QUERY); ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<Employee>();
            Employee employee;
            while (rs.next()) {
                employee = new Employee();
                employee.setEmpNo(rs.getInt("empno"));
                employee.setEname(rs.getString("ename"));
                employee.setJob(rs.getString("job"));
                employee.setMgr(rs.getInt("mgr"));
                employee.setHiredate(rs.getDate("hiredate"));
                employee.setSal(rs.getInt("sal"));
                employee.setComm(rs.getInt("comm"));
                employee.setDeptno(rs.getInt("deptno"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}


