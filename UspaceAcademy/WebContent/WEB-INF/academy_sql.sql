--생성할때 순서대로 하기.

-- 1.코드테이블
drop table code_table;
create table code_table(			 	-- 코드 테이블 
	code_id varchar2(5) primary key, 	-- 코드 
	code_name varchar2(30) not null, 	-- 코드 이름
	code_type varchar2(30) not null  	-- 코드 종류
);

-- 2.관리자ㅇ
drop table administrator;
create table administrator(							-- 관리자 테이블
	administrator_id varchar2(15) primary key,		-- 관리자 아이디
	administrator_password varchar2(50) not null	-- 관리자 비밀번호
);
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into administrator values('admin', '1234');
------------------------------------------------------------------------------------------------------------------------------------------------------
-- 3.강사ㅇ
drop table teacher cascade constraint;
create table teacher(							-- 강사회원 테이블
	teacher_id varchar2(50) primary key,		-- 강사회원 아이디
	teacher_password varchar2(50) not null,		-- 강사회원 비밀번호
	teacher_name varchar2(100) not null,		-- 강사회원 이름
	teacher_email varchar2(100) not null,		-- 강사회원 이메일
	teacher_phone_no varchar2(13) not null,		-- 강사회원 핸드폰번호
	teacher_address varchar2(100) not null,		-- 강사회원 주소
	teacher_subject varchar2(50) not null,		-- 강사회원 강의 과목
	teacher_salary number not null				-- 강사회원 월급
);
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into teacher values('teacher1', '1234', '김국어', 'as@naver.com', '010-6266-5153', '서울시 서초구', '국어', 1000000);
insert into teacher values('teacher2', '1234', '김영어', 'as@naver.com', '010-6266-5153', '서울시 서초구', '영어', 1000000);
insert into teacher values('teacher3', '1234', '김수학', 'as@naver.com', '010-6266-5153', '서울시 서초구', '수학', 1000000);
select * from teacher;
------------------------------------------------------------------------------------------------------------------------------------------------------
-- 4.학생
DROP table student cascade constraint;
CREATE TABLE student(							-- 학생회원 테이블
	student_id varchar2(50) primary key, 		-- 학생회원 아이디 
	student_password varchar2(50) not null, 	-- 학생회원 비밀번호
	student_name varchar2(100) not null, 		-- 학생회원 이름
	student_email varchar2(100) not null,  		-- 학생회원 이메일
	student_phone_no varchar2(13) not null, 	-- 학생회원 핸드폰번호
	student_address varchar2(100) not null 		-- 학생회원 주소
);
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into STUDENT values('student1','1234','이영주','iidd1@kosta.com','010-1111-1111','경기도 용인시 처인구 고림동');
insert into STUDENT values('student2','1234','김수진','iidd2@kosta.com','010-1111-2222','경기도 수원시 장안구 파장동');
insert into STUDENT values('student3','1234','황경희','iidd3@kosta.com','010-1111-3333','경기도 안산시 상록구 본오동');
insert into STUDENT values('student4','1234','김세은','iidd4@kosta.com','010-1111-4444','서울시 서대문구 은평동');
------------------------------------------------------------------------------------------------------------------------------------------------------
-- 5.FAQ, 공지사항ㅇ
drop table basic_board;
create table basic_board(					-- 공지사항 & FAQ 테이블
	basic_no number primary key,			-- 글 번호
	basic_writer varchar2(50) not null,		-- 글 작성자
	basic_title varchar2(100) not null,		-- 글 제목	
	basic_content clob not null,			-- 글 내용
	basic_date varchar2(50) not null,		-- 글 작성일	
	basic_hit number not null,				-- 글 조회수	
	basic_type varchar2(30) not null		-- 글 종류
);
drop sequence basic_board_seq; 
create sequence basic_board_seq nocache;

-- 6.수강 후기ㅇ
drop table review_board;					
create table review_board(						-- 수강후기 테이블
	review_no number primary key,				-- 수강후기 번호
	review_writer_id  varchar2(50) not null,	-- 수강후기 작성자 아이디
	review_writer varchar2(50) not null,		-- 수강후기 작성자
	lecture_title varchar2(100) not null,		-- 강의 제목
	lecture_subject varchar2(50) not null,		-- 강의 종류
	review_title varchar2(100) not null,		-- 수강후기 제목
	review_content clob not null,				-- 수강후기 내용
	review_date varchar2(50) not null,			-- 수강후기 작성일
	review_hit number not null					-- 수강후기 조회수
);
DROP SEQUENCE review_board_seq;
CREATE SEQUENCE review_board_seq nocache;

