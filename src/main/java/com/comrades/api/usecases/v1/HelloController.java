package com.comrades.api.usecases.v1;

import com.comrades.application.TestDto;
import com.comrades.domain.models.Employee;
import com.comrades.persistence.dataaccess.DatasourceConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping("/")
    public List<Employee> Get() {

        var hikariDataSource = DatasourceConfig.connectHikariDataSource();
        final String SQL_QUERY = "select * from emp";
        List<Employee> employees = null;
        try (Connection con = hikariDataSource.getConnection(); PreparedStatement pst = con.prepareStatement(SQL_QUERY); ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<Employee>();
            Employee employee;
            while (rs.next()) {
                employee = new Employee();
                employee.setId(UUID.fromString(rs.getString("id")));
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


    @GetMapping("test")
    @Operation(description = "Get a test model demo", parameters = {
            @Parameter(name = "name", in = ParameterIn.QUERY, required = true, description = "name parameter")
    })
    public Mono<TestDto> getTestDto(final @RequestParam String name,
                                    final ServerWebExchange exchange) {
        TestDto testDto = new TestDto();
        testDto.setName(name);
        testDto.setAge(0);
        testDto.setName("Welcome "+name);
        return Mono.just(testDto);
    }

    @GetMapping("/flux_result")
    public Mono<String> getResult(ServerHttpRequest request) {
        return Mono.defer(() -> Mono.just("Result is ready!"))
                .delaySubscription(Duration.ofMillis(500));
    }

}
