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
