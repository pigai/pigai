package com.pigai.dao.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.pigai.dao.BaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	@Override
	public T save(T entity) {
		return (T) getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<T> clazz, Serializable id) {
		delete(get(clazz, id));
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T get(Class<T> clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll(Class<T> clazz) {
		return (List<T>) getHibernateTemplate().find(
				"from " + clazz.getSimpleName());
	}

	@Override
	public List<T> findAll(Class<T> clazz, String condition, Object... objects) {
		String sql = "from " + clazz.getSimpleName() + " " + condition;
		return (List<T>) getHibernateTemplate().find(sql, objects);
	}

	@Resource(name = "sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public <T> List<T> getResultList(Class<T> entityClass, String whereJpql,
			LinkedHashMap<String, String> orderBy, Object... args) {
		// 获取实体类的实体名。默认情况下，实体名和类名相同
		String entityName = entityClass.getSimpleName();
		// 创建查询
		Query query = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						("select distinct(o) from " + entityName + " as o "
								+ (null == whereJpql ? "" : whereJpql) + buildOrderby(orderBy)));
		// 为查询字符串中参数设置值
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				if (null != args[i]) {
					query.setParameter(i, args[i]);
				}
			}
		}
		// 返回结果集
		return (List<T>) query.list();
	}

	public <T> List<T> getResultList(Class<T> entityClass, String whereJpql,
			int firstResult, int maxResult,
			LinkedHashMap<String, String> orderBy, Object... args) {
		// 获取实体类的实体名。默认情况下，实体名和类名相同
		String entityName = entityClass.getSimpleName();
		// 创建查询
		Query query = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						("select distinct(o) from " + entityName + " as o "
								+ (null == whereJpql ? "" : whereJpql) + buildOrderby(orderBy)));
		// 为查询字符串中参数设置值
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				if (null != args[i]) {
					query.setParameter(i, args[i]);
				}
			}
		}
		// 对查询结果集进行分页
		query.setMaxResults(maxResult).setFirstResult(firstResult);
		// 返回结果集
		return (List<T>) query.list();
	}

	/**
	 * 构建排序子句
	 * 
	 * @param orderby
	 *            LinkedHashMap对象，每个key-value对指定一个排序条件
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer out = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			// 添加order by 子句
			out.append(" order by ");
			// 遍历LinkedHashMap中的每个key-value对，
			// 每个key-value对生成一个排序条件
			for (String key : orderby.keySet()) {
				out.append("o." + key + " " + orderby.get(key));
				out.append(",");
			}
			out.deleteCharAt(out.length() - 1);
		}
		return out.toString();
	}

	public <T> int getResultCount(Class<T> entityClass, String whereJpql,
			Object... args) {
		return getResultCountDistinct(entityClass, "*", whereJpql, args);
	}

	@Override
	public <T> int getResultCountDistinct(Class<T> entityClass,
			String distinct, String whereJpql, Object... args) {
		// 获取实体类的实体名。默认情况下，实体名和类名相同
		String entityName = entityClass.getSimpleName();
		// 创建查询
		Query query = getSessionFactory().getCurrentSession()
				.createQuery(
						("select count(" + distinct + ") from " + entityName
								+ " as o " + (null == whereJpql ? ""
								: whereJpql)));
		// 为查询字符串中参数设置值
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				if (null != args[i]) {
					query.setParameter(i, args[i]);
				}
			}
		}
		if (query.list().size() == 0) {
			return 0;
		}
		return Integer.parseInt(query.uniqueResult().toString());
	}

}
