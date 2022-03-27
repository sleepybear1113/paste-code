-- 新建一个数据库 schema 如果不存在
create schema if not exists paste_code;

-- 如果存在那么删除表
drop table if exists paste_code.code;

-- 新建表
create table paste_code.code
(
    id          int auto_increment primary key,
    create_time bigint      null,
    modify_time bigint      null,
    user_id     int         null,
    language    varchar(31) not null comment '代码语言',
    style       varchar(31) not null comment '代码风格',
    code        mediumtext  not null
);