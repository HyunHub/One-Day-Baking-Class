create table customers(
	customer_id varchar2(20),
	customer_pw varchar2(20),
	customer_name varchar2(20),
	customer_email varchar2(30),
	customer_phone varchar2(20),
	customer_address varchar2(100),
	customer_join date,
	customer_withdraw number
);
create table member_board(
	board_num number not null,
	board_id varchar2(50),
	board_subject varchar2(100),
	board_content varchar2(2000),
	board_file varchar2(100),
	board_count number,
	board_date date,
	board_del varchar2(2) default 'n',
	board_category varchar2(10),
	like_count number default 0,
	constraint pk_member_board primary key(board_num)
);

create sequence board_num;

alter table member_board add constraint pk_board_id foreign key(board_id) references customers(customer_id);

create table board_recommend(
	recommend_id varchar2(50) not null,
	recommend_num number not null,
	constraint pk_board_recommend primary key(recommend_id, recommend_num),
	constraint fk_recommend_id foreign key(recommend_id) references customers(customer_id),
	constraint fk_recommend_num foreign key(recommend_num) references member_board(board_num)
);

create table Event(
	event_id varchar2(50) not null,
	event_file varchar2(100) not null,
	event_date date,
	event_del varchar2(2) default 'n',
	event_num number not null primary key,
	constraint fk_event_id foreign key(event_id) references customers(customer_id)
);

create sequence event_num;

create table qna_board(
	qna_num number not null primary key,
	qna_id varchar2(50) not null,
	qna_subject varchar2(100),
	qna_content varchar2(2000),
	qna_category varchar2(10),
	qna_date date default sysdate,
	qna_del varchar2(2) default 'n',
	qna_re_ref number,
	qna_re_lev number,
	qna_re_seq number,
	qna_pw varchar2(10)
);
alter table qna_board add constraint fk_qna_id foreign key(qna_id) references customers(customer_id);

create sequence qna_num;

create table comment_board(
	comment_num number not null primary key,
	comment_board number not null,
	comment_id varchar2(50),
	comment_date date,
	comment_parent number,
	comment_content varchar2(1000) not null,
	comment_del varchar2(2), default 'n',
	constraint fk_comment foreign key(comment_board) references member_board(board_num)
);
create sequence comment_seq;

create table bclass(
	cname varchar2(50),
	teacher varchar2(50),
	image varchar2(255),
	bday varchar2(20),
	constraint pk_bclass primary key(cname, bday)
);

create table book(
	cname varchar2(50),
	people number,
	bday varchar2(20),
	customer_id varchar2(30),
	constraint pk_book primary key(cname, customer_id),
	constraint fk_cname foreign key(cname, bday) references bclass(cname, bday)
);

create table cart (               
   cart_no int not null primary key,               
   customer_id varchar(20) references customers(customer_id),               
   cart_count int,               
   cart_price int,               
   product_id int references products(product_id)               
);

create table products (         
	product_id int not null primary key  ,         
	product_name varchar(30) not null,         
	product_price int not null,         
	product_state int,         
	product_stock int not null,         
	PRODUCT_IMG varchar(100),         
	PRODUCT_content varchar(200)      
);

create table orders (      
	order_id int not null primary key,      
	order_date date not null,      
	order_price int not null,      
	order_state int not null,      
	customer_id varchar(20) references customers(customer_id),      
	deliveryname varchar(20) not null,      
	deliveryphone varchar(20) not null,      
	deliveryaddress varchar(80) not null,      
	account varchar(50) not null,      
	order_count int not null,      
	product_id int references products(product_id)      
);

create table bank (      
   account varchar2(30) primary key,   
   bank varchar2(16) not null,    
   name varchar2(12) not null
);

create sequence product_seq
	increment by 1
	start with 0
	minvalue 0
	maxvalue 100
	nocycle;

create sequence cart_seq      
	increment by 1      
	start with 0      
	minvalue 0      
	maxvalue 100      
	nocycle;

create sequence order_seq      
	increment by 1      
	start with 0      
	minvalue 0      
	maxvalue 100      
	nocycle;

insert into bank values('110-123-123456','신한','김미리');      
insert into bank values('3333-12-123456','카카오뱅크','하수지');      
insert into bank values('045-123456-01-011','기업','김현아');    