package com.pigai.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigai.dao.CommonDao;
import com.pigai.dao.SelectcourseDao;
import com.pigai.dao.SubmitrecordDao;
import com.pigai.entity.Selectcourse;
import com.pigai.entity.Submitrecord;
import com.pigai.service.SubmitrecordService;
import com.pigai.util.PageModel;

@Service("submitrecordService")
public class SubmitrecordServiceImpl extends BaseServiceImpl<Submitrecord>
		implements SubmitrecordService {

	protected static final Class<Submitrecord> entityClass = Submitrecord.class;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private SubmitrecordDao submitrecordDao;

	@Autowired
	private SelectcourseDao selectcourseDao;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Serializable objkey) throws Exception {

		commonDao.delete(entityClass, objkey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Submitrecord get(Serializable primaryKey) throws Exception {

		return (Submitrecord) commonDao.get(entityClass, primaryKey);
	}

	@Override
	public PageModel getPageModelByHomeWorkId(PageModel pageModel,
			Integer homeworkId) {
		try {
			pageModel
					.setPageData(submitrecordDao.getSubmitrecordsByHomeworkId(
							homeworkId, pageModel.getOffset(),
							pageModel.getPageSize()));
			pageModel.setTotalRecord(submitrecordDao
					.getHomeworkCountByHomeworkId(homeworkId));
		} catch (Exception e) {
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}

	@Override
	public PageModel getPageModelByHomeWorkIdAndStudentId(PageModel pageModel,
			Integer homeworkId, Integer studentId) {
		try {
			pageModel.setPageData(submitrecordDao
					.getSubmitrecordsByHomeworkIdAndStudentId(homeworkId,
							studentId, pageModel.getOffset(),
							pageModel.getPageSize()));
			pageModel.setTotalRecord(submitrecordDao
					.getSubmitrecordsCountByHomeworkIdAndStudentId(homeworkId,
							studentId));
		} catch (Exception e) {
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}

	@Override
	public PageModel getPageModelByStudentId(PageModel pageModel,
			Integer studentId) {
		try {
			pageModel.setPageData(submitrecordDao.getSubmitrecordsByStudentId(
					studentId, pageModel.getOffset(), pageModel.getPageSize()));
			pageModel.setTotalRecord(submitrecordDao
					.getSubmitrecordsCountByStudentId(studentId));
		} catch (Exception e) {
			e.printStackTrace();
			pageModel.setPageData(java.util.Collections.EMPTY_LIST);
			pageModel.setTotalRecord(0);
		}
		return pageModel;
	}

	@Override
	public void updateScore(int score, Integer id) {

		try {
			Submitrecord submitrecord = get(id);
			int changeScore = score - submitrecord.getScore();
			submitrecord.setScore(score);
			Selectcourse selectCourse = selectcourseDao
					.getSelectcourseByCourseIdAndStudentId(submitrecord
							.getHomework().getCourse().getCourseId(),
							submitrecord.getStudent().getStudentId());
			selectCourse.setGrade(selectCourse.getGrade() + changeScore);
			selectcourseDao.update(selectCourse);
			submitrecord.setCorrect(true);
			update(submitrecord);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
