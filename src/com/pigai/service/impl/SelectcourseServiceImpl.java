package com.pigai.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigai.dao.CommonDao;
import com.pigai.dao.SelectcourseDao;
import com.pigai.entity.Selectcourse;
import com.pigai.service.SelectcourseService;

@Service("selectcourseService")
public class SelectcourseServiceImpl extends BaseServiceImpl<Selectcourse> implements
SelectcourseService{
	
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private SelectcourseDao selectcourseDao;
	
	protected static final Class<Selectcourse> entityClass = Selectcourse.class;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Serializable objkey) throws Exception {

		commonDao.delete(entityClass, objkey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Selectcourse get(Serializable primaryKey) throws Exception {

		return (Selectcourse) commonDao.get(entityClass, primaryKey);
	}

}
