package com.pigai.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.pigai.dao.SelectcourseDao;
import com.pigai.entity.Selectcourse;

@Repository("selectcourseDao")
public class SelectcourseDaoImpl extends BaseDaoImpl<Selectcourse> implements SelectcourseDao{

	@Override
	public Selectcourse getSelectcourseByCourseIdAndStudentId(final Integer courseId,
			final Integer studentId) {
		return getHibernateTemplate().execute(new HibernateCallback<Selectcourse>()
				{
					@Override
					public Selectcourse doInHibernate(Session session)throws HibernateException
					{
						String hql="from Selectcourse where course=:courseId and student=:studentId ";
						Query query=session.createQuery(hql);
						query.setInteger("courseId", courseId);
						query.setInteger("studentId", studentId);
						Selectcourse selectcourse=(Selectcourse) query.uniqueResult();
						return selectcourse;
					}
					
				});
	}




}
