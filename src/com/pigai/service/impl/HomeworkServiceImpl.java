package com.pigai.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigai.dao.CommonDao;
import com.pigai.dao.HomeworkDao;
import com.pigai.entity.Homework;
import com.pigai.service.HomeworkService;
import com.pigai.util.PageModel;

@Service("homeworkService")
public class HomeworkServiceImpl extends BaseServiceImpl<Homework>
		implements HomeworkService {

	protected static final Class<Homework> entityClass = Homework.class;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private HomeworkDao homeworkDao;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Serializable objkey) throws Exception {

		commonDao.delete(entityClass, objkey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Homework get(Serializable primaryKey) throws Exception {

		return (Homework) commonDao.get(entityClass, primaryKey);
	}

	@Override
	public PageModel getPageModelByCourseId(PageModel pageModel,
			Integer courseId) {
		try {
			pageModel.setPageData(homeworkDao.getHomeworksByCourseId(
					courseId, pageModel.getOffset(), pageModel.getPageSize()));
			pageModel.setTotalRecord(homeworkDao
					.getHomeworkCountByCourseId(courseId));
		} catch (Exception e) {
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}

}
