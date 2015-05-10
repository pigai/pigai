package com.pigai.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pigai.dao.HomeworkDao;
import com.pigai.entity.Homework;

@Repository("homeworkDao")
public class HomeworkDaoImpl extends BaseDaoImpl implements HomeworkDao {
	protected static final Class<Homework> entityClass = Homework.class;

	@SuppressWarnings("unchecked")
	@Override
	public int getHomeworkCountByCourseId(Integer courseId) throws Exception {
		String hql = " where o.course.courseId = ? ";
		return getResultCount(entityClass, hql, courseId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Homework> getHomeworksByCourseId(Integer courseId,
			int offset, int pageSize) throws Exception {
		String hql = " where o.course.courseId = ? ";

		return getResultList(entityClass, hql, offset, pageSize, null,
				new Object[] { courseId });

	}

}
