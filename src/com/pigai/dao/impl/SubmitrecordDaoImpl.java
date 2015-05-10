package com.pigai.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jhlabs.composite.AddComposite;
import com.pigai.constant.Constants;
import com.pigai.dao.SubmitrecordDao;
import com.pigai.entity.Homework;
import com.pigai.entity.Selectcourse;
import com.pigai.entity.Submitrecord;
import com.pigai.vo.HomeworkSubmitVo;

@Repository("submitrecordDao")
public class SubmitrecordDaoImpl extends BaseDaoImpl implements SubmitrecordDao {
	protected static final Class<Submitrecord> entityClass = Submitrecord.class;

	@SuppressWarnings("unchecked")
	@Override
	public List<HomeworkSubmitVo> getSubmitrecordsByHomeworkId(
			Integer homeworkId, int offset, int pageSize) {

		Homework homework = (Homework) get(Homework.class, homeworkId);
		String whereJpql = "where o.course.courseId = ?";

		List<Selectcourse> selectCourses = getResultList(Selectcourse.class,
				whereJpql, offset, pageSize, null, new Object[] { homework
						.getCourse().getCourseId() });
		List<HomeworkSubmitVo> homeworkSubmitVos = new ArrayList<HomeworkSubmitVo>();
		for (Selectcourse s : selectCourses) {
			HomeworkSubmitVo homeworkSubmitVo = new HomeworkSubmitVo();
			homeworkSubmitVo.setHomeworkId(homeworkId);
			homeworkSubmitVo.setHomeworkName(homework.getName());
			Submitrecord submitrecord = _ForGetSubmitrecordByHomeworkIdAndStudentId(
					homeworkId, s.getStudent().getStudentId());
			homeworkSubmitVo.setScore(submitrecord.getScore());
			homeworkSubmitVo.setCorrect(submitrecord.isCorrect());
			homeworkSubmitVo.setCreateTime(submitrecord.getCreateTime());
			homeworkSubmitVo.setSubmitId(submitrecord.getSubmitId());
			if (null != submitrecord.getFileinfo()) {
				homeworkSubmitVo.setFileId(submitrecord.getFileinfo()
						.getFileId());
			}
			homeworkSubmitVo.setStudentId(s.getStudent().getStudentId());
			homeworkSubmitVo.setStudentName(s.getStudent().getName());
			homeworkSubmitVos.add(homeworkSubmitVo);
		}
		return homeworkSubmitVos;
	}

	@SuppressWarnings({ "unchecked" })
	private Submitrecord _ForGetSubmitrecordByHomeworkIdAndStudentId(
			Integer homeworkId, Integer studentId) {

		String whereJpql = "where o.homework.homeworkId = ? and  o.student.studentId = ?";

		List<Submitrecord> submitrecords = getResultList(entityClass,
				whereJpql, null, homeworkId, studentId);

		if (null == submitrecords || submitrecords.isEmpty()) {
			Submitrecord submitrecord = new Submitrecord();
			submitrecord.setSubmitId(Constants.NOT_EXSIT);
			submitrecord.setScore(Constants.NOT_EXSIT);
			submitrecord.setCorrect(false);
			return submitrecord;
		}
		return submitrecords.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getHomeworkCountByHomeworkId(Integer homeworkId) {
		Homework homework = (Homework) get(Homework.class, homeworkId);
		String whereJpql = "where o.course.courseId = ?";
		return getResultCount(Selectcourse.class, whereJpql, homework
				.getCourse().getCourseId());
	}

}
