package com.ni.blog.advice;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ni.blog.dto.ResultDTO;
import com.ni.blog.exception.CustomizeErrorCode;
import com.ni.blog.exception.CustomizeException;

@ControllerAdvice
public class CustomizeExceptionHandler {
	@ExceptionHandler(Exception.class)
	ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
		String contenType = request.getContentType();
		if("application/json".equals(contenType)) {
			ResultDTO resultDTO = null;
			//返回JSON
			if(e instanceof CustomizeException) {
				resultDTO = ResultDTO.errorOf((CustomizeException) e);
			}else {
				resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
			}
			try{
				response.setContentType("application/jason");
				response.setStatus(200);
				response.setCharacterEncoding("utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(JSON.toJSONString(resultDTO));
				writer.close();
			}catch (IOException ioe) {
				
			}
			return null;
		}else {
			//错误页面跳转
			if(e instanceof CustomizeException) {
				model.addAttribute("msg", e.getMessage());
			}else {
				model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
			}
			return new ModelAndView("error");
		}
		
	}
		
}
