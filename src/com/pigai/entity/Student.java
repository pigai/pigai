package com.pigai.entity;

// default package
// Generated 2015-5-10 0:12:37 by Hibernate Tools 4.3.1

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
 * Student generated by hbm2java
 */
@Entity
@Table(name = "student", catalog = "pigai")
public class Student implements java.io.Serializable {

	private Integer studentId;
	private String studentNo;
	private String name;
	private String school;
	private String college;
	private String password;
	private Set<Selectcourse> selectcourses = new HashSet<Selectcourse>(0);
	private Set<Submitrecord> submitrecords = new HashSet<Submitrecord>(0);

	public Student() {
	}

	public Student(String studentNo, String name, String school,
			String college, String password) {
		this.studentNo = studentNo;
		this.name = name;
		this.school = school;
		this.college = college;
		this.password = password;
	}

	public Student(String studentNo, String name, String school,
			String college, String password, Set<Selectcourse> selectcourses,
			Set<Submitrecord> submitrecords) {
		this.studentNo = studentNo;
		this.name = name;
		this.school = school;
		this.college = college;
		this.password = password;
		this.selectcourses = selectcourses;
		this.submitrecords = submitrecords;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "studentId", unique = true, nullable = false)
	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "studentNo", nullable = false, length = 50)
	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "school", nullable = false, length = 50)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "college", nullable = false, length = 50)
	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Selectcourse> getSelectcourses() {
		return this.selectcourses;
	}

	public void setSelectcourses(Set<Selectcourse> selectcourses) {
		this.selectcourses = selectcourses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Submitrecord> getSubmitrecords() {
		return this.submitrecords;
	}

	public void setSubmitrecords(Set<Submitrecord> submitrecords) {
		this.submitrecords = submitrecords;
	}

}
