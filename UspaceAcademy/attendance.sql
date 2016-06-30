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

