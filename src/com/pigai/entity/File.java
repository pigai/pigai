package com.pigai.entity;

// default package
// Generated 2015-5-5 22:39:25 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * File generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "file", catalog = "pigai")
public class File implements java.io.Serializable {

	private Integer fileId;
	private String fileName;
	private String filePath;
	private Double fileSize;
	private String fileType;
	private Set<Courseware> coursewares = new HashSet<Courseware>(0);
	private Set<Submitrecord> submitrecords = new HashSet<Submitrecord>(0);

	public File() {
	}

	public File(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public File(String fileName, String filePath, Double fileSize,
			String fileType, Set<Courseware> coursewares,
			Set<Submitrecord> submitrecords) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.coursewares = coursewares;
		this.submitrecords = submitrecords;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "fileId", unique = true, nullable = false)
	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "fileName", nullable = false)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "filePath", nullable = false)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "fileSize", precision = 22, scale = 0)
	public Double getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "fileType")
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Courseware> getCoursewares() {
		return this.coursewares;
	}

	public void setCoursewares(Set<Courseware> coursewares) {
		this.coursewares = coursewares;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Submitrecord> getSubmitrecords() {
		return this.submitrecords;
	}

	public void setSubmitrecords(Set<Submitrecord> submitrecords) {
		this.submitrecords = submitrecords;
	}

}