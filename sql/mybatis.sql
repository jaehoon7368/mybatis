--====================================
-- mybatis
--====================================
--web계정사용

create table student(
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date,
    deleted_at date,
    constraint pk_student_no primary key(no)
);

create sequence seq_student_no;

insert into
    student(no,name,tel)
values(
    seq_student_no.nextval,'홍길동','01022223333'
);

insert into
    student(no,name,tel)
values(
    seq_student_no.nextval,'신사임당','01055553333'
);

insert into
    student(no,name,tel)
values(
    seq_student_no.nextval,'이순신','01077773333'
);

select * from student;


-- 수정

update
    student
set
    tel='01033334444',
    updated_at = sysdate
where
    no = 1;
    
    
-- 삭제
update
    student
set
    deleted_at = sysdate
where
    no = 3;
    
commit;

-- 일반회원조회
select * from student where deleted_at is null;
select * from student where deleted_at is null and tel like '%33%';


grant all on sh.employee to web;
grant all on sh.department to web;
grant all on sh.job to web;
grant all on sh.sal_grade to web;

-- sh계정 employee | department | job | sal_grade
select * from sh.employee;
select * from sh.department;
select * from sh.job;
select * from sh.grade;

-- 관리자가 create synonym권환을 web에게 부여
grant create synonym to web;

--동의어 synonym 객체지정
-- 특정사용자,특정테이블에 대한 별칭
create synonym emp for sh.employee;
create synonym dept for sh.department;
create synonym job for sh.job;
create synonym sal_grade for sh.sal_grade;

select * from emp;

-- 사번/사원명/이메일/전화번호 검색 && 성별 검색
select
    *
from
(
    select
        e.*,
        decode(substr(emp_no,8,1),'1','남','3','남','여')gender
    from
        emp e
)
where
    quit_yn = 'N'
    and
    emp_name like '%이%'
    and
    gender = '남';


