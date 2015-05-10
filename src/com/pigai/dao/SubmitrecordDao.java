package com.pigai.dao;

import java.util.List;

import com.pigai.vo.HomeworkSubmitVo;

public interface SubmitrecordDao extends BaseDao{

	List<HomeworkSubmitVo> getSubmitrecordsByHomeworkId(Integer homeworkId, int offset,
			int pageSize);

	int getHomeworkCountByHomeworkId(Integer homeworkId);

}
