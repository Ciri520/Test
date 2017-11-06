package cn.itcast.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		// 1）写日志(配置log4j)
		e.printStackTrace();
		// 2）发送邮件
		// 3）发短信
		// 4）展示一个错误页面，提示。
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", e.getMessage());
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
