package com.pigai.dao;

import com.pigai.entity.Selectcourse;

public interface SelectcourseDao extends BaseDao<Selectcourse>{

	Selectcourse getSelectcourseByCourseIdAndStudentId(Integer courseId,Integer studentId);
}
