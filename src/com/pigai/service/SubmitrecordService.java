package com.pigai.service;


import com.pigai.entity.Submitrecord;
import com.pigai.util.PageModel;

public interface SubmitrecordService extends BaseService<Submitrecord> {

	public PageModel getPageModelByHomeWorkId(PageModel pageModel,
			Integer homeworkId);

}
