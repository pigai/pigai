package com.pigai.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pigai.entity.Courseware;
import com.pigai.entity.Fileinfo;
import com.pigai.entity.Submitrecord;
import com.pigai.service.CoursewareService;
import com.pigai.service.FileinfoService;
import com.pigai.service.SubmitrecordService;


@Controller
@RequestMapping("/download")
public class DownloadController {
	
	@Autowired
	private FileinfoService fileinfoService;
	
	@Autowired
	private CoursewareService coursewareService;
	
	@Autowired
	private SubmitrecordService submitrecordService;

	@RequestMapping("/courseware/{coursewareId}")
	public void download(@PathVariable("coursewareId") String coursewareId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")+"\\upload\\";
		System.out.println("ctxPath"+ctxPath);

		try {
			Integer coursewareid = Integer.parseInt(coursewareId);
			Courseware courseware = coursewareService.get(coursewareid);
			Fileinfo fileinfo = fileinfoService.get(courseware.getFileinfo().getFileId());
			String downLoadPath = ctxPath + fileinfo.getFileName();
			System.out.println("downLoadPath"+downLoadPath);
			long fileLength = new File(downLoadPath).length();
			response.setContentType("multipart/form-data");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileinfo.getFileName().getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return;
	}
	@RequestMapping("/submitrecord/{submitrecordId}")
	public void homeworkdownload(@PathVariable("submitrecordId") String submitrecordId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")+"\\upload\\";
		System.out.println("ctxPath"+ctxPath);

		try {
			Integer submitrecordid = Integer.parseInt(submitrecordId);
			Submitrecord  submitrecord = submitrecordService.get(submitrecordid);
			Fileinfo fileinfo = fileinfoService.get(submitrecord.getFileinfo().getFileId());
			String downLoadPath = ctxPath + fileinfo.getFileName();
			System.out.println("downLoadPath"+downLoadPath);
			long fileLength = new File(downLoadPath).length();
			response.setContentType("multipart/form-data");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileinfo.getFileName().getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return;
	}

}
