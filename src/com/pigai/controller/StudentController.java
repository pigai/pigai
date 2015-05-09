package com.pigai.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pigai.constant.Constants;
import com.pigai.entity.Course;
import com.pigai.entity.Selectcourse;
import com.pigai.entity.Student;
import com.pigai.service.CourseService;
import com.pigai.service.SelectcourseService;
import com.pigai.service.StudentService;
import com.pigai.util.JSONUtil;
import com.pigai.util.UserType;
import com.pigai.vo.User;

@Controller
@RequestMapping(value="/student")
public class StudentController {	
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SelectcourseService selectcourseService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
		return "student/register";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public void register(HttpServletRequest request,HttpServletResponse response,Student student) throws IOException{
		Boolean exist = studentService.isStudentExisted(student.getStudentNo());
		if(exist == true){
			JSONUtil.outputError(Constants.USER_EXIST, response);
		}else{
			studentService.saveStudent(student);
			JSONUtil.outputSuccess(Constants.REGISTER_SECCESS, response);
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "student/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response,String studentNo,String password) throws IOException{
		Boolean exist = studentService.isStudentExisted(studentNo);
		if(exist == true){
			Student student = studentService.findStudent(studentNo, password);
			System.out.println(student);
			if(student!=null){
				JSONUtil.outputSuccess(Constants.LOGIN_SUCCESS, response);
				User user = new User(student.getStudentId(),student.getStudentNo(),student.getName(),UserType.STUDENT);
				request.getSession().setAttribute("user",user);
			}else{
				JSONUtil.outputError(Constants.PASSWORD_IS_WRONG, response);
			}
		}else{
			JSONUtil.outputError(Constants.USER_NOT_EXIST, response);
		}		
	}

	@RequestMapping(value="/info",method=RequestMethod.GET)
	public String center(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		Student student = studentService.findStudent(user.getUserNo());
		request.setAttribute("student", student);
		return "student/info";
	}
	
	@RequestMapping(value = "updatePassword",method=RequestMethod.GET)
	public String updatepass(){
		return "student/updatePassword";
	}
	@RequestMapping(value = "updatePassword",method=RequestMethod.POST)
	public void updatePass(HttpServletRequest request,HttpServletResponse response,String oldpassword,String newpassword) throws IOException{
		User user = (User)request.getSession().getAttribute("user");
		Student student = studentService.findStudent(user.getUserNo(),oldpassword);
		if(student == null){
			JSONUtil.outputError(Constants.PASSWORD_IS_WRONG, response);
		}else{
			studentService.updateStudent(student, newpassword);
			JSONUtil.outputSuccess(Constants.UPDATE_SECCESS, response);	
		}				
	}

	@RequestMapping(value = "selectCourse/{courseId}")
	public void secelt(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer courseId) throws IOException{		
		try {
			User user = (User)request.getSession().getAttribute("user");
			Student student = studentService.getStudent(user.getUserId());
			Course course = courseService.get(courseId);
			Selectcourse selectcourse = new Selectcourse(course, student, 0, new Date(), course.getCourseName());
			selectcourseService.add(selectcourse);
			JSONUtil.outputSuccess("选课成功", response);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtil.outputError("选课失败", response);
		}
	}
	@RequestMapping(value = "submit",method=RequestMethod.GET)
	public String submit(){
		return "student/submitHomework";
	}
}
