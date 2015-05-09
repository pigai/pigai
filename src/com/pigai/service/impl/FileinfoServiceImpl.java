package com.pigai.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigai.dao.CommonDao;
import com.pigai.entity.Fileinfo;
import com.pigai.service.FileinfoService;

@Service("fileinfoService")
public class FileinfoServiceImpl extends BaseServiceImpl<Fileinfo> implements
		FileinfoService {

	protected static final Class<Fileinfo> entityClass = Fileinfo.class;

	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Serializable objkey) throws Exception {

		commonDao.delete(entityClass, objkey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Fileinfo get(Serializable primaryKey) throws Exception {

		return (Fileinfo) commonDao.get(entityClass, primaryKey);
	}

}
