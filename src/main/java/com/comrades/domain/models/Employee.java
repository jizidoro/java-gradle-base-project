package com.comrades.domain.models;

import com.comrades.domain.bases.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity {

    private int empNo;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private int sal;
    private int comm;
    private int deptno;

    @Override
    public String getValue() {
        return ename;
    }

    @Override
    public UUID getKey() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Employee [empNo=%d, ename=%s, job=%s, mgr=%d, hiredate=%s, sal=%d, comm=%d, deptno=%d]", empNo, ename, job, mgr, hiredate, sal, comm, deptno);
    }

}
