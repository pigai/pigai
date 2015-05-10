package com.pigai.service;

import com.pigai.entity.Homework;
import com.pigai.util.PageModel;

public interface HomeworkService extends BaseService<Homework> {

	public PageModel getPageModelByCourseId(PageModel pageModel,
			Integer courseId);

}
