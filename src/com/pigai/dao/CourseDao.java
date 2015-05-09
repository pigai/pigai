package com.pigai.dao;

import java.util.List;

import com.pigai.entity.Course;
import com.pigai.vo.CourseCriteria;

public interface CourseDao extends BaseDao<Course>{

	public List<Course> getCoursesByCriteria(CourseCriteria criteria, int offset,
			int pageSize)throws Exception;
	public int getCourseCountByCriteria(CourseCriteria criteria) throws Exception;
	
	public Long getCourseTotalNumByStudentId(Integer studentId);
	public List<Course> findCourseByStudentIdByPage(Integer studentId,Integer pageNum);
	
	public Long getCourseTotalNumByTeacherId(Integer teacherId);
	public List<Course> findCourseByTeacherIdByPage(Integer teacherId,Integer pageNum);
	
}
