package com.pigai.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigai.dao.CommonDao;
import com.pigai.dao.CourseDao;
import com.pigai.entity.Course;
import com.pigai.service.CourseService;
import com.pigai.util.PageModel;
import com.pigai.vo.CourseCriteria;

@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService{

	protected static final Class<Course> entityClass = Course.class;
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Serializable objkey) throws Exception {
	
		commonDao.delete(entityClass, objkey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Course get(Serializable primaryKey) throws Exception {
	
		return (Course) commonDao.get(entityClass, primaryKey);
	}

	@Override
	public PageModel getPageModelByCriteria(PageModel pageModel,
			CourseCriteria criteria) {
		try{
			pageModel.setPageData(courseDao.getCoursesByCriteria(criteria,pageModel.getOffset(),pageModel.getPageSize()));
			pageModel.setTotalRecord(courseDao.getCourseCountByCriteria(criteria));
		}catch(Exception e){
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}

	@Override
	public PageModel getPageModelByStudentId(PageModel pageModel,Integer studentId) {
		try{
			pageModel.setPageData(courseDao.findCourseByStudentIdByPage(studentId, pageModel.getOffset()));
			pageModel.setTotalRecord(courseDao.getCourseTotalNumByStudentId(studentId).intValue());
		}catch(Exception e){
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}

	@Override
	public PageModel getPageModelByTeacherId(PageModel pageModel,Integer teacherId) {
		try{
			pageModel.setPageData(courseDao.findCourseByTeacherIdByPage(teacherId, pageModel.getOffset()));
			pageModel.setTotalRecord(courseDao.getCourseTotalNumByTeacherId(teacherId).intValue());
		}catch(Exception e){
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}



}
