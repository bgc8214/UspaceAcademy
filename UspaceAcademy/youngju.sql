--	 --조인
--		SELECT   a.assignment_no, a.assignment_writer_id, a.assignment_secret, a.assignment_title, a.assignment_content,
--				    a.assignment_date, a.assignment_hit, a.assignment_password, a.assignment_re_family,
--				    a.assignment_re_step, a.assignment_re_level, a.assignment_writer, a.assignment_deadline,
--					a.lecture_no,
--					l.lecture_no, l.lecture_title, l.lecture_description, l.lecture_start_time, l.lecture_end_time, l.lecture_day, 
--					l.lecture_start_date, l.lecture_end_date, l.lecture_price, l.lecture_total_student, l.lecture_current_student, l.lecture_subject, l.teacher_id2   
--		
--		FROM  assignment_board a, lecture l
--		WHERE  a.lecture_no= 1
--	--
--			SELECT   a.assignment_no, a.assignment_writer_id, a.assignment_secret, a.assignment_title, a.assignment_content,
--			    a.assignment_date, a.assignment_hit, a.assignment_password, a.assignment_re_family,
--			    a.assignment_re_step, a.assignment_re_level, a.assignment_writer, a.assignment_deadline,
--				a.lecture_no,
--				l.lecture_no, l.lecture_title, l.lecture_description, l.lecture_start_time, l.lecture_end_time, l.lecture_day, 
--				l.lecture_start_date, l.lecture_end_date, l.lecture_price, l.lecture_total_student, l.lecture_current_student, l.lecture_subject, l.teacher_id2   
--	
--	FROM  assignment_board a, lecture l
--	WHERE  a.lecture_no=l.lecture_no 
--	--
--	SELECT   a.assignment_no, a.assignment_writer_id, a.assignment_secret, a.assignment_title, a.assignment_content,
--			    a.assignment_date, a.assignment_hit, a.assignment_password, a.assignment_re_family,
--			    a.assignment_re_step, a.assignment_re_level, a.assignment_writer, a.assignment_deadline,
--				a.lecture_no,
--				l.lecture_no, l.lecture_title, l.lecture_description, l.lecture_start_time, l.lecture_end_time, l.lecture_day, 
--				l.lecture_start_date, l.lecture_end_date, l.lecture_price, l.lecture_total_student, l.lecture_current_student, l.lecture_subject, l.teacher_id2   
--	
--	FROM  assignment_board a, lecture l
--	WHERE   l.lecture_no
	
	
	


-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	--lecture assignment_board 조인
		SELECT   a.assignment_no, a.assignment_writer_id, a.assignment_secret, a.assignment_title, a.assignment_content,
			    a.assignment_date, a.assignment_hit, a.assignment_password, a.assignment_re_family,
			    a.assignment_re_step, a.assignment_re_level, a.assignment_writer, a.assignment_deadline,
				a.lecture_no,
				l.lecture_no, l.lecture_title, l.lecture_description, l.lecture_start_time, l.lecture_end_time, l.lecture_day, 
				l.lecture_start_date, l.lecture_end_date, l.lecture_price, l.lecture_total_student, l.lecture_current_student, l.lecture_subject, l.teacher_id2   
	
	FROM  assignment_board a, lecture l
	WHERE   l.lecture_no=4
	
	
	--lectureMapper에 한거
	SELECT lecture_no, lecture_title, lecture_description, lecture_start_time, lecture_end_time, lecture_day, lecture_start_date, lecture_end_date, lecture_price, lecture_total_student, lecture_current_student, lecture_subject, teacher_id2   
	FROM 	lecture
	WHERE  lecture_subject='국어'
		

	desc assignment_board	
	
	
	
		SELECT assignment_no, assignment_writer_id, assignment_secret, assignment_title, assignment_content,
	assignment_date, assignment_hit, assignment_password, assignment_re_family,
	assignment_re_step, assignment_re_level, assignment_writer, assignment_deadline, assignment_file,
	lecture_no
	FROM(SELECT CEIL (rownum/1) page, assignment_no, assignment_writer_id, assignment_secret, assignment_title, assignment_content,
	assignment_date, assignment_hit, assignment_password, assignment_re_family,
	assignment_re_step, assignment_re_level, assignment_writer, assignment_deadline, assignment_file,
	lecture_no
	FROM(SELECT assignment_no, assignment_writer_id, assignment_secret, assignment_title, assignment_content,
	assignment_date, assignment_hit, assignment_password, assignment_re_family,
	assignment_re_step, assignment_re_level, assignment_writer, assignment_deadline, assignment_file,
	lecture_no
			FROM assignment_board 
			where lecture_no = 1
			order by   assignment_no   desc
		)
	)
	WHERE page=1
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	