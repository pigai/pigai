package com.pigai.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pigai.dao.CoursewareDao;
import com.pigai.entity.Courseware;

@Repository("coursewareDao")
public class CoursewareDaoImpl extends BaseDaoImpl implements CoursewareDao {
	protected static final Class<Courseware> entityClass = Courseware.class;

	@SuppressWarnings("unchecked")
	@Override
	public int getCoursewareCountByCourseId(Integer courseId) throws Exception {
		String hql = " where o.course.courseId = ? ";
		return getResultCount(entityClass, hql, courseId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Courseware> getCoursewaresByCourseId(Integer courseId,
			int offset, int pageSize) throws Exception {
		String hql = " where o.course.courseId = ? ";

		return getResultList(entityClass, hql, offset, pageSize, null,
				new Object[] { courseId });

	}

}
