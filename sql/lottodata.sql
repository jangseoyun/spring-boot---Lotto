# 로또 테이블

# 시퀀스 생성
create database Lotto;

# 데이터베이스 선택
use Lotto;

# 데이터베이스 목록 확인(현재 계정이 접근 가능한)
show databases;

# 테이블 확인
show tables;

#---- users ----------------------------------------
# users table 확인
select *
from users;

# 테이블 삭제
drop table users;
# 테이블 생성
create table users (
                       user_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       user_id varchar(20) not null unique ,
                       user_pw varchar(50) not null ,
                       user_email varchar(50) not null ,
                       user_phone varchar(50) ,
                       join_data timestamp not null default now()
);

# insert
insert into users
values(null,'test','12341234','hjj92000@gmail.com','010-1111-2222',now());

# update
        update users
  set user_id = 'testtest'
  where user_no = 1;

# delete
delete from users
where user_no = 1;

commit ;
#---- winningInfo ----------------------------------------
# winningInfo 테이블 확인
select *
from winningInfo;

# 테이블 삭제
drop table winningInfo;

# 테이블 생성
create table winningInfo(
                            user_no int not null primary key ,
                            win_cycle_no int not null ,
                            win_ranking int not null ,
                            win_prize bigint not null,
                            win_data timestamp not null default now(),
                            win_sixnum varchar(100) not null ,
                            foreign key (user_no) references users (user_no)
);

# insert
insert into winningInfo
values(1,5110,5,5000,now(),'1,2,3,4,5,6');

# update
        update winningInfo
  set win_prize = '6000'
  where user_no = 1;

# delete
delete from winningInfo
where user_no = 1;

commit ;

#---- subscription ----------------------------------------
# subscription 테이블 확인
select *
from subscription;

# 테이블 삭제
drop table subscription;

# 테이블 생성
create table subscription (
                              subscription_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              user_no int not null ,
                              licence boolean not null ,
                              subscription_date timestamp not null default now(),
                              foreign key (user_no) references users (user_no)
);

# insert
insert into subscription
values (null, 1,true,now());

# update
        update subscription
  set licence = false
  where user_no = 1;

# delete
delete from subscription
where user_no = 1;

commit ;

#---- ballStorage ----------------------------------------
# ballStorage 테이블 확인
select *
from ballStorage;

# 테이블 삭제
drop table ballStorage;

# 테이블 생성
create table ballStorage(
                            Storage_no int not null auto_increment primary key ,
                            user_no int not null ,
                            six_ball varchar(100) not null ,
                            storage_cycle_num int not null ,
                            storage_date timestamp not null default now()
);

# insert
insert into ballStorage
values (null, 1,'11,2,3,4,5,6',5110,now());

# update
        update ballStorage
  set six_ball = '2,3,4,5,7,8'
  where user_no = 1;

# delete
delete from ballStorage
where user_no = 1;

commit ;

