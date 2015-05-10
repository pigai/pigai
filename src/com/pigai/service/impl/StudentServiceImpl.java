package com.pigai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigai.dao.CommonDao;
import com.pigai.dao.StudentDao;
import com.pigai.entity.Student;
import com.pigai.service.StudentService;
import com.pigai.util.EncryptionUtil;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private StudentDao studentDao;

	
	public boolean isStudentExisted(String studentNo){
		Student student = studentDao.findStudentById(studentNo);
 		if(student == null)
 		{
 			return false;
 		}else
 			return true;
 	}

	public Student findStudent(String studentNo){
  		return studentDao.findStudentById(studentNo);
  	}
	
  	public Student findStudent(String studentNo,String password){
  		return studentDao.findStudent(studentNo, EncryptionUtil.MD5(password));
  	}
  	

	public void saveStudent(Student student){
  		student.setPassword(EncryptionUtil.MD5(student.getPassword()));
  		studentDao.save(student);
  	}

	public void updateStudent(Student student,String password){
  		student.setPassword(EncryptionUtil.MD5(password));
  		studentDao.update(student);
  	}

	@Override
	public Student getStudent(Integer studentId) {
		return studentDao.get(Student.class, studentId);
	}
		
	
}
