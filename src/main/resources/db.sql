drop table if exists emp;
drop table if exists dept;
drop table if exists jorge;

create table dept
(
    deptno numeric,
    dname  varchar(14),
    loc    varchar(13),
    constraint pk_dept primary key (deptno)
);

create table emp
(
    id       UUID,
    empno    numeric,
    ename    varchar(10),
    job      varchar(9),
    mgr      numeric,
    hiredate date,
    sal      numeric,
    comm     numeric,
    deptno   numeric,
    constraint pk_emp primary key (empno),
    constraint fk_deptno foreign key (deptno) references dept (deptno)
);

create table jorge
(
    id IDENTITY(1,1),
    iban    varchar(80)    not null,
    balance DECIMAL(18, 2) not null,
    constraint pk_jorge primary key (id)
);

insert into dept
values (10, 'ACCOUNTING', 'NEW YORK');
insert into dept
values (20, 'RESEARCH', 'DALLAS');
insert into dept
values (30, 'SALES', 'CHICAGO');
insert into dept
values (40, 'OPERATIONS', 'BOSTON');

insert into emp
values ('7105df94-f258-11eb-9a03-0242ac130003',
        7839, 'KING', 'PRESIDENT', null,
        to_date('17-11-1981', 'dd-mm-yyyy'),
        7698, null, 10);
insert into emp
values ('7105e228-f258-11eb-9a03-0242ac130003',
        7698, 'BLAKE', 'MANAGER', 7839,
        to_date('1-5-1981', 'dd-mm-yyyy'),
        7782, null, 20);
insert into emp
values ('7105e318-f258-11eb-9a03-0242ac130003',
        7782, 'CLARK', 'MANAGER', 7839,
        to_date('9-6-1981', 'dd-mm-yyyy'),
        7566, null, 30);
insert into emp
values ('7105e3e0-f258-11eb-9a03-0242ac130003',
        7566, 'JONES', 'MANAGER', 7839,
        to_date('2-4-1981', 'dd-mm-yyyy'),
        7839, null, 40);



insert into jorge(iban, balance)
values ('BR430120980198201982', 100.00);
insert into jorge(iban, balance)
values ('BR430120998729871000', 250.00);

commit;