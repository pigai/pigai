package com.pigai.service;

import com.pigai.entity.Student;

public interface StudentService{

	
	public boolean isStudentExisted(String studentNo);
	public Student findStudent(String studentNo);
  	public Student findStudent(String studentNo,String password);
  	
  	public void saveStudent(Student student);
  	public void updateStudent(Student student,String password);
  	
  	public Student getStudent(Integer studentId);
  	
}
