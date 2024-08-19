package com.tenco.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.blog.handler.exception.DataDeleveryException;
import com.tenco.blog.handler.exception.RedirectException;
import com.tenco.blog.handler.exception.UnAuthorizedException;

@ControllerAdvice // IoC 대상 (싱글톤 패턴) --> HTML 렌더링 예외에 많이 사용
public class GlobalControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(DataDeleveryException.class)
	public String dataDeleveryException(DataDeleveryException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('"+ e.getMessage() +"'); ");
		sb.append(" window.history.back(); ");
		sb.append(" </script> ");
		return sb.toString();
	}
	
	@ResponseBody
	@ExceptionHandler(UnAuthorizedException.class)
	public String unAuthorizedException(Exception e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('"+ e.getMessage() +"'); ");
		sb.append(" location.href='/user/sign-in'; ");
		sb.append(" </script> ");
		return sb.toString();
	}
	
	/**
	 * 에러 페이지로 이동 처리
	 * JSP로 이동시 데이터를 담아서 보내는 방법
	 * ModelAndView, Model 사용 가능
	 * throw new RedirectException("페이지가 존재하지 않음", 404);
	 */
	
	@ResponseBody
	@ExceptionHandler(RedirectException.class)
	public ModelAndView redirectException(RedirectException e) {
		
		ModelAndView modelAndView = new ModelAndView("errorPage"); 
		modelAndView.addObject("statusCode", e.getStatus().value());
		modelAndView.addObject("message", e.getMessage());
		return modelAndView; // 페이지 반환 + 데이터 내려 줌
	}
	
	
	
}
