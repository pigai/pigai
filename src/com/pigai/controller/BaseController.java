package com.pigai.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 控制器基类，做一些数据绑定之类的全局操作
 * 
 * @author chenlei
 *
 */
public abstract class BaseController extends MultiActionController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (!StringUtils.isEmpty(text)) {
					try {
						Date date = null;
						if (text.length() == 13) {
							// 毫秒
							Long time = Long.valueOf(text);
							date = new Date(time);
						} else {
							date = sf.parse(text);
						}
						setValue(date);
					} catch (ParseException e) {
						throw new IllegalArgumentException(e);
					}
				}
			}
		});

		binder.registerCustomEditor(Timestamp.class,
				new PropertyEditorSupport() {

					final SimpleDateFormat sf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");

					@Override
					public void setAsText(String text)
							throws IllegalArgumentException {
						if (!StringUtils.isEmpty(text)) {
							try {
								Date date = sf.parse(text);
								setValue(new Timestamp(date.getTime()));
							} catch (ParseException e) {
								throw new IllegalArgumentException(e);
							}
						}
					}
				});

	}

	/**
	 * 转到错误页面，并提示错误信息
	 * 
	 * @param request
	 * @param errorMessage
	 * @return
	 */
	public String toErrorPage(HttpServletRequest request, String errorMessage) {
		request.setAttribute("ERROR_MSG", errorMessage);
		return "common/error";
	}
}
