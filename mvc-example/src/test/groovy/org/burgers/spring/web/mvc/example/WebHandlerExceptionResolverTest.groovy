package org.burgers.spring.web.mvc.example

import org.junit.Test

class WebHandlerExceptionResolverTest {
  @Test
  void resolveException(){
    def result = new WebHandlerExceptionResolver().resolveException(null, null, null, null)
    assert result.viewName == "technicalDifficulties"
  }
}
