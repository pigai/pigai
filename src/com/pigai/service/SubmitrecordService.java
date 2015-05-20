package com.pigai.service;


import com.pigai.entity.Submitrecord;
import com.pigai.util.PageModel;

public interface SubmitrecordService extends BaseService<Submitrecord> {

	public PageModel getPageModelByHomeWorkId(PageModel pageModel,
			Integer homeworkId);
	
	public PageModel getPageModelByHomeWorkIdAndStudentId(PageModel pageModel,
			Integer homeworkId,Integer studentId);
	
	public PageModel getPageModelByStudentId(PageModel pageModel,
			Integer studentId);

	public void updateScore(int score, Integer id) ;

}
