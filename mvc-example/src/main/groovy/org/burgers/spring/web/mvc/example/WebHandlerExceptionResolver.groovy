package org.burgers.spring.web.mvc.example

import org.apache.log4j.Logger
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class WebHandlerExceptionResolver implements HandlerExceptionResolver{
  def logger = Logger.getLogger(this.class)
  ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
      logger.error(e.message, e)
      new ModelAndView("technicalDifficulties")
  }
}
