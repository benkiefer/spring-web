package org.burgers.demo

import org.junit.Test

class WebHandlerExceptionResolverTest {
  @Test
  void resolveException(){
    def result = new WebHandlerExceptionResolver().resolveException(null, null, null, null)
    assert result.viewName == "technicalDifficulties"
  }
}
