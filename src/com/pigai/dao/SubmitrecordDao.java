package com.pigai.dao;

import java.util.List;

import com.pigai.entity.Submitrecord;
import com.pigai.vo.HomeworkSubmitVo;

public interface SubmitrecordDao extends BaseDao{

	List<HomeworkSubmitVo> getSubmitrecordsByHomeworkId(Integer homeworkId, int offset,
			int pageSize);

	int getHomeworkCountByHomeworkId(Integer homeworkId);
	
	List<HomeworkSubmitVo> getSubmitrecordsByHomeworkIdAndStudentId(Integer homeworkId,
			Integer studentId,int offset,int pageSize);

	int getSubmitrecordsCountByHomeworkIdAndStudentId(Integer homeworkId,Integer studentId);

	List<HomeworkSubmitVo> getSubmitrecordsByStudentId(Integer studentId,
			int offset,int pageSize);

	int getSubmitrecordsCountByStudentId(Integer studentId);
}