-- 7.강의ㅇ
DROP table lecture cascade constraint;
CREATE TABLE lecture(								-- 강의 테이블
	lecture_no number primary key, 					-- 강의 번호 
	lecture_title varchar2(100) not null, 			-- 강의 제목
	lecture_description varchar2(1000) not null, 	-- 강의 설명
	lecture_start_time varchar2(15) not null, 		-- 강의 시작 시간
	lecture_end_time varchar2(15) not null, 		-- 강의 종료 시간
	lecture_day varchar2(50) not null, 				-- 강의 요일
	lecture_start_date varchar2(50) not null, 		-- 강의 시작일
	lecture_end_date varchar2(50) not null, 		-- 강의 종료일
	lecture_price number not null, 					-- 강의 수강료
	lecture_total_student number not null, 			-- 강의 수강가능 인원
	lecture_current_student number not null, 		-- 강의 현재수강 인원
	lecture_subject varchar2(50) not null, 			-- 강의 종류
	teacher_id2 varchar2(50), 						-- 강사회원 아이디 foreign key
	constraint fk_lecture_teacher foreign key (teacher_id2) references teacher(teacher_id)
);
drop sequence lecture_seq;
create sequence lecture_seq
nocache;
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into lecture values(5, '수학', '설명', '0515', '0516', '0820', '0821', '0822', 11, 2, 3, '수학', 'id-12');
insert into lecture values(5, '수학고등3', '설명', '0515', '0516', '0820', '0821', '0822', 11, 2, 3, '수학', 'id-12');
insert into lecture values(1, '수학고등3', '설ㅇ', '0515', '0516', '0420', '0821', '0822', 11, 2, 3, '수학', 'id-12');
insert into lecture values(2, '수학고등3', '설ㅇ', '0515', '0516', '0420', '0821', '0822', 11, 2, 3, '수학', 'id-12');
insert into lecture values(3, '수학고등3', '설ㅇ', '0515', '0516', '0420', '0821', '0822', 11, 2, 3, '수학', 'id-12');
insert into lecture values(4, '수학고등3', '설ㅇ', '0515', '0516', '0420', '0821', '0822', 11, 2, 3, '수학', 'id-12');
insert into lecture values(6, '수학고등3', '설ㅇ', '0515', '0516', '0420', '0821', '0822', 11, 2, 3, '수학', 'teacher1');
select * from lecture;
------------------------------------------------------------------------------------------------------------------------------------------------------

-- 8. 1:1문의, 질문게시판ㅇ
drop table advanced_board cascade constraint;
create table advanced_board(						-- 1:1문의 & 질문게시판 테이블
	advanced_no number primary key,					-- 글 번호
	advanced_secret varchar2(20) not null,			-- 비밀글 여부	
	advanced_title varchar2(100) not null,			-- 글 제목
	advanced_content clob not null,					-- 글 내용
	advanced_date varchar2(50) not null,			-- 글 작성일
	advanced_hit number not null,					-- 글 조회수
	advanced_id varchar2(50) not null,				--  
	advanced_type varchar2(50) not null,
	lecture_no2 number,
	constraint fk_advanced_lecture foreign key(lecture_no2) references lecture(lecture_no)
);
drop sequence advanced_board_seq;
create sequence advanced_board_seq nocache;
drop sequence advanced_board2_seq;
create sequence advanced_board2_seq nocache;
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into advanced_board values(4, 1, '제목', '내용', '2016-05-22', 1, 'id-11', 5);
insert into advanced_board values(5, 1, '제목2', '내용2', '2016-05-23', 1, 'id-13', null, null);
insert into advanced_board values(6, 1, '제목3', '내용3', '2016-05-24', 1, 'id-14', null, null);
select * from ADVANCED_BOARD;
insert into ADVANCED_BOARD values(1, '0', '제목1', '내용1', '2016-05-05', 0, 'student', '1:1문의', null);
------------------------------------------------------------------------------------------------------------------------------------------------------

-- 9. 코멘트
DROP table comment_table cascade constraint;
CREATE TABLE comment_table(
	comment_no number primary key,
	comment_content clob not null,
	comment_date varchar2(50) not null,
	comment_writer varchar2(50) not null, 
	comment_type varchar2(50) not null,
	advanced_no2 number not null,
	constraint fk_comment_advanced foreign key (advanced_no2) references advanced_board(advanced_no)
);
drop sequence comment_board_seq;
create sequence comment_board_seq nocache;
select * from COMMENT_Table;
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into COMMENT_TABLE values(11, '댓글내용1', '2015-06-04', 'student2', '1:1문의댓글', 9);
insert into COMMENT_TABLE values(12, '댓글내용2', '2015-06-05', 'student2', '1:1문의댓글', 9);
insert into COMMENT_TABLE values(11, '댓글내용3', '2015-06-06', 'student2', '1:1문의댓글', 2);
insert into COMMENT_TABLE values(1, '댓글내용1', '2015-06-04', 'student2', '강의질문댓글', 100);
------------------------------------------------------------------------------------------------------------------------------------------------------

