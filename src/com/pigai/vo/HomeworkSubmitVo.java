package com.pigai.vo;

import java.util.Date;

public class HomeworkSubmitVo {

	Integer homeworkId;
	Integer score;
	Integer studentId;
	Integer submitId;
	boolean correct;
	String studentName;
	String homeworkName;
	Date  createTime;
	Integer   fileId;

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

}
