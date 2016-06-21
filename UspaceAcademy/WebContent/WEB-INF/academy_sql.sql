

-------------------------------------------------------------------ok
-- 코드테이블
drop table code_table;
create table code_table(
	code_id varchar2(1) primary key,
	code_name varchar2(30) not null,
	code_type varchar2(30) not null
);

-- 관리자
drop table administrator;
create table administrator(
	administrator_id varchar2(50) primary key,
	administrator_password varchar2(50) not null
);

-- 강사
drop table teacher cascade constraint;
create table teacher(
	teacher_id varchar2(50) primary key,
	teacher_password varchar2(50) not null,
	teacher_name varchar2(100) not null,
	teacher_email varchar2(100) not null,
	teacher_phone_no varchar2(13) not null,
	teacher_adress varchar2(100) not null,
	teacher_subject varchar2(50) not null,
	teacher_salary number not null
);

-- 학생
DROP table student cascade constraint;
CREATE TABLE student(
	student_id varchar2(50) primary key, --학생아이디 primary key
	student_password varchar2(50) not null, --비밀번호
	student_name varchar2(100) not null, --이름
	student_email varchar2(100) not null,  --이메일
	student_phone_no varchar2(13) not null, --핸드폰번호
	student_address varchar2(100) not null --주소
);

insert into STUDENT values('id-1','1111','이영주','iidd1@kosta.com','010-1111-1111','경기도 용인시 처인구 고림동');
insert into STUDENT values('id-2','2222','김수진','iidd2@kosta.com','010-1111-2222','경기도 수원시 장안구 파장동');
insert into STUDENT values('id-3','3333','황경희','iidd3@kosta.com','010-1111-3333','경기도 안산시 상록구 본오동');
insert into STUDENT values('id-4','4444','김세은','iidd4@kosta.com','010-1111-4444','서울시 서대문구 은평동');

-- FAQ, 공지사항
drop table basic_board;
create table basic_board(
	basic_no number primary key,
	basic_writer varchar2(50) not null,
	basic_title varchar2(100) not null,
	basic_content clob not null,	
	basic_date varchar2(10) not null,
	basic_hit number not null,
	basic_type varchar2(30) not null
);

create sequence basic_board_seq 
nocache;












-- 수강 후기
select * from REVIEW_BOARD;
drop table review_board;
create table review_board(
	review_no number primary key,
	review_writer varchar2(50) not null,
	lecture_title varchar2(100) not null,
	lecture_subject varchar2(50) not null,
	review_title varchar2(100) not null,
	review_content clob not null,
	review_date varchar2(10) not null,
	review_hit number not null
);

insert into review_board values (publisher_no_seq.nextval, '이영주', '국어고등3', '국어', '국어 수업 너무 재미있어요','국어고등3 국어수업을 들었습니다. 이것은 수강후기입니다. 수강후기','20160620',0);
insert into review_board values (publisher_no_seq.nextval, '김수진', '영어고등3', '영어', '영어 수업 너무 재미있어요','영어고등3 영어수업을 들었습니다. 이것은 영어수강후기입니다. 수강후기','20160620',0);

--수강후기 페이징 처리   ------------------------------물어봐
select review_no, review_writer, lecture_title, lecture_subject, review_title, review_content, review_date, review_hit
from(
	select ceil(rownum/8) page, review_no, review_writer, lecture_title, lecture_subject, review_title, review_content, review_date, review_hit
	from(
			select review_no, review_writer, lecture_title, lecture_subject, review_title, review_content, review_date, review_hit
			from review_board order by review_no desc
	)
)where page=2














-- 강의
DROP table lecture cascade constraint;
CREATE TABLE lecture(
	lecture_no number primary key, --강의번호 primary key
	lecture_title varchar2(100) not null, --강의제목
	lecture_description varchar2(1000) not null, --강의 설명
	lecture_start_time number not null, --강의 시작 시간
	lecture_end_time number not null, --강의 끝 시간
	lecture_day varchar2(10) not null, --강의 요일
	lecture_start_date varchar2(10) not null, --강의 시작일
	lecture_end_date varchar2(10) not null, --강의 종료일
	lecture_price number not null, --강의 수강료
	lecture_total_student number not null, --강의 수강가능 인원
	lecture_current_student number not null, --강의 현재수강 인원
	lecture_subject varchar2(50) not null, --강의 종류
	teacher_id2 varchar2(50), --강사아이디 foreign key
	constraint fk_lecture_teacher foreign key (teacher_id2) references teacher(teacher_id)
);

-- 1:1문의, 질문게시판
drop table advanced_board cascade constraint;
create table advanced_board(
	advanced_no number primary key,
	advanced_secret number not null,
	advanced_title varchar2(100) not null,
	advanced_content clob not null,
	advanced_date varchar2(10) not null,
	advanced_hit number not null,
	advanced_id varchar2(50) not null,
	lecture_no2 number null,
	constraint fk_advanced_lecture foreign key(lecture_no2) references lecture(lecture_no)
);

-- 코멘트
DROP table comment_table cascade constraint;
CREATE TABLE comment_table(
	comment_no number primary key,
	comment_content clob not null,
	comment_date varchar2(10) not null,
	comment_writer varchar2(50) not null, 
	advanced_no2 number not null,
	constraint fk_comment_advanced foreign key (advanced_no2) references advanced_board(advanced_no)
);

-- 과제
drop table assignment_board;
create table assignment_board(
	assignment_no number primary key,
	assignment_secret number not null,
	assignment_title varchar2(100) not null,
	assignment_content clob not null,
	assingment_date varchar2(10) not null,
	assingment_hit number not null,
	assingment_password number null,
	assingment_re_family number not null,
	assingment_re_step number not null,
	assingment_re_level number not null,
	assingment_id varchar2(50) not null,
	assingment_deadline varchar2(10) not null,
	lecture_no number not null,
	constraint fk_assignment_lecture foreign key (lecture_no) references lecture(lecture_no)
);



-- 학생 강의 조인
DROP table student_lecture_join;
CREATE TABLE student_lecture_join(
	student_id3 varchar2(50),
	lecture_no3 number,
	primary key (student_id3, lecture_no3), --학생아이디 primary key, --강의번호 primary key
	zzim_option varchar2(1) not null, --찜여부
	constraint fk_student_lecture_join foreign key (student_id3) references student(student_id),
	constraint fk_lecture_student_join foreign key (lecture_no3) references lecture(lecture_no)
);

-- 출석부
DROP table attendance;
CREATE TABLE attendance( 
	attandance_state varchar2(6) not null, --출석상태
	lecture_day number not null, --강의일차
	student_id2 varchar2(50) not null, --학생아이디 foreign key
	lecture_no2 number not null, --강의번호 foreign key
	constraint fk_attendance foreign key (student_id2, lecture_no2) references student_lecture_join(student_id3, lecture_no3)
);








-- code_table insert하는 구문
insert into CODE_TABLE values('1', '공지사항', 'basic_board');
insert into CODE_TABLE values('2', 'FAQ', 'basic_baord');

-------------------------------------------------------------------ok
