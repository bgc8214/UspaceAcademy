-- 강사 - 내강좌 정보 
select distinct l.lecture_no, l.lecture_title, l.lecture_start_date, l.lecture_end_date 
from lecture l, STUDENT_LECTURE_JOIN sl
where l.teacher_id2='34567' and sl.lecture_no3 = l.lecture_no


-- 19번 강좌를 수강중인 학생정보
select s.student_id, s.student_name
from lecture l, student_lecture_join sl, student s
where s.student_id = sl.student_id3
and sl.lecture_no3 = l.lecture_no and sl.lecture_no3=21 and sl.zzim_option ='0'

-- 21번 강좌를 수강중인 학생정보를 학생아이디로
select s.student_id, s.student_name
from lecture l, student_lecture_join sl, student s
where s.student_id = sl.student_id3
and sl.lecture_no3 = l.lecture_no and sl.lecture_no3 = 21 and sl.zzim_option='0'

-- 21번 강좌를 수강중인 학생정보를 학생아이디로 오름차순
select s.student_id, s.student_name
		from lecture l, student_lecture_join sl, student s
		where s.student_id = sl.student_id3 and sl.lecture_no3 = l.lecture_no 
			and sl.lecture_no3 =19 and sl.zzim_option='0'
		order by s.student_id
		
select distinct *
from ATTENDANCE
where lecture_no2 = 21
order by lecture_day, student_id2

-- 출석부 수정 테스트
update attendance
set attandance_state = '조퇴'
where lecture_day = 3 and lecture_no2 = 21 and student_id2 = 'student4'


select ab.assignment_title, ab.assignment_hit, l.lecture_no, l.lecture_day
from ASSIGNMENT_BOARD ab, lecture l
where ab.lecture_no = l.lecture_no

delete from LECTURE where lecture_no = 22

delete from teacher where teacher_id = '34567'

drop table assignment_board

drop table advanced_board

select *from student_lecture_join

delete 
from STUDENT_LECTURE_JOIN 
where lecture_no3 = 22

delete
from ATTENDANCE
where lecture_no2 = 22

delete 
from ATTENDANCE
where student_id2 = '11111'

delete
from STUDENT_LECTURE_JOIN
where student_id3 = '11111'

delete
from STUDENT
where student_id = '11111'

delete
from STUDENT_LECTURE_JOIN
where lecture_no3 = 18

delete
from lecture
where teacher_id2 = '12345'

delete 
from TEACHER
where teacher_id = '12345'

-- 강사 탈퇴를 위한 조건
 1. 강의테이블에서 강사가 강의하고 있는 강의 번호 조회
	select lecture_no
	from lecture
	where teacher_id2 = '678910'

 2. 학생_강의_조인 테이블에서 강의번호로 삭제
 
 3. 강의 테이블 - 강사 아이디 삭제
 
 4. 강사 테이블 - 삭제
 
 ----------------------------------------------
 
 
 		select distinct l.lecture_no, l.lecture_title, l.lecture_start_date, l.lecture_end_date, l.lecture_current_student, l.lecture_total_student
		from lecture l, student_lecture_join sl
		where l.teacher_id2 = '34567' and sl.lecture_no3 = l.lecture_no
		order by l.lecture_no
		
		drop table lecture cascade constraint
		