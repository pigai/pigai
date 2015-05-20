package com.pigai.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pigai.entity.Course;
import com.pigai.entity.Courseware;
import com.pigai.entity.Fileinfo;
import com.pigai.hadoop.HttpFSClient;
import com.pigai.service.CourseService;
import com.pigai.service.CoursewareService;
import com.pigai.service.FileinfoService;
import com.pigai.service.TeacherService;
import com.pigai.util.JSONUtil;
import com.pigai.util.PageModel;
import com.pigai.vo.CourseCriteria;

@Controller
@RequestMapping(value = "/teacher/courseware")
public class CoursewareController extends BaseController {

	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;

	@Autowired
	@Qualifier("coursewareService")
	private CoursewareService coursewareService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("fileinfoService")
	private FileinfoService fileinfoService;

	@RequestMapping()
	public String toIndex(PageModel pageModel, HttpServletRequest request,
			CourseCriteria criteria) {
		request.setAttribute("pageMoel",
				courseService.getPageModelByCriteria(pageModel, criteria));

		return "teacher/course/index";
	}

	@RequestMapping(value = "/add/{courseId}", method = RequestMethod.GET)
	public String toAdd(@PathVariable("courseId") Integer courseId,
			HttpServletRequest request) {
		request.setAttribute("courseId", courseId);

		return "teacher/courseware/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void doAdd(HttpServletRequest request, HttpServletResponse response,
			Courseware courseware) throws IOException {
		try {
			Integer courseId = Integer.parseInt(request
					.getParameter("courseId"));
			String path = request.getSession().getServletContext()
					.getRealPath("/courseware/");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("file");
			String hdfsFileName = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date())
					+ file.getOriginalFilename().substring(
							file.getOriginalFilename().lastIndexOf("."));
			Fileinfo fileinfo = new Fileinfo();
			fileinfo.setFileName(file.getOriginalFilename());
			fileinfo.setFilePath("/courseware/" + hdfsFileName);
			fileinfo.setCreateTime(new Date());
			File targetFile = new File(path, hdfsFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			file.transferTo(targetFile);
			HttpFSClient httpfsClient = new HttpFSClient();
			httpfsClient.initCookie();
			// 获取当前用户的目录
			httpfsClient.get("", "op=gethomedirectory");
			// // 上传文件
			httpfsClient.put("/courseware", "op=CREATE&buffersize=1000");
			httpfsClient.upload("/courseware/" + hdfsFileName,
					"op=CREATE&buffersize=1000&data=true",
					targetFile.getAbsolutePath());
			// 获取文件列表信息
			String result = httpfsClient.get("/courseware", "op=LISTSTATUS");
			fileinfoService.add(fileinfo);
			System.out.println(path);
			Course course = courseService.get(courseId);
			courseware.setCourse(course);
			courseware.setFileinfo(fileinfo);
			courseware.setCreateTime(new Date());
			coursewareService.add(courseware);
			targetFile.delete();
			JSONUtil.outputSuccess("添加成功", response);

		} catch (Exception e) {
			e.printStackTrace();
			JSONUtil.outputError("添加失败", response);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String toCourseware(@PathVariable("id") Integer id,
			HttpServletRequest request, PageModel pageModel) {

		try {
			request.setAttribute("courseId", id);
			pageModel = coursewareService.getPageModelByCourseId(pageModel, id);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "teacher/courseware/index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void doDelete(@PathVariable("id") Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			coursewareService.delete(id);
			JSONUtil.outputSuccess("删除成功", response);
		} catch (Exception e) {
			JSONUtil.outputError("申请失败", response);

		}
	}

}
