package com.pigai.service;

import com.pigai.entity.Selectcourse;


public interface SelectcourseService extends BaseService<Selectcourse>{

	public Selectcourse getSelectcourseByCourseIdAndStudentId(Integer courseId,Integer studentId);
}
