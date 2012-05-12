package org.burgers.web

import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component

@Component
class WebHandlerExceptionResolver implements HandlerExceptionResolver{
  ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
      new ModelAndView("technicalDifficulties")
  }
}