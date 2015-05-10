package com.pigai.dao;

import java.util.List;

import com.pigai.entity.Homework;

public interface HomeworkDao extends BaseDao {

	List<Homework> getHomeworksByCourseId(Integer courseId, int offset,
			int pageSize) throws Exception;

	int getHomeworkCountByCourseId(Integer courseId) throws Exception;

}