select * from file_board;
drop table file_board cascade constraint;

--10. 영주 파일업로드할 테이블
create table file_board(
title varchar2(1000) null ,  -->사용안함
upfile varchar2(4000)
);

-- 11.과제게시판
select * from assignment_board;
drop table assignment_board cascade constraint;
create table assignment_board(
	assignment_no number primary key,
	assignment_writer_id varchar2(50) not null,
	assignment_secret number not null,--사용안함
	assignment_title varchar2(100) not null,
	assignment_content clob not null,
	assignment_date varchar2(50) not null,
	assignment_hit number not null,
	assignment_password number null,--사용안함
	assignment_re_family number not null,
	assignment_re_step number not null,
	assignment_re_level number not null,
	assignment_writer varchar2(50) not null,
	assignment_deadline varchar2(10) not null,
	assignment_file varchar2(100) null,
	lecture_no number not null, -- n o t null 로바꾸기 영주
	constraint fk_assignment_lecture foreign key (lecture_no) references lecture(lecture_no)
);
drop sequence assignment_board_seq;
create sequence assignment_board_seq nocache;
------------------------------------------------------------------------------------------------------------------------------------------------------
insert into assignment_board values(assignment_board_seq.nextval,'1',0,'고등국어3','고등국어2과제입니다제풀해주세요','20160622',0,1234,1,0,0,'1이름','20160627','파일',1);
insert into assignment_board values(assignment_board_seq.nextval,'1',0,'고등국ㅇ3','고등국어2과제입니다제풀해주세요','20160622',0,1234,1,0,0,'12름','20160627','파일',13);
------------------------------------------------------------------------------------------------------------------------------------------------------
--영주 : 지우지마세요~!
--	assignment_no number primary key,					--1.글번호
--	assignment_title varchar2(100) not null,				--3.글제목
--	assignment_secret number not null,					--4.비밀여부
--	assignment_writer varchar2(50) not null,				--6.글쓴이
--	assignment_date varchar2(50) not null,				--7.글 등록일
--	assignment_deadline varchar2(10) not null,			--8.글 마감일
--	assignment_hit number not null,						--9.글 조회수
--	
--	assignment_password number null,					--글 비밀번호
--	assignment_content clob not null,					--글내용
--	reply          number not null,							--답글 그룹reply
--	reply_step   number not null,							--답글 레벨reply_step
--	reply_level   number not null,							--답글 스텝 (순서)reply_level
--	
--	lecture_no number not null							--F키 , 강의번호	2.강의명	5.강사명

-- 12. 학생 강의 조인
DROP table student_lecture_join cascade constraint;
CREATE TABLE student_lecture_join(
	student_id3 varchar2(50),				-- 학생회원 아이디
	lecture_no3 number,						-- 강의 번호
	primary key (student_id3, lecture_no3), -- 학생아이디 primary key, -- 강의번호 primary key
	zzim_option varchar2(1) not null, 		-- 찜여부
	constraint fk_student_lecture_join foreign key (student_id3) references student(student_id),
	constraint fk_lecture_student_join foreign key (lecture_no3) references lecture(lecture_no)
);

-- 13. 출석부
DROP table attendance;
CREATE TABLE attendance( 
	attandance_state varchar2(6) not null, 				-- 출석상태
	lecture_day number not null, 						-- 강의일차
	student_id2 varchar2(50) not null,					-- 학생회원 아이디 foreign key
	lecture_no2 number not null, 						-- 강의번호 foreign key
	constraint fk_attendance foreign key (student_id2, lecture_no2) references student_lecture_join(student_id3, lecture_no3)
);
------------------------------------------------------------------------------------------------------------------------------------------------------
-- 0 . code_table insert하는 구문  ( -> 이거하려면 출결table지웠다가 다시해야함)
insert into CODE_TABLE values(code_table_seq.nextval, '공지사항', 'basic_board');
insert into CODE_TABLE values(code_table_seq.nextval, 'FAQ', 'basic_board');
insert into CODE_TABLE values(code_table_seq.nextval, '국어', 'teacherSubject');
insert into CODE_TABLE values(code_table_seq.nextval, '영어', 'teacherSubject');
insert into CODE_TABLE values(code_table_seq.nextval, '수학', 'teacherSubject');
drop sequence CODE_TABLE_seq;
create sequence CODE_TABLE_seq nocache;
