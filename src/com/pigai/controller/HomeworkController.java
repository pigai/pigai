package com.pigai.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pigai.entity.Course;
import com.pigai.entity.Homework;
import com.pigai.entity.Selectcourse;
import com.pigai.entity.Submitrecord;
import com.pigai.service.CourseService;
import com.pigai.service.HomeworkService;
import com.pigai.service.SelectcourseService;
import com.pigai.service.SubmitrecordService;
import com.pigai.service.TeacherService;
import com.pigai.util.CheckUtil;
import com.pigai.util.JSONUtil;
import com.pigai.util.PageModel;

@Controller
@RequestMapping(value = "/teacher/homework")
public class HomeworkController extends BaseController {

	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;

	@Autowired
	@Qualifier("homeworkService")
	private HomeworkService homeworkService;

	@Autowired
	@Qualifier("submitrecordService")
	private SubmitrecordService submitrecordService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("selectcourseService")
	private SelectcourseService selectcourseService;

	@RequestMapping(value = "/add/{courseId}", method = RequestMethod.GET)
	public String toAdd(@PathVariable("courseId") Integer courseId,
			HttpServletRequest request) {
		request.setAttribute("courseId", courseId);
		String id = request.getParameter("id");
		if (!CheckUtil.isEmpty(id)) {
			try {
				int homeworkId = Integer.parseInt(id);
				request.setAttribute("homeworkId", homeworkId);
				request.setAttribute("homework",
						homeworkService.get(homeworkId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "teacher/homework/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void doAdd(HttpServletRequest request, HttpServletResponse response,
			Homework homework) throws IOException {
		try {
			Integer courseId = Integer.parseInt(request
					.getParameter("courseId"));
			Course course = courseService.get(courseId);
			homework.setCourse(course);
			homework.setCreateTime(new Date());
			if (null == homework.getHomeworkId()) {
				homeworkService.add(homework);
			} else {
				homeworkService.update(homework);
			}

			JSONUtil.outputSuccess("添加成功", response);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtil.outputError("添加失败", response);

		}
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public String toHomework(@PathVariable("courseId") Integer courseId,
			HttpServletRequest request, PageModel pageModel) {

		try {
			request.setAttribute("courseId", courseId);
			pageModel = homeworkService.getPageModelByCourseId(pageModel,
					courseId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "teacher/homework/index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void doDelete(@PathVariable("id") Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			homeworkService.delete(id);
			JSONUtil.outputSuccess("删除成功", response);
		} catch (Exception e) {
			JSONUtil.outputError("申请失败", response);

		}
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String toDetail(@PathVariable("id") Integer id,
			HttpServletRequest request) {
		try {
			request.setAttribute("homeworkId", id);
			request.setAttribute("homework", homeworkService.get(id));

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "teacher/homework/detail";
	}

	@RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
	public String toSubmit(@PathVariable("id") Integer id, PageModel pageModel,
			HttpServletRequest request) {
		try {

			request.setAttribute("homeworkId", id);
			pageModel = submitrecordService.getPageModelByHomeWorkId(pageModel,
					id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "teacher/homework/submit_list";
	}

	@RequestMapping(value = "/grade/{id}", method = RequestMethod.GET)
	public String toGrade(@PathVariable("id") Integer id, PageModel pageModel,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("id", id);
		return "teacher/homework/grade";
	}

	@RequestMapping(value = "/grade/{id}", method = RequestMethod.POST)
	public void doGrade(@PathVariable("id") Integer id, PageModel pageModel,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			int score = Integer.parseInt(request.getParameter("score"));
			submitrecordService.updateScore(score,id);		
			JSONUtil.outputSuccess("打分成功", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
