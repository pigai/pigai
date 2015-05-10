package com.pigai.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pigai.entity.Course;
import com.pigai.entity.Teacher;
import com.pigai.service.CourseService;
import com.pigai.service.CoursewareService;
import com.pigai.service.TeacherService;
import com.pigai.util.CheckUtil;
import com.pigai.util.JSONUtil;
import com.pigai.util.PageModel;
import com.pigai.vo.CourseCriteria;
import com.pigai.vo.User;

@Controller
@RequestMapping(value = "/course")
public class CourseController extends BaseController {

	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;

	@Autowired
	@Qualifier("coursewareService")
	private CoursewareService coursewareService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@RequestMapping()
	public String toIndex(PageModel pageModel, HttpServletRequest request,
			CourseCriteria criteria) {
		request.setAttribute("pageMoel",
				courseService.getPageModelByCriteria(pageModel, criteria));

		return "course/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		if (!CheckUtil.isEmpty(request.getParameter("id"))) {

			try {

				Integer id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("id", id);
				request.setAttribute("course", courseService.get(id));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "course/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void doAdd(HttpServletRequest request, HttpServletResponse response,
			Course course) throws IOException {
		try {

			Integer teacherId = Integer.parseInt(request
					.getParameter("teacherId"));
			Teacher teacher = teacherService.get(teacherId);
			course.setTeacher(teacher);

			if (CheckUtil.isEmpty(course.getCourseId())) {
				courseService.add(course);
			} else {
				courseService.update(course);
			}

			JSONUtil.outputSuccess("添加成功", response);
		} catch (Exception e) {
			JSONUtil.outputError("申请失败", response);

		}
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String toDetail(@PathVariable("id") Integer id,
			HttpServletRequest request) {

		try {
			request.setAttribute("courseId", id);
			request.setAttribute("course", courseService.get(id));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "course/detail";
	}

	@RequestMapping(value = "/ware/{id}", method = RequestMethod.GET)
	public String toCourseware(@PathVariable("id") Integer id,
			HttpServletRequest request, PageModel pageModel) {

		try {
			request.setAttribute("courseId", id);
			pageModel = coursewareService.getPageModelByCourseId(pageModel, id);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "courseware/index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void doDelete(@PathVariable("id") Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			courseService.delete(id);
			JSONUtil.outputSuccess("删除成功", response);
		} catch (Exception e) {
			JSONUtil.outputError("申请失败", response);

		}
	}
	
	@RequestMapping(value = "/studentCourse")
	public String studentCourse(PageModel pageModel, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");	
		PageModel pageMoel = courseService.getPageModelByStudentId(pageModel, user.getUserId());
		request.setAttribute("pageMoel",pageMoel);
		return "course/index";
	}
	
	@RequestMapping(value = "/teacherCourse")
	public String teacherCourse(PageModel pageModel, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		PageModel pageMoel = courseService.getPageModelByTeacherId(pageModel, user.getUserId());
		request.setAttribute("pageMoel", pageMoel);
		return "course/index";
	}

}
