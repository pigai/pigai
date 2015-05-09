package com.pigai.service;

import com.pigai.entity.Course;
import com.pigai.util.PageModel;
import com.pigai.vo.CourseCriteria;


public interface CourseService extends BaseService<Course>{
	
public PageModel getPageModelByCriteria(PageModel pageModel,CourseCriteria criteria);
public PageModel getPageModelByStudentId(PageModel pageModel,Integer studentId);
public PageModel getPageModelByTeacherId(PageModel pageModel,Integer teacherId);

}
