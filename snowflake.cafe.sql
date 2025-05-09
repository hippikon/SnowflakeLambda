
CREATE DATABASE jobsearch2025;

create table app_data(id smallint not null auto_increment primary key,
name varchar(200), company varchar(200),
job_url varchar(200), details varchar(3000), website varchar(100),
applied boolean not null default true, created datetime not null default now(),
updated datetime not null default now());

create table app_data_fulltime(id smallint not null auto_increment primary key,
name varchar(200), company varchar(200),
job_url varchar(200), details varchar(3000), website varchar(100),
applied boolean not null default true, created datetime not null default now(),
updated datetime not null default now());

create table app_data_india(id smallint not null auto_increment primary key,
name varchar(200), phone varchar(25), email varchar(100), company varchar(200), details varchar(3000),
created datetime not null default now(), updated datetime not null default now());

alter table app_data MODIFY COLUMN job_url varchar(500);

create table app_detail(id smallint not null auto_increment primary key,
job_id smallint not null, tag_name varchar(200), tag_value varchar(200), created datetime not null default now(),
updated datetime not null default now(),
FOREIGN KEY (job_id)
        REFERENCES app_data(id)
        ON DELETE CASCADE
);

create table app_detail_fulltime(id smallint not null auto_increment primary key,
job_id smallint not null, tag_name varchar(200), tag_value varchar(200), created datetime not null default now(),
updated datetime not null default now(),
FOREIGN KEY (job_id)
        REFERENCES app_data_fulltime(id)
        ON DELETE CASCADE
);

//  drop table app_data;
//  drop table app_detail;

GRANT ALL PRIVILEGES ON jobsearch2025.* TO 'madhuri'@'10.79.2.129' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON jobsearch2025.* TO 'madhuri'@'%' WITH GRANT OPTION;

select * from app_data;
select * from app_detail;

update app_detail set tag_value = 'remote' where tag_name='location'and job_id = 11;

// update app_data set applied=true where id = 4;