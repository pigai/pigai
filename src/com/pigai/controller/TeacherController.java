package com.pigai.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pigai.constant.Constants;
import com.pigai.entity.Teacher;
import com.pigai.service.TeacherService;
import com.pigai.util.EncryptionUtil;
import com.pigai.util.JSONUtil;
import com.pigai.util.UserType;
import com.pigai.vo.User;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController extends BaseController {

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "teacher/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(HttpServletRequest request,
			HttpServletResponse response, Teacher teacher) throws IOException {
		Boolean exist = teacherService.isTeacherExisted(teacher.getTeacherNo());
		if (exist == true) {
			JSONUtil.outputError(Constants.USER_EXIST, response);
		} else {
			try {
				teacherService.add(teacher);
				JSONUtil.outputSuccess(Constants.REGISTER_SECCESS, response);
			} catch (Exception e) {
				JSONUtil.outputError(Constants.USER_EXIST, response);
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "teacher/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response,
			String teacherNo, String password) throws IOException {
		Boolean exist = teacherService.isTeacherExisted(teacherNo);
		if (exist == true) {
			Teacher teacher = teacherService.getTeacherByUsernameAndPwd(
					teacherNo, password);
			System.out.println(teacher);
			if (teacher != null) {
				JSONUtil.outputSuccess(Constants.LOGIN_SUCCESS, response);
				User user = new User(teacher.getTeacherId(),
						teacher.getTeacherNo(), teacher.getName(),
						UserType.TEACHER);
				request.getSession().setAttribute("user", user);
			} else {
				JSONUtil.outputError(Constants.PASSWORD_IS_WRONG, response);
			}
		} else {
			JSONUtil.outputError(Constants.USER_NOT_EXIST, response);
		}
	}
	@RequestMapping(value="/logout")
	public String logoutDo(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if (session!=null)
		{
			session.invalidate();
		}
		return "forward:../home/homepage";
	}

	@RequestMapping(value = "/info")
	public String center(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Teacher teacher = new Teacher();
		try {
			teacher = teacherService.get(user.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("teacher", teacher);
		return "teacher/info";
	}

	@RequestMapping(value = "updatepassword")
	public String updatepass() {
		return "teacher/updatepassword";
	}

	@RequestMapping(value = "updatepassword", method = RequestMethod.POST)
	public void updatepass(HttpServletRequest request,
			HttpServletResponse response, String oldpassword, String newpassword)
			throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		Teacher teacher = teacherService.getTeacherByUsernameAndPwd(
				user.getUserNo(), oldpassword);
		if (teacher == null) {
			JSONUtil.outputError(Constants.PASSWORD_IS_WRONG, response);
		} else {
			try {
				teacher.setPassword(EncryptionUtil.MD5(newpassword));
				teacherService.update(teacher);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONUtil.outputSuccess(Constants.UPDATE_SECCESS, response);
		}
	}

}
