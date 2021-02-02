drop table if exists user;

create table if not exists user (
    id varchar2(50) primary key,
    name varchar2(50) not null
)