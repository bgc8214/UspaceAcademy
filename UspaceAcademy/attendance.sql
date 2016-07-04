-- 출석 조회를 위한  --

select *
from attendance
where lecture_no2 = 21 
order by lecture_day, student_id2

resultType="_int"
1. int max = selectOne( select max(lecture_day) from attendance where lecture_no2 = 21)
조회 값이 null - 0리턴
int max;


ArrayList<List> list = new ArrayList();
for(int i =1; i<=max; i++){
	List<String> alist = selectList(select attandance_state from attendance where lecture_no2=21 and lecture_day=i order by student_id2)
	list.add(alist)
}


-- 영주 
--	SELECT   a.assignment_no, a.assignment_writer_id, a.assignment_secret, a.assignment_title, a.assignment_content,
--			    a.assignment_date, a.assignment_hit, a.assignment_password, a.assignment_re_family,
--			    a.assignment_re_step, a.assignment_re_level, a.assignment_writer, a.assignment_deadline,
--				a.lecture_no,
--				l.lecture_no, l.lecture_title, l.lecture_description, l.lecture_start_time, l.lecture_end_time, l.lecture_day, 
--				l.lecture_start_date, l.lecture_end_date, l.lecture_price, l.lecture_total_student, l.lecture_current_student, l.lecture_subject, l.teacher_id2   
--	
--	FROM  assignment_board a, lecture l
--	WHERE  a.lecture_no= 1


-- 학생이 수강 중인 강의 정보
select distinct l.lecture_no, l.lecture_title
from student st, student_lecture_join sl, lecture l
where sl.student_id3 = 'dlalwl' and sl.lecture_no3 = l.lecture_no and sl.zzim_option = '0'

-- 학생이 선택한 강좌의 출결 정보
select lecture_day, attandance_state, student_id2
from attendance
where lecture_no2 = 21 and student_id2 = 'student4'
order by lecture_day 


-- 관리자 강사관리 탭 선택 시 강사정보(강의중인 강의 정보 포함) - O
select t.teacher_id 강사아이디, t.teacher_name 강사이름, t.teacher_email 이메일, t.teacher_phone_no 전화번호, t.teacher_address 주소, t.teacher_subject 과목, t.teacher_salary 월급, l.lecture_no 강의번호, l.lecture_title 강의제목, l.lecture_description 강의내용, l.lecture_start_time 시작시간, l.lecture_end_time 종료시간, l.lecture_day 강의요일, l.lecture_start_date 강의시작일, l.lecture_end_date 강의종료일, l.lecture_price 수강료, l.lecture_total_student 총인원, l.lecture_current_student 현재인원, l.lecture_subject 과목, teacher_id2 강사아이디 
from teacher t, lecture l
where t.teacher_id = l.teacher_id2

-- 관리자가 학생관리 탭 선택 시 학생정보 - X ->보류
select s.student_id 학생아이디, s.student_name 학생이름, s.student_email 이메일, s.student_phone_no 전화번호, s.student_address 주소, l.lecture_no 강의번호, l.lecture_title 강의제목, l.lecture_description 강의내용, l.lecture_start_time 시작시간, l.lecture_end_time 종료시간, l.lecture_day 강의요일, l.lecture_start_date 강의시작일, l.lecture_end_date 강의종료일, l.lecture_price 수강료, l.lecture_total_student 총인원, l.lecture_current_student 현재인원, l.lecture_subject 과목, teacher_id2 강사아이디
from student s, student_lecture_join sl, lecture l
where s.student_id = sl.student_id3 and sl.lecture_no3 = l.lecture_no and zzim_option = '0'

select *from student
 	
