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







	SELECT   a.assignment_no, a.assignment_writer_id, a.assignment_secret, a.assignment_title, a.assignment_content,
			    a.assignment_date, a.assignment_hit, a.assignment_password, a.assignment_re_family,
			    a.assignment_re_step, a.assignment_re_level, a.assignment_writer, a.assignment_deadline,
				a.lecture_no,
				l.lecture_no, l.lecture_title, l.lecture_description, l.lecture_start_time, l.lecture_end_time, l.lecture_day, 
				l.lecture_start_date, l.lecture_end_date, l.lecture_price, l.lecture_total_student, l.lecture_current_student, l.lecture_subject, l.teacher_id2   
	
	FROM  assignment_board a, lecture l
	WHERE  a.lecture_no= 1