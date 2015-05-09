package com.pigai.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pigai.constant.Constants;
import com.pigai.dao.CourseDao;
import com.pigai.entity.Course;
import com.pigai.vo.CourseCriteria;

@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
	protected static final Class<Course> entityClass = Course.class;

	@Override
	public List<Course> getCoursesByCriteria(CourseCriteria criteria,
			int offset, int pageSize) throws Exception {
		List<Object> args = new ArrayList<Object>();
		return getResultList(entityClass, _ForGetHqlByCriteria(args, criteria),
				offset, pageSize, null, args.toArray());
	}

	@Override
	public int getCourseCountByCriteria(CourseCriteria criteria)
			throws Exception {

		List<Object> args = new ArrayList<Object>();
		return getResultCount(entityClass,
				_ForGetHqlByCriteria(args, criteria), args.toArray());
	}

	/**
	 * 根据条件生成hql查询语句
	 * 
	 * @param args
	 * @param criteria
	 * @return
	 */
	private String _ForGetHqlByCriteria(List<Object> args,
			CourseCriteria criteria) {

		StringBuffer queryHql = new StringBuffer("where 1=1");
		if (null == criteria) {
			return Constants.EMPTY;
		}

		if (!StringUtils.isEmpty(criteria.getTeacherName())) {
			queryHql.append(" and o.teacherName like ?");
			args.add("%" + criteria.getTeacherName() + "%");
		}

		if (!StringUtils.isEmpty(criteria.getCourseName())) {
			queryHql.append(" and o.courseName like ?");
			args.add("%" + criteria.getCourseName() + "%");
		}
		return queryHql.toString();
	}

	@Override
	public Long getCourseTotalNumByStudentId(final Integer studentId) {
		{
			return getHibernateTemplate().execute
					(
					   new HibernateCallback<Long>()
					   {
						  @Override
						  public Long doInHibernate(Session session) throws HibernateException
						  {
							  String hql="select count(distinct c) from Course c,Selectcourse sc where c.courseId = sc.course and sc.student=:studentId";
							  Query query=session.createQuery(hql);
							  query.setCacheable(true);
							  query.setInteger("studentId", studentId);
							  return (Long) query.uniqueResult();
						  }
					   }
				    );
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Course> findCourseByStudentIdByPage(final Integer studentId,
			final Integer pageNum) {
		return getHibernateTemplate().execute
				(
				   new HibernateCallback<List<Course>>()
				   {
					  @Override
					  public List<Course> doInHibernate(Session session) throws HibernateException
					  {
						  String hql="select distinct c from Course c,Selectcourse sc where c.courseId = sc.course and sc.student=:studentId";
						  Query query = session.createQuery(hql);
						  query.setCacheable(true);
						  query.setInteger("studentId", studentId);
					      query.setFirstResult((pageNum-1)*Constants.PAGE_Size);
					      query.setMaxResults(Constants.PAGE_Size);
					      return (List<Course>)query.list();
					  }
				   }
			    );
	}

	@Override
	public Long getCourseTotalNumByTeacherId(final Integer teacherId) {
		{
			return getHibernateTemplate().execute
					(
					   new HibernateCallback<Long>()
					   {
						  @Override
						  public Long doInHibernate(Session session) throws HibernateException
						  {
							  String hql="select count(distinct c) from Course c where teacher=:teacherId";
							  Query query=session.createQuery(hql);
							  query.setCacheable(true);
							  query.setInteger("teacherId", teacherId);
							  return (Long) query.uniqueResult();
						  }
					   }
				    );
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Course> findCourseByTeacherIdByPage(Integer teacherId,
			Integer pageNum) {
		return getHibernateTemplate().execute
				(
				   new HibernateCallback<List<Course>>()
				   {
					  @Override
					  public List<Course> doInHibernate(Session session) throws HibernateException
					  {
						  String hql="select distinct c from Course c where teacher=:teacherId";
						  Query query = session.createQuery(hql);
						  query.setCacheable(true);
						  query.setInteger("teacherId", teacherId);
					      query.setFirstResult((pageNum-1)*Constants.PAGE_Size);
					      query.setMaxResults(Constants.PAGE_Size);
					      return (List<Course>)query.list();
					  }
				   }
			    );
	}

}
