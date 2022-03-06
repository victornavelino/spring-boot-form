package com.springboot.form.interceptores;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Es un metodo del controlador!! y se llama: " + metodo.getMethod().getName());
		}
		long tiempoInicio = System.currentTimeMillis();
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...tiempoInicio: " + tiempoInicio);
		request.setAttribute("tiempoInicio", tiempoInicio);

		Random random = new Random();
		Integer demora = random.nextInt(100);
		Thread.sleep(demora);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (!request.getMethod().equalsIgnoreCase("post")) {

			long tiempoFin = System.currentTimeMillis();
			logger.info("TiempoTranscurridoInterceptor: postHandle() entrando...tiempoFin: " + tiempoFin);
			long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
			long tiempoTranscurrido = tiempoFin - tiempoInicio;

			if (handler instanceof HandlerMethod && modelAndView != null) {
				modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
			}
			logger.info("TiempoTranscurrido: " + tiempoTranscurrido);
			logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo...");

		}
	}

}
